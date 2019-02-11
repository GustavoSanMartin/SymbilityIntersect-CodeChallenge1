package ca.gustavo.cryptocharts.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("Symbol")
    @Expose
    var symbol: String = ""

    @SerializedName("CoinName")
    @Expose
    var coinName: String = ""

    var price: Double = -1.0
}