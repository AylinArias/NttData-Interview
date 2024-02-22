package com.example.nttdata_interview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ProductAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        holder.titleTextView.text = product.title

        // Glide para cargar la imagen
        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .error(R.drawable.error)  // muestra esta imagen en caso de error al cargar la imagen principal
            .into(holder.imageView)

        // clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener { onItemClick(product) }
    }

    // devuelve la cantidad total de elementos en la lista
    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
