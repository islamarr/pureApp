package com.islam.pureApp.data.remote.api

import com.islam.pureApp.BuildConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiServiceImpl : ApiService {

    override fun getAllWords(): Response<String> {

        var reader: BufferedReader? = null
        var resCode: Int

        return try {
            val url = URL(BuildConfig.BASE_URL)
            (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Accept", "application/json")
                reader = BufferedReader(InputStreamReader(inputStream))
                resCode = responseCode
            }

            val builder = StringBuilder()
            var line: String?
            while (reader?.readLine().also { line = it } != null) {
                builder.append(line + "\n")
            }

            return Response.Success(resCode, builder.toString())

        } catch (e: Throwable) {
            when (e) {
                is IOException -> {
                    Response.NetworkError
                }
                is KotlinNullPointerException -> {
                    Response.EmptyError
                }
                else -> {
                    Response.Error(message = e.message)
                }
            }


        } finally {
            reader?.let {
                try {
                    it.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }

}