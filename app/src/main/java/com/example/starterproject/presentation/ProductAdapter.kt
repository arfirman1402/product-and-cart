package com.example.starterproject.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starterproject.R
import com.example.starterproject.data.ProductResponse
import com.example.starterproject.databinding.ItemProductBinding

class ProductAdapter(val onProductUpdated: (ProductResponse, Int) -> Unit) :
  ListAdapter<ProductResponse, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
    val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ProductViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }

  inner class ProductViewHolder(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ProductResponse) {
      binding.apply {
        var cartCount = 0
        tvProductName.text = data.name
        tvProductDesc.text = data.sortDescription
        tvProductPrice.text = "Price = ${data.price}"
        etProductCount.setText(cartCount.toString())
        ivProductImage.setImageResource(R.drawable.ic_launcher_foreground)
        btnProductCartPlus.setOnClickListener {
          Log.i("ProductAdapter", "bind: Button Plus Clicked")
          cartCount++
          etProductCount.setText(cartCount.toString())
          onProductUpdated.invoke(data, cartCount)
        }
        btnProductCartMinus.setOnClickListener {
          Log.i("ProductAdapter", "bind: Button Minus Clicked")
          if (cartCount > 0) {
            cartCount--
            etProductCount.setText(cartCount.toString())
            onProductUpdated.invoke(data, cartCount)
          }
        }
      }
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductResponse>() {
      override fun areItemsTheSame(
        oldItem: ProductResponse, newItem: ProductResponse
      ): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
        oldItem: ProductResponse, newItem: ProductResponse
      ): Boolean {
        return oldItem == newItem
      }
    }
  }
}