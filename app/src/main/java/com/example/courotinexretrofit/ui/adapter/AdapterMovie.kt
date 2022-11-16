package com.example.courotinexretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.courotinexretrofit.data.model.Movie
import com.example.courotinexretrofit.databinding.ItemPopularMovieBinding

class AdapterMovie : RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {
    class MovieViewHolder(private val binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(movie: Movie){
            binding.titleMovie.text = movie.title
            binding.ratingMovie.text = movie.rating.toString()
            Glide.with(binding.root.context).load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .into(binding.posterMovie)
        }
    }

    private val listMovie = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularMovieBinding.inflate(layoutInflater,parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bindView(movie)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    fun addItemMovie(item: List<Movie>){
        listMovie.apply {
            clear()
            addAll(item)
            notifyDataSetChanged()
        }
    }
}