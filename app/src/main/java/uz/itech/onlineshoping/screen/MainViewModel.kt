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
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel


class MainViewModel: ViewModel() {
    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://osonsavdo.devapp.uz/api/").build()
    val api= retrofit!!.create(Api::class.java)

    val error= MutableLiveData<String>()
    val offersData= MutableLiveData<List<OfferModel>>()
    val categoriesData= MutableLiveData<List<CategoryModel>>()
    val productData= MutableLiveData<List<ProductModel>>()

    fun getOffers(){
        api.getOffers().enqueue(object : Callback<BaseResponse<List<OfferModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<OfferModel>>>,
                response: Response<BaseResponse<List<OfferModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                    offersData.value= response.body()!!.data!!
                }else{
                    error.value= response.body()?.message
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                    error.value=t.localizedMessage
            }
        })
    }
    fun getCategors() {
        api.getCategories().enqueue(object : Callback<BaseResponse<List<CategoryModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<CategoryModel>>>,
                response: Response<BaseResponse<List<CategoryModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                  categoriesData.value= response.body()?.data?: emptyList()
                }else{
                    error.value= response.body()?.message
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
               error.value= t.localizedMessage
            }
        })
    }

    fun getTopProducts() {
        api.getTopProducts().enqueue(object : Callback<BaseResponse<List<ProductModel>>>{

            override fun onResponse(
                call: Call<BaseResponse<List<ProductModel>>>,
                response: Response<BaseResponse<List<ProductModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                   productData.value=response.body()?.data?: emptyList()
                }else{
                   error.value= response.body()?.message
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<ProductModel>>>, t: Throwable) {
               error.value = t.localizedMessage
            }
        })
    }
}