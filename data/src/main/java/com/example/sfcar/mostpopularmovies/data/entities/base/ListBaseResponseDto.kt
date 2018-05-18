package com.example.sfcar.mostpopularmovies.data.entities.base

data class ListBaseResponseDto<T>(val page: Int = 0,
                                  val results: List<T>? = null,
                                  val total_results: Int = 0,
                                  val total_pages: Int = 0)
