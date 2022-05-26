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
        return try {

            val url = URL(BuildConfig.BASE_URL)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.setRequestProperty("Content-Type", "application/json")
            httpURLConnection.setRequestProperty("Accept", "application/json")
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true
            val responseCode = httpURLConnection.responseCode
            val sb = StringBuilder()
            reader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))

            var line: String?

            while (reader.readLine().also { line = it } != null) {
                sb.append(line + "\n")
            }

            return Response.Success(responseCode, sb.toString())
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
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }


}