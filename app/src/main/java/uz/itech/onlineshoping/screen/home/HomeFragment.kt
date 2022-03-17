package uz.itech.onlineshoping.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    val images = arrayOf(R.drawable.rek, R.drawable.rek2, R.drawable.rek3)
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

        binding.carouselView.pageCount=images.count()
        binding.carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(images[position])


        }
    }



    companion object {
       @JvmStatic fun newInstance() = HomeFragment()
    }
}