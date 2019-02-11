package ca.gustavo.cryptocharts.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://min-api.cryptocompare.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val api = retrofit.create(GetCollectionService::class.java)

    fun getCryptos() = api.getCriptocurrencies()

    fun getPrices(cryptoID: String, currency: String = "CAD") = api.getPrice(cryptoID, currency)
}