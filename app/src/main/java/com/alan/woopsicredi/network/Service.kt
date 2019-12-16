package com.alan.woopsicredi.network

import com.alan.woopsicredi.models.ObjectEvent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("events")
    fun getListEvent() : Call<List<ObjectEvent>>

    @GET("events/{id}")
    fun getDetailsEvent(@Path("id") id : String) : Call<ObjectEvent>

    @POST("checkin")
    fun setCheckin(@Query("eventId") id : String,
                   @Query("name") name : String,
                   @Query("email") email : String) : Call<Void>
}