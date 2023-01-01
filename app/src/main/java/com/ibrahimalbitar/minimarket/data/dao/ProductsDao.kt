package com.ibrahimalbitar.minimarket.data.dao

import androidx.room.*
import androidx.room.Dao
import com.ibrahimalbitar.minimarket.data.model.ProductsResp.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie :Product)

    @Update
    suspend fun update(movie: Product)

    @Delete
    suspend fun delete(movie: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(movies: List<Product>)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("Select * from products order by id ASC")
    fun getAllProducts(): Flow<List<Product>>
}