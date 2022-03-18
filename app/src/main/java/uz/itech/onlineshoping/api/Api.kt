package uz.itech.onlineshoping.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel
import java.util.*

interface Api {

    @GET("get_offers")
    fun getOffers(): Call<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Call<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts(): Call<BaseResponse<List<ProductModel>>>
}