package com.example.androidtest.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.adapters.MoviesAdapter
import com.example.androidtest.callbacks.TextChangeWatcher
import com.example.androidtest.database.GetDataBaseInstance
import com.example.androidtest.databinding.FragmentMoviesListBinding
import com.example.androidtest.helpers.NetworkConnection
import com.example.androidtest.helpers.TextWatcherHelper
import com.example.androidtest.helpers.hideProgress
import com.example.androidtest.helpers.showProgress
import com.example.androidtest.helpers.snack
import com.example.androidtest.models.MoviesItem
import com.example.androidtest.view.view_models.MoviesListViewModel
import com.example.androidtest.view.view_models.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint

class MoviesFragment : Fragment(), TextChangeWatcher {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<MoviesListViewModel>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var adapterMovies: MoviesAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesListBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner

            moviesVm = viewModel    // Attach your view model here

        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterMovies = MoviesAdapter(object : MoviesAdapter.OnItemClickListener {
            override fun onItemClick(item: MoviesItem?) {
                if (NetworkConnection.isNetworkAvailable(requireContext())) {

                    item?.id?.let { sharedViewModel.sendMovieId(it) }
                    val action = item?.let { it1 ->
                        MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment()
                    }
                    if (action != null) {
                        findNavController().navigate(action)
                    }
                } else {
                    view.snack("No Internet Connection")
                }
            }
        })

        _binding?.listOfMovies?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.listOfMovies?.setHasFixedSize(true)
        _binding?.listOfMovies?.adapter = adapterMovies!!

        if (NetworkConnection.isNetworkAvailable(requireContext())) {

            viewModel.viewModelScope.launch {
                viewModel.listMovies.collect {

                    adapterMovies?.submitData(lifecycle, it)

                }
            }

            adapterMovies?.snapshot()?.items?.forEachIndexed { index, element ->
                GetDataBaseInstance.getRoomDataBase(requireContext())?.movieDao()?.insertAll(
                    MoviesItem
                        (
                        element.adult,
                        element.backdrop_path,
                        element.id,
                        element.original_language,
                        element.original_title,
                        element.overview,
                        element.popularity,
                        element.poster_path,
                        element.release_date,
                        element.title,
                        element.video,
                        element.vote_average,
                        element.vote_count
                    )
                )
            }
        } else {
            val moviesList = GetDataBaseInstance.getRoomDataBase(requireContext())?.movieDao()?.getAllMovies()

            if (moviesList != null) {
                val distinct = moviesList.distinct()
                adapterMovies?.submitData(lifecycle, PagingData.from(distinct))
            }
        }



        adapterMovies?.addLoadStateListener { combinedLoadStates ->


            if (combinedLoadStates.refresh is LoadState.Loading || combinedLoadStates.append is LoadState.Loading) {
                binding?.progressBar?.showProgress(requireContext(),"Loading")
            } else {
                binding?.progressBar?.hideProgress()

            }
        }

        binding?.editSearch?.addTextChangedListener(TextWatcherHelper(this))

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val filteredMoviesList = GetDataBaseInstance.getRoomDataBase(requireContext())?.movieDao()?.getFilteredMovies(s.toString())

        if (filteredMoviesList != null) {
            val distinct = filteredMoviesList.distinct()
            adapterMovies?.submitData(lifecycle, PagingData.from(distinct))
        }
    }

}




