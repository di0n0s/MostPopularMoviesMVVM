package com.example.sfcar.mostpopularmovies.domain.interactor.usecase

import com.example.sfcar.mostpopularmovies.domain.executor.PostExecutionThread
import com.example.sfcar.mostpopularmovies.domain.interactor.params.Params
import com.example.sfcar.mostpopularmovies.domain.interactor.params.SearchMoviesParams
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository, private val postExecutionThread: PostExecutionThread)
    : BaseUseCase<MovieListPagination>(postExecutionThread = postExecutionThread) {

    override fun execute(params: Params, observer: DisposableObserver<MovieListPagination>) {
        if (!(params as SearchMoviesParams).loadEndlessData) {
            val publishSubject = createPublishSubject()
            val observable = createDebounceObservable(publishSubject, params, observer)
            launchPublishSubject(publishSubject, params)
            addDisposable(observable)
        } else
            super.execute(params, observer)
    }

    private fun launchPublishSubject(publishSubject: PublishSubject<Params>, params: Params) {
        publishSubject.onNext(params)
    }

    private fun createDebounceObservable(publishSubject: PublishSubject<Params>, params: Params, observer: DisposableObserver<MovieListPagination>): DisposableObserver<MovieListPagination> {
        return publishSubject.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap {
                    buildUseCaseObservable(params)
                            .subscribeOn(Schedulers.io())
                            .observeOn(postExecutionThread.getScheduler())
                }
                .subscribeWith(observer)
    }

    private fun createPublishSubject(): PublishSubject<Params> {
        return PublishSubject.create()
    }

    override fun buildUseCaseObservable(params: Params): Observable<MovieListPagination> =
            movieRepository.searchMovies((params as SearchMoviesParams).page, params.query)
}