package ca.gustavo.cryptocharts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.gustavo.cryptocharts.retrofit.Data

class CryptosAdapter(cryptoMap: Map<String, Data>) : RecyclerView.Adapter<CryptosAdapter.CryptosViewHolder>() {
    private var cryptoList = cryptoMap.values.toList()

    fun setData(cryptoMap: Map<String, Data>) {
        cryptoList = cryptoMap.values.toList()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptosAdapter.CryptosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_crypto, parent, false)

        return CryptosViewHolder(view)
    }

    override fun getItemCount() = cryptoList.size

    override fun onBindViewHolder(holder: CryptosAdapter.CryptosViewHolder, pos: Int) {
        holder.name.text = cryptoList[pos].coinName

        val price = if (cryptoList[pos].price == -1.0) "" else "$${"%.2f".format(cryptoList[pos].price)} CAD"
        holder.price.text = price
    }

    class CryptosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        val price: TextView = view.findViewById(R.id.tv_price)
    }
}