package com.example.sfcar.mostpopularmovies.domain.interactor.usecase

import com.example.sfcar.mostpopularmovies.domain.interactor.params.MostPopularMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.params.Params
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) : BaseUseCase<MovieListPagination>() {

    override fun buildUseCaseObservable(params: Params): Observable<MovieListPagination> =
            movieRepository.getPopularMovies((params as MostPopularMoviesParams).page)

}