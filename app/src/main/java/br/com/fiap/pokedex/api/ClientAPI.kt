package br.com.fiap.pokedex.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientApi<T> {

    fun getClient(c: Class<T>): T {
        val builder = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
                .client(builder.build())
                .baseUrl("https://pokedexdx.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(c)
    }

}

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val requestBuilder = chain!!.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Basic cG9rZWFwaTpwb2tlbW9u")
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY")
        }
        return response
    }
}

fun getPokemonApi(): PokemonAPI {
    return ClientApi<PokemonAPI>().getClient(PokemonAPI::class.java)
}
