package dev.ozkan.ratingapplication.app.home.recyclerviewadapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import dev.ozkan.ratingapplication.R
import dev.ozkan.ratingapplication.app.home.models.Products
import dev.ozkan.ratingapplication.app.home.product.ProductActivity
import dev.ozkan.ratingapplication.core.home.selected.SelectedItem


class ProductRecyclerViewAdapter(private val context : Context, private val productList: ArrayList<Products>) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.productImage.setImageBitmap(currentItem.productImage)
        holder.header.text = currentItem.header
    }


    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val productImage: ShapeableImageView = itemView.findViewById(R.id.product_image)
        val header: TextView = itemView.findViewById(R.id.product_header)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedItem = productList[position]
                SelectedItem.productId = clickedItem.id
                SelectedItem.header = clickedItem.header
                SelectedItem.productBitmap = clickedItem.productImage
                SelectedItem.rating = clickedItem.rating

                val intent = Intent(view.context,ProductActivity::class.java)
                context.startActivity(intent)
            }
        }


    }


}
