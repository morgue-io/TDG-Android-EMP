package com.thebobighat.emp

import retrofit2.Response
import retrofit2.http.*

interface TdgApi {

    @POST("login")
    suspend fun postCredentials(@Body credentials: LoginCredentials) : Response<LoginResponse>

    @GET("orders")
    suspend fun getNewTasks(@Header("Authorization") jwt: String) : Response<GetOrdersResponse>

    @GET("tasks")
    suspend fun getMyTasks(@Header("Authorization") jwt: String) : Response<GetTasksResponse>

    @POST("attendance")
    suspend fun postAttendance(@Header("Authorization") jwt: String) : Response<GenericResponse>

    @GET("service-history")
    suspend fun getServiceHistory(@Header("Authorization") jwt: String) : Response<GetServiceHistoryResponse>

    @POST("orders/self-assign/pick-up")
    suspend fun assignPickup(@Header("Authorization") jwt: String, @Query("id") orderId: String): Response<GenericResponse>

    @POST("orders/self-assign/delivery")
    suspend fun assignDelivery(@Header("Authorization") jwt: String, @Query("id") orderId: String): Response<GenericResponse>

    @POST("orders/self-assign/pick-up/done")
    suspend fun assignPickupDone(@Header("Authorization") jwt: String, @Query("id") orderId: String): Response<GenericResponse>

    @POST("orders/self-assign/delivery/done")
    suspend fun assignDeliveryDone(@Header("Authorization") jwt: String, @Query("id") orderId: String): Response<GenericResponse>
}