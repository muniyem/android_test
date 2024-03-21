package com.example.androidtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.databinding.FragmentMoviesItemBinding
import com.example.androidtest.models.MoviesItem

class MoviesAdapter(private val listener: OnItemClickListener?) :
    PagingDataAdapter<MoviesItem, MoviesAdapter.ViewHolder>(COMPARATOR) {

    private val mItems: ArrayList<MoviesItem>? = null
    fun getItems(): List<MoviesItem?>? {
        return mItems
    }


    private lateinit var binding: FragmentMoviesItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesList = getItem(position)
        if (moviesList != null) {
            holder.bind(moviesList, listener)
        }
    }

    class ViewHolder(
        private val binding: FragmentMoviesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MoviesItem, listener: OnItemClickListener?) {
            binding.moviesItem = movieItem

            binding.cvItem.setOnClickListener {
                listener?.onItemClick(
                    movieItem
                )
            }
        }
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MoviesItem>() {
            override fun areItemsTheSame(oldItem: MoviesItem, newItem: MoviesItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesItem, newItem: MoviesItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: MoviesItem?)
    }


}





















