package com.example.starterproject.data

import com.example.starterproject.di.AppModule
import javax.inject.Inject

interface Repository {
  suspend fun fetchProducts(): List<ProductResponse>
}

class RepositoryImpl() : Repository {
  override suspend fun fetchProducts(): List<ProductResponse> {
    return emptyList()
  }
}