package ca.gustavo.cryptocharts.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Currencies {

    @SerializedName("Message")
    @Expose
    var message: String = ""

    @SerializedName("Data")
    @Expose
    var data: Map<String, Data> = emptyMap()

}