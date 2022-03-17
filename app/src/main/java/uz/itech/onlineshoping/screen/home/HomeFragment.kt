package uz.itech.onlineshoping.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.api.Api
import uz.itech.onlineshoping.databinding.FragmentHomeBinding
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.OfferModel

class HomeFragment : Fragment() {
    var offers:List<OfferModel> = emptyList()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://osonsavdo.devapp.uz/api/").build()

        val api= retrofit!!.create(Api::class.java)
        api.getOffers().enqueue(object : Callback<BaseResponse<List<OfferModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<OfferModel>>>,
                response: Response<BaseResponse<List<OfferModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success){
                    offers= response.body()!!.data
                    binding.carouselView.setImageListener { position, imageView ->
                    Glide.with(imageView).load("http://osonsavdo.devapp.uz/images/${offers[position].image}").into(imageView)
                    }
                    binding.carouselView.pageCount=offers.count()
                }else{
                    Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })


    }



    companion object {
       @JvmStatic fun newInstance() = HomeFragment()
    }
}