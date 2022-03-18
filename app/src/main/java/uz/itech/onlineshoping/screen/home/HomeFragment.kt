package uz.itech.onlineshoping.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.itech.onlineshop.view.CategoryAdapter
import uz.itech.onlineshop.view.ProductAdapter
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.api.Api
import uz.itech.onlineshoping.databinding.FragmentHomeBinding
import uz.itech.onlineshoping.model.BaseResponse
import uz.itech.onlineshoping.model.CategoryModel
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.model.ProductModel
import uz.itech.onlineshoping.screen.MainViewModel

class HomeFragment : Fragment() {
    lateinit var viewModel:MainViewModel
    var offers:List<OfferModel> = emptyList()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCategories.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerProduct.layoutManager=LinearLayoutManager(requireContext())

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.offersData.observe(requireActivity(), Observer {
            binding.carouselView.setImageListener { position, imageView ->
                Glide.with(imageView).load("http://osonsavdo.devapp.uz/images/${it[position].image}").into(imageView)
            }
            binding.carouselView.pageCount= it.count()
        })

        viewModel.categoriesData.observe(requireActivity(), Observer {
            binding.recyclerCategories.adapter= CategoryAdapter(it)
        })

        viewModel.productData.observe(requireActivity(), Observer {
            binding.recyclerProduct.adapter=ProductAdapter(it)
        })
            loadData()
    }

   fun loadData() {
      viewModel.getOffers()
       viewModel.getCategors()
       viewModel.getTopProducts()
    }


    companion object {
       @JvmStatic fun newInstance() = HomeFragment()
    }
}