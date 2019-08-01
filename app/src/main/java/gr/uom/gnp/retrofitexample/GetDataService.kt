package gr.uom.gnp.retrofitexample

import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {

    @GET("/photos")
    fun getAllPhotos(): Call<List<RetroPhoto>>

}