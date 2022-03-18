package uz.itech.onlineshoping.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel
import java.util.*

interface Api {

    @GET("get_offers")
    fun getOffers(): Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories():Observable<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts():Observable<BaseResponse<List<ProductModel>>>


    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId:Int):Observable<BaseResponse<List<ProductModel>>>

}