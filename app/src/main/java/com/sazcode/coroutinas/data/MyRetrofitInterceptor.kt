package com.sazcode.coroutinas.data

import android.util.Log
import jakarta.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class MyRetrofitInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Imprimir Request
        Log.d("OkHttp", "➡️ REQUEST: ${request.method} ${request.url}")
        Log.d("OkHttp", "Headers:\n${request.headers}")
        request.body?.let { body ->
            Log.d("OkHttp", "Body: $body")
        }

        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs = (System.nanoTime() - startNs) / 1e6

        // Imprimir Response
        Log.d("OkHttp", "⬅️ RESPONSE: ${response.code} ${response.message} (${tookMs}ms)")
        Log.d("OkHttp", "Headers:\n${response.headers}")

        // ⚠️ Solo usa peekBody para loguear sin consumir el stream
        val responseBody = response.peekBody(Long.MAX_VALUE)
        Log.d("OkHttp", "Body:\n${responseBody.string()}")

        return response
    }
}