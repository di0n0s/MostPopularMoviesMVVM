package com.example.sfcar.mostpopularmovies.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun getScheduler(): Scheduler
}