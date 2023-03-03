package com.amg.openlist.data.remote

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        return if (originalResponse.body != null && originalResponse.body!!
                .contentLength() != 0L
        ) {
            var responseBodyString = originalResponse.body!!.string()

            val jsonObject = Gson().fromJson(responseBodyString, JsonObject::class.java)

            if (jsonObject.has("results")) {
                responseBodyString = jsonObject.get("results").asJsonArray.toString()
            }

            val responseBody =
                responseBodyString.toResponseBody(originalResponse.body!!.contentType())

            originalResponse.newBuilder()
                .body(responseBody)
                .build()

        } else {
            originalResponse
        }

    }
}