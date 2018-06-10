package com.example.sfcar.mostpopularmovies.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.interfaces.AdapterListOnClickListener
import com.example.sfcar.mostpopularmovies.model.BaseMovieView
import com.example.sfcar.mostpopularmovies.model.MovieView
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesFooterHolder
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesHolder

class MostPopularMoviesAdapter(private val movieList: List<BaseMovieView>, private val viewListener: AdapterListOnClickListener.ViewListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AdapterListOnClickListener.AdapterListener {

    private var lastPosition = -1

    companion object {
        const val MOVIE_TYPE = 1
        const val FOOTER_TYPE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                MOVIE_TYPE ->
                    MostPopularMoviesHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false), this)
                else -> {
                    MostPopularMoviesFooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.generic_footer, parent, false))
                }

            }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MostPopularMoviesHolder -> holder.bindMovie(movieList[position] as MovieView)
            is MostPopularMoviesFooterHolder -> holder.bindProgressBar()
        }
        setAnimation(holder.itemView, position)
    }

    override fun getItemViewType(position: Int): Int =
            when (movieList[position]) {
                is MovieView -> MOVIE_TYPE
                else -> FOOTER_TYPE
            }

    override fun onItemSelected(position: Int, view: View) {
        viewListener.onItemSelected(position, view)
    }

    fun restartLastPosition() {
        lastPosition = -1
    }

    @SuppressLint("PrivateResource")
    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.abc_slide_in_bottom)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}