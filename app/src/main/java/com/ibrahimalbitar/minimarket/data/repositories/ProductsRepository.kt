package com.ibrahimalbitar.minimarket.data.repositories

import androidx.room.withTransaction
import com.ibrahimalbitar.minimarket.data.dao.ProductsDao
import com.ibrahimalbitar.minimarket.data.db.AppDatabase
import com.ibrahimalbitar.minimarket.data.services.ApiService
import com.ibrahimalbitar.minimarket.utils.dataSourceSelector

class ProductsRepository constructor(private val apiServices: ApiService, private val database: AppDatabase, private val dao : ProductsDao) {

    fun getAllProducts() = dataSourceSelector(
        query = {
            dao.getAllProducts()
        },
        fetch = {
            apiServices.getAllProducts()
        },
        saveFetchResult = { product ->
            database.withTransaction {
                dao.deleteAllProducts()
                product.results?.let { dao.insertProducts(it) }
            }
        }
    )
}