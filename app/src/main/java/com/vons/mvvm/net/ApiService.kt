package com.vons.mvvm.net

import com.vons.mvvm.entity.BaseResult
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("/article/list/{page}/json")
    suspend fun homePage(@Path("page") page: Int = 0): BaseResult

    @GET(value = "/banner/json")
    suspend fun banner(): BaseResult

    @GET("/project/tree/json")
    suspend fun projectCategory(): BaseResult

    @GET("/project/list/{page}/json")
    suspend fun getProjectContent(@Path("page") page: Int = 1, @Query("cid") cid: Int): BaseResult

    @GET("/tree/json")
    suspend fun systemData(): BaseResult

    @GET("/navi/json")
    suspend fun navigationData(): BaseResult

    @GET("/wxarticle/chapters/json")
    suspend fun officialAccount(): BaseResult

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun officialDataById(@Path("id") id: Int, @Path("page") page: Int): BaseResult

    @POST("/user/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): BaseResult

}