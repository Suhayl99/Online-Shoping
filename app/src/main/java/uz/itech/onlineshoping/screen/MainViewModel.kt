package uz.itech.onlineshoping.screen

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.itech.onlineshop.view.CategoryAdapter
import uz.itech.onlineshop.view.ProductAdapter
import uz.itech.onlineshoping.api.Api
import uz.itech.onlineshoping.api.NetworkManager
import uz.itech.onlineshoping.api.repository.ShopRepository
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel
import uz.itech.onlineshoping.utils.Constans


class MainViewModel: ViewModel() {
    val repository = ShopRepository()
    val error= MutableLiveData<String>()
    val offersData=MutableLiveData<List<OfferModel>>()
    val categoriesData=MutableLiveData<List<CategoryModel>>()
    val productData=MutableLiveData<List<ProductModel>>()
    var progress= MutableLiveData<Boolean>()
    fun getOffers(){
        repository.getOffers(error, progress, offersData)
    }
    fun getCategors() {
        repository.getCategors(error,categoriesData)
    }

    fun getTopProducts() {
        repository.getTopProducts(error,productData)
    }

    fun getProductsByCategory(id:Int){
        repository.getProductsByCategory(id,error,productData)
    }
}