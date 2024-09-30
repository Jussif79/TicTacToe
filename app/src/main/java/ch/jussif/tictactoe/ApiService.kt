package ch.jussif.tictactoe

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

const val Base_URL = "http://javaprojects.ch:5005"

// End Points
interface ApiService {
    @GET("ping")
    suspend fun ping(): String

    @Headers("Content-Type: application/json")
    @POST("users/register")
    suspend fun register(@Body data: String): String

    @Headers("Content-Type: application/json")
    @POST("users/login")
    suspend fun login(@Body data: String): String

    @Headers("Content-Type: application/json")
    @POST("user/new")
    suspend fun newGame(@Body data: String): String

    // Access resources on the server
    object ApiObject {
        val retrofit =
            Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(Base_URL)
                .build()
        val retrofitService : ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}