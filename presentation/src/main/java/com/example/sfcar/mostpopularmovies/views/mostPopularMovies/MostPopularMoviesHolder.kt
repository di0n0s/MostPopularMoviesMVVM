package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.glide.GlideApp
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MostPopularMoviesHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindMovie(movie: MovieViewModel) {
//        setTextViewsTexts(movie)
        setPoster(movie)
    }

//    private fun setTextViewsTexts(movie: MovieViewModel) {
//        itemView.movieTitleTextView.text = movie.title
//        itemView.movieDateTextView.text = movie.releaseDate
//        itemView.movieOverviewTextView.text = movie.overview
//    }

    private fun setPoster(movie: MovieViewModel) {
        if (movie.picturePath != "") {
            GlideApp.with(itemView.context)
                    .load(movie.picturePath)
                    .placeholder(R.drawable.img_empty_documentos)
                    .centerInside()
                    .into(itemView.moviePoster as ImageView)
        } /*else {
            GlideApp.with(itemView.context)
                    .load(R.drawable.img_empty_documentos)
                    .into(itemView.moviePoster as ImageView)
        }*/
    }
}