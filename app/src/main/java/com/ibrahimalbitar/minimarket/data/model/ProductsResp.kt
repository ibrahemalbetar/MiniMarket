package com.ibrahimalbitar.minimarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductsResp(
    @SerializedName("results")
    val results: List<Product>? = null
) : Parcelable {
    @Parcelize
    @Entity(tableName = "products")
    data class Product(@ColumnInfo(name = "name")val name: String, @ColumnInfo(name = "image_urls") val imageUrls: List<String>,
                   @ColumnInfo(name = "price") val price: String, @ColumnInfo(name = "image_urls_thumbnails") val image_urls_thumbnails: List<String> )
        :Parcelable{

        @PrimaryKey(autoGenerate = true)
        var id = 0
    }
}