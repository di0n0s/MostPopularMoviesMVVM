package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.sfcar.mostpopularmovies.R
import kotlinx.android.synthetic.main.generic_footer.view.*

class MostPopularMoviesFooterHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindProgressBar() {
        itemView.footerProgressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white), PorterDuff.Mode.MULTIPLY)
    }

}