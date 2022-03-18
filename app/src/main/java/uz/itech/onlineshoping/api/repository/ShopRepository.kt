package uz.itech.onlineshoping.api.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.itech.onlineshoping.api.NetworkManager
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel

class ShopRepository {
    val compositeDisposable= CompositeDisposable()
    fun getOffers(error:MutableLiveData<String>, progress: MutableLiveData<Boolean> , success:MutableLiveData<List<OfferModel>>){
        progress.value=true
        compositeDisposable.add(NetworkManager.getApiService()!!.getOffers().subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<BaseResponse<List<OfferModel>>>(){
                override fun onNext(t: BaseResponse<List<OfferModel>>) {
                    progress.value=false
                    if (t.success){
                        success.value=t.data
                    }else{
                        error.value=t.message
                    }
                }

                override fun onError(e: Throwable) {
                    progress.value=false
                    error.value=e.localizedMessage
                }

                override fun onComplete() {

                }

            }))
    }

    fun getCategors(error:MutableLiveData<String>, success:MutableLiveData<List<CategoryModel>>) {
        compositeDisposable.add(NetworkManager.getApiService()!!.getCategories().subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>(){
                override fun onNext(t: BaseResponse<List<CategoryModel>>) {
                    if (t.success){
                        success.value=t.data
                    }else{
                        error.value=t.message
                    }
                }

                override fun onError(e: Throwable) {
                    error.value=e.localizedMessage
                }

                override fun onComplete() {

                }

            }))
    }

    fun getTopProducts(error:MutableLiveData<String>, success:MutableLiveData<List<ProductModel>>) {
        compositeDisposable.add(NetworkManager.getApiService()!!.getTopProducts()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>(){
                override fun onNext(t: BaseResponse<List<ProductModel>>) {
                    if (t.success){
                        success.value=t.data
                    }else{
                        error.value=t.message
                    }
                }

                override fun onError(e: Throwable) {
                    error.value=e.localizedMessage
                }

                override fun onComplete() {

                }

            }))
    }

}