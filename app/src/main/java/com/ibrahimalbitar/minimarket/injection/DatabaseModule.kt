package com.ibrahimalbitar.minimarket.injection

import android.annotation.SuppressLint
import androidx.room.Room
import com.ibrahimalbitar.minimarket.data.db.AppDatabase
import com.ibrahimalbitar.minimarket.data.repositories.ProductsRepository
import com.ibrahimalbitar.minimarket.data.services.ApiService
import com.ibrahimalbitar.minimarket.data.services.RetrofitClientService
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

object DatabaseModule : () -> Module {
    override fun invoke(): Module= module  {
        single {
            Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
        }
        single { get<AppDatabase>().dao() }
        single { RetrofitClientService.createService(ApiService::class.java) }
        factory { ProductsRepository(get(), get(), get())}


    }
}