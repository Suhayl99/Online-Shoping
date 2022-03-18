package uz.itech.onlineshoping.api.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.itech.onlineshoping.api.NetworkManager
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel

class ShopRepository {

    fun getOffers(error:MutableLiveData<String>, progress: MutableLiveData<Boolean> , success:MutableLiveData<List<OfferModel>>){
        progress.value=true
        NetworkManager.getApiService()!!.getOffers().enqueue(object :
            Callback<BaseResponse<List<OfferModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<OfferModel>>>,
                response: Response<BaseResponse<List<OfferModel>>>
            ) {
                progress.value=false
                if (response.isSuccessful && response.body()!!.success){
                    success.value= response.body()!!.data
                }else{
                    error.value= response.body()?.message
                }

            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                error.value=t.localizedMessage
                progress.value=false
            }
        })
    }

    fun getCategors(error:MutableLiveData<String>, success:MutableLiveData<List<CategoryModel>>) {
        NetworkManager.getApiService()!!.getCategories().enqueue(object :
            Callback<BaseResponse<List<CategoryModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CategoryModel>>>,
                response: Response<BaseResponse<List<CategoryModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                    success.value= response.body()?.data?: emptyList()
                }else{
                    error.value= response.body()?.message
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
                error.value= t.localizedMessage
            }
        })
    }

    fun getTopProducts(error:MutableLiveData<String>, success:MutableLiveData<List<ProductModel>>) {
        NetworkManager.getApiService()!!.getTopProducts().enqueue(object :
            Callback<BaseResponse<List<ProductModel>>> {

            override fun onResponse(
                call: Call<BaseResponse<List<ProductModel>>>,
                response: Response<BaseResponse<List<ProductModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                    success.value=response.body()?.data?: emptyList()
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