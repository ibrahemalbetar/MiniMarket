package com.ibrahimalbitar.minimarket.vvm.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.ibrahimalbitar.minimarket.databinding.ActivityMainBinding
import com.ibrahimalbitar.minimarket.listener.ProductItemActionsListener
import com.ibrahimalbitar.minimarket.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ProductItemActionsListener {
    private val viewModel: MainViewModel by viewModel()
    private val adapter = ProductsAdapter(this)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapter

        viewModel.productsList.observe(this@MainActivity) { result ->
            result.data?.let { adapter.setMovies(it) }
            contentLayout()
            val isEmptyList = result.data.isNullOrEmpty()
            when(result){
                is Resource.Loading<*> ->{
                    if(isEmptyList){
                        loadingLayout()
                    }
                }
                is Resource.Error<*>->{
                    if(isEmptyList){
                        noContentLayout()
                        Toast.makeText(this, result.error?.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
                else -> {
                    contentLayout()
                }
            }

          /*  if (result is Resource.Loading<*> && result.data.isNullOrEmpty() ) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
            if (result.data?.isEmpty() == false){
                binding.noContent.visibility = View.GONE
            }else{
                binding.noContent.visibility = View.VISIBLE
            }
            if (result is Resource.Error<*> && result.data.isNullOrEmpty()){
                binding.noContent.visibility = View.VISIBLE
                Toast.makeText(this, result.error?.localizedMessage, Toast.LENGTH_LONG).show()
            }*/
        }
    }

    fun loadingLayout(){
        binding.progressDialog.visibility = View.VISIBLE
        binding.noContent.visibility = View.GONE
    }

    fun noContentLayout(){
        binding.progressDialog.visibility = View.GONE
        binding.noContent.visibility = View.VISIBLE
    }

    fun contentLayout(){
        binding.progressDialog.visibility = View.GONE
        binding.noContent.visibility = View.GONE
    }




    override fun shareProduct(appId: String, productUrl: String) {
        viewModel.shareProduct(this,appId, productUrl)
    }
}