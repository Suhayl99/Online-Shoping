package uz.itech.onlineshoping.api

import retrofit2.Call
import retrofit2.http.GET
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.OfferModel
import java.util.*

interface Api {

    @GET("get_offers")
    fun getOffers(): Call<BaseResponse<List<OfferModel>>>

}