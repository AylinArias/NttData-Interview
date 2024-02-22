package com.example.nttdata_interview

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val product = intent.getSerializableExtra("product") as Product

        // vistas en el diseño mediante sus identificadores
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val imageView: ImageView = findViewById(R.id.imageView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val backButton: Button = findViewById(R.id.backButton)

        // texto del titulo
        titleTextView.text = product.title

        // Utiliza la biblioteca Glide para cargar la imagen del producto en el ImageView
        Glide.with(this)
            .load(product.thumbnail)
            .error(R.drawable.error)  // Muestra esta imagen en caso de error al cargar la imagen principal
            .into(imageView)

        // texto de la descripción
        descriptionTextView.text = product.description

        backButton.setOnClickListener {
            finish()
        }
    }
}
