package com.example.nttdata_interview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // se inicializa la vista del RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // retrofit para realizar la llamada de la API
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // se crea una instancia de la interfaz ApiService
        val apiService = retrofit.create(ApiService::class.java)

        // use coroutine para realizar la llamada asíncrona a la API y obtener la lista de productos
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                // obtener la respuesta de productos
                val productResponse = apiService.getProducts()

                // la lista de productos de la respuesta
                val products: List<Product> = productResponse.products

                //  adaptador de productos
                adapter = ProductAdapter(products) { product -> showDetailActivity(product) }

                // configuracion del adaptador en el recyclerView
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                // excepciones con la llamada a la API
                Log.e("Error fetching products", e.message ?: "Unknown error")
            }
        }
    }

    // metodo para mostrar los detalles al hacer clic en un producto
    private fun showDetailActivity(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}
