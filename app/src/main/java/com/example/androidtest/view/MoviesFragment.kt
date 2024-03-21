package com.example.androidtest.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.adapters.MoviesAdapter
import com.example.androidtest.databinding.FragmentMoviesListBinding
import com.example.androidtest.models.MoviesItem
import com.example.androidtest.view.view_models.MoviesListViewModel
import com.example.androidtest.view.view_models.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint

class MoviesFragment : Fragment() {

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
                item?.id?.let { sharedViewModel.sendMovieId(it) }
                val action = item?.let { it1 ->
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment()
                }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
        })

        _binding?.listOfMovies?.layoutManager = LinearLayoutManager(requireContext())
        _binding?.listOfMovies?.setHasFixedSize(true)
        _binding?.listOfMovies?.adapter = adapterMovies!!


        viewModel.viewModelScope.launch {
            viewModel.listMovies.collect {
                adapterMovies?.submitData(lifecycle, it)
            }
        }

        binding?.editSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the data based on the entered text
                filterData(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
                // Not used
            }
        })


        adapterMovies?.addLoadStateListener { combinedLoadStates ->


            if (combinedLoadStates.refresh is LoadState.Loading || combinedLoadStates.append is LoadState.Loading) {
//                showProgressbar
            } else {
//            hideProgressbar
            }
        }
    }

//    private fun filterData(query: String) {
//        val filteredData = originalData.filter { it.contains(query, ignoreCase = true) }
//        adapter.clear()
//        adapter.addAll(filteredData)
//        adapter.notifyDataSetChanged()
//    }
}