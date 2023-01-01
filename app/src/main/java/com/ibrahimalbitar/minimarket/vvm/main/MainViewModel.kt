package com.ibrahimalbitar.minimarket.vvm.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.ibrahimalbitar.minimarket.data.repositories.ProductsRepository


const val  FacebookAppID = "com.facebook.katana"
const val  InstagramAppID =  "com.instagram.android"
class MainViewModel constructor(mainRepository: ProductsRepository) : ViewModel() {

    val productsList = mainRepository.getAllProducts().asLiveData()

    fun shareProduct(context : Context, application : String, productUrl: String){
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, productUrl)


        val installed: Boolean = checkAppInstall(context, application)
        if (installed) {
            intent.setPackage(application)
            startActivity(context, intent, null)
        } else {
            Toast.makeText(
                context,
                "Installed application first", Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun checkAppInstall(context : Context, uri: String): Boolean {
        val pm: PackageManager = context.packageManager
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
        }
        return false
    }

}