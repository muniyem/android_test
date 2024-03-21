package com.example.androidtest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.androidtest.databinding.FragmentMovieDetailBinding
import com.example.androidtest.view.view_models.MovieDetailViewModel
import com.example.androidtest.view.view_models.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<MovieDetailViewModel>()
//    private val sharedViewModel by activityViewModels
    private val sharedViewModel: SharedViewModel by activityViewModels()

//    private var adapterMovies: MoviesAdapter? = null
//private  val argsMovieItem:MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(
            inflater, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner

            movieDetailVm = viewModel    // Attach your view model here

        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.MovieId.observe(viewLifecycleOwner){
            viewModel.MovieDetailApiCall(it)
        }
//        val movieItem = argsMovieItem.MovieItem

//        viewModel.MovieDetailApiCall(movieItem.)
//        adapterMovies = MoviesAdapter(object : MoviesAdapter.OnItemClickListener {
//            override fun onItemClick(item: MoviesItem?) {
//
//            }
//        })
//
//        _binding?.listOfMovies?.layoutManager = LinearLayoutManager(requireContext())
//        _binding?.listOfMovies?.setHasFixedSize(true)
//        _binding?.listOfMovies?.adapter = adapterMovies!!
//
//
//        viewModel.viewModelScope.launch {
//            viewModel.listMovies.collect{
//                adapterMovies?.submitData(lifecycle, it)
//            }
//        }

//
//        adapterMovies?.addLoadStateListener { combinedLoadStates ->
//
//
//            if (combinedLoadStates.refresh is LoadState.Loading || combinedLoadStates.append is LoadState.Loading) {
////                showProgressbar
//            } else {
////            hideProgressbar
//            }
//        }    }
    }
}