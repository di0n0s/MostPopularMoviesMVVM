package com.example.sfcar.mostpopularmovies.data.net.interceptor

import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        var request = chain?.request()
        var url = request?.url()

        val urlBuilder = url?.newBuilder()
        urlBuilder?.addEncodedQueryParameter(ApiConstants.QUERY_PARAM_API_KEY, ApiConstants.API_KEY)
        urlBuilder?.addEncodedQueryParameter(ApiConstants.QUERY_PARAM_LANGUAGE, Locale.getDefault().displayLanguage)
        urlBuilder?.addEncodedQueryParameter(ApiConstants.QUERY_PARAM_REGION, Locale.getDefault().toString())

        url = urlBuilder?.build()
        request = request?.newBuilder()?.url(url)?.build()
        println("URl -> " + request?.url())
        return chain?.proceed(request)!!
    }


}