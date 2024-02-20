package com.example.starterproject.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starterproject.data.RepositoryImpl
import com.example.starterproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private var viewModel: MainViewModel = MainViewModel(RepositoryImpl())

  private lateinit var binding: ActivityMainBinding

  private var adapter: ProductAdapter = ProductAdapter(onProductUpdated = { product, cartTotal ->
    Toast.makeText(this, "Cart Updated. ${product.name} Total $cartTotal", Toast.LENGTH_SHORT)
      .show()
  })

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setProductList()
    setupObservers()
  }

  private fun setupObservers() {

  }

  private fun setProductList() = with(binding) {
    rvProducts.adapter = adapter
    rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)

    val responses = viewModel.dummyProductList()
    adapter.submitList(responses)
  }
}