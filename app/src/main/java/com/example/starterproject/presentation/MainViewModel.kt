package com.example.starterproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starterproject.data.ProductResponse
import com.example.starterproject.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
  private val productRepository: Repository
) : ViewModel() {

  private var _dataProduct = MutableSharedFlow<List<ProductResponse>>()
  var dataProduct = _dataProduct.asSharedFlow()

  fun fetchDataProduct() {
    viewModelScope.launch {
      productRepository.fetchProducts().let {
        _dataProduct.emit(dummyProductList())
      }
    }
  }

  fun dummyProductList(): List<ProductResponse> {
    return listOf(
      ProductResponse(
        id = 1,
        name = "Stylish Leather Handbag",
        sortDescription = "Elegant leather handbag with a classic design. The perfect accessory for any occasion.",
        price = 1200000.0,
        rating = 4.0,
        discount = 10,
        images = "https://raw.githubusercontent.com/iwrrr/iwrrr/main/images/Stylish%20Leather%20Handbag/img-0.jpeg"
      ), ProductResponse(
        id = 2,
        name = "Smartphone Stand and Charger",
        sortDescription = "Convenient smartphone stand with a built-in charger. Keep your phone charged and accessible at all times.",
        price = 450000.0,
        rating = 4.5,
        discount = 15,
        images = "https://raw.githubusercontent.com/iwrrr/iwrrr/main/images/Smartphone%20Stand%20and%20Charger/img-0.jpeg"
      ), ProductResponse(
        id = 3,
        name = "Designer Sunglasses",
        sortDescription = "Designer sunglasses with UV protection. Stay stylish while protecting your eyes from the sun.",
        price = 750000.0,
        rating = 4.0,
        discount = 20,
        images = "https://raw.githubusercontent.com/iwrrr/iwrrr/main/images/Designer%20Sunglasses/img-0.jpeg"
      )
    )
  }
}