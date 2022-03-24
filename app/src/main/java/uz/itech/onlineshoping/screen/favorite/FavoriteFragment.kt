package uz.itech.onlineshoping.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.itech.onlineshop.view.ProductAdapter
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.FragmentFavoriteBinding
import uz.itech.onlineshoping.databinding.FragmentHomeBinding
import uz.itech.onlineshoping.model.OfferModel
import uz.itech.onlineshoping.screen.MainViewModel
import uz.itech.onlineshoping.utils.PrefUtils

class FavoriteFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.productData.observe(this, Observer {
            binding.recyclerProduct.adapter= ProductAdapter(it)
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing=it
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerProduct.layoutManager= LinearLayoutManager(requireContext())
        loadData()
        binding.swipe.setOnRefreshListener {
            loadData()
        }
    }


    fun loadData() {
        viewModel.getProductsByIds(PrefUtils.getFavoriteList())
    }


    companion object {
         @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}