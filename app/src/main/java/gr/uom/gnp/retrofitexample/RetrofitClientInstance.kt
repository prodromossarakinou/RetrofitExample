package gr.uom.gnp.retrofitexample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory





class RetrofitClientInstance {
    companion object Factory {
        var retrofit: Retrofit? = null
        val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
    }
}