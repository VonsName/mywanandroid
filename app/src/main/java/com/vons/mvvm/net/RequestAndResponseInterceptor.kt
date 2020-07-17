package com.vons.mvvm.net

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http.HttpHeaders
import java.net.SocketTimeoutException
import java.nio.charset.Charset

class RequestAndResponseInterceptor(private val context: Context) : Interceptor {
    private val TAG = "MVVM"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val start = System.currentTimeMillis()
        try {
            val response = chain.proceed(request)
            val end = System.currentTimeMillis()
            val clone = response.newBuilder().build()
            var body = clone.body() ?: return response
//            val bytes = IOUtils.toByteArray(body.byteStream())
            val bytes = body.bytes()
//            body.byteStream()
            val contentType = body.contentType()
            val charset = contentType?.charset(Charset.defaultCharset()) ?: Charset.defaultCharset()
            if (HttpHeaders.hasBody(clone)) {
                Log.i(
                    TAG,
                    """                     [${request.url()}]-[${clone.code()}]-[${clone.message()}]
                     耗时[${(end - start)}]毫秒
                     响应结果=[${
                    String(
                        bytes, charset
                    )}]""".trimIndent()
                )
            } else {
                Log.i(
                    TAG,
                    """                     [${request.url()}]-[${clone.code()}]-[${clone.message()}]
                     耗时[${(end - start)}]毫秒
                    """.trimIndent()
                )
            }
            body = ResponseBody.create(body.contentType(), bytes)
            return response.newBuilder().body(body).build()
        } catch (e: SocketTimeoutException) {
            Toast.makeText(context, "网络繁忙,请稍后再试!", Toast.LENGTH_SHORT).show()
        }
        return Response.Builder().build()
    }
}