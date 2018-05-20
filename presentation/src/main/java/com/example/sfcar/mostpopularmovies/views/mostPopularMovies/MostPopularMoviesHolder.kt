package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.glide.CustomRequestListener
import com.example.sfcar.mostpopularmovies.glide.GlideApp
import com.example.sfcar.mostpopularmovies.interfaces.AdapterListOnClickListener
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MostPopularMoviesHolder(itemView: View?, private val adapterListener: AdapterListOnClickListener.AdapterListener) : RecyclerView.ViewHolder(itemView) {

    fun bindMovie(movie: MovieViewModel) {
        setPoster(movie)
        itemView.setOnClickListener { adapterListener.onItemSelected(adapterPosition, itemView) }
    }

    private fun setPoster(movie: MovieViewModel) {
        if (movie.picturePath != "") {
            GlideApp.with(itemView.context)
                    .load(movie.picturePath)
                    .apply(RequestOptions().centerInside())
                    .apply(RequestOptions().placeholder(R.drawable.img_empty_documentos))
                    .listener(CustomRequestListener(itemView))
                    .into(itemView.moviePoster as ImageView)
        }
    }
}