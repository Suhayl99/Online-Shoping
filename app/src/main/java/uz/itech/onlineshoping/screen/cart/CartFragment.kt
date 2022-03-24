package uz.itech.onlineshoping.screen.cart

import android.content.Intent
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
import uz.itech.onlineshoping.databinding.FragmentCartBinding
import uz.itech.onlineshoping.databinding.FragmentHomeBinding
import uz.itech.onlineshoping.model.ProductModel
import uz.itech.onlineshoping.screen.MainViewModel
import uz.itech.onlineshoping.screen.makeorder.MakeOrderActivity
import uz.itech.onlineshoping.utils.Constans
import uz.itech.onlineshoping.utils.PrefUtils
import uz.itech.onlineshoping.view.CartAdapter
import java.io.Serializable

class CartFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.error.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing=it
        })
        viewModel.productData.observe(this, Observer {
            binding.recyclerProduct.adapter=CartAdapter(it)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerProduct.layoutManager=LinearLayoutManager(requireContext())
        binding.swipe.setOnRefreshListener {
            loadData()
        }
        binding.btnMakeOrder.setOnClickListener {
            val intent=Intent(requireContext(), MakeOrderActivity::class.java)
            intent.putExtra(Constans.EXTA_DATA,(viewModel.productData.value?: emptyList<ProductModel>()) as Serializable)
            startActivity(intent)
        }

        loadData()
    }

    fun loadData() {

        viewModel.getProductsByIds( PrefUtils.getCartList().map { it.product_id })
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}