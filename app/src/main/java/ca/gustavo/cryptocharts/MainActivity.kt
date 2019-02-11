package ca.gustavo.cryptocharts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.gustavo.cryptocharts.retrofit.Data
import ca.gustavo.cryptocharts.retrofit.Price
import ca.gustavo.cryptocharts.retrofit.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CryptosAdapter

    private val disposables = CompositeDisposable()
    private val cryptoMap: MutableMap<String, Data> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.pb_spinner)
        recyclerView = findViewById(R.id.rv_cryptos)

        adapter = CryptosAdapter(cryptoMap)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getCryptocurrencies()
    }

    private fun getCryptocurrencies() {
        RetrofitInstance.getCryptos()
            .map { it.data }
            .toObservable()
            .doOnNext(cryptoMap::putAll)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(adapter::setData)
            .doOnNext { spinner.visibility = View.GONE }
            .observeOn(Schedulers.io())
            .flatMapIterable { it.values }
            .getPrice()
            .subscribeOn(Schedulers.io())
            .subscribe({
                cryptoMap[it.symbol] = it
                runOnUiThread { adapter.notifyDataSetChanged() }
            }, { Log.d("Network Error", it.message) })
            .let(disposables::add)
    }

    private fun Observable<Data>.getPrice() = flatMap(
        { RetrofitInstance.getPrices(it.symbol).toObservable() },
        { data: Data, price: Price ->
            data.price = price.cad
            return@flatMap data
        }
    )

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }
}
