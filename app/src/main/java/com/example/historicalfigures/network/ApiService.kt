package com.example.historicalfigures.network

import com.example.historicalfigures.model.HistoricalFigure
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("X-Api-Key: whLzgxyCK7uGDuvzaGkPyQ==CQ9FFVL121FYB4Zv")
    @GET("historicalfigures")
    suspend fun getFigures(@Query("name") name: String): Response<List<HistoricalFigure>>
}