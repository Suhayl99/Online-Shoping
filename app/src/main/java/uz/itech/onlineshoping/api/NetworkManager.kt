package uz.itech.onlineshoping.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.itech.onlineshoping.utils.Constans

object NetworkManager {
    var retrofit: Retrofit?=null
    var api :Api? = null

    fun getApiService(): Api? {
        if (api == null) {
            retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constans.BASE_URL).build()
            api = retrofit!!.create(Api::class.java)
        }
        return api!!
    }
}