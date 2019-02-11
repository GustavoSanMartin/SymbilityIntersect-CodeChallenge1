package ca.gustavo.cryptocharts.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GetCollectionService {

    @GET("data/all/coinlist") //documentation recommended to use this api call
    fun getCriptocurrencies(): Single<Currencies>

    @GET("data/price")
    fun getPrice(@Query("fsym") fsym: String, @Query("tsyms") tsyms: String = "CAD"): Single<Price>
}