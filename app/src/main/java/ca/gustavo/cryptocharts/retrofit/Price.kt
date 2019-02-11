package ca.gustavo.cryptocharts.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Price {

    @SerializedName("CAD")
    @Expose
    var cad: Double = -1.0

}