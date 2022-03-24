package uz.itech.onlineshoping.screen.makeorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.ActivityMakeOrderBinding
import uz.itech.onlineshoping.databinding.FragmentHomeBinding

class MakeOrderActivity : AppCompatActivity() {
    private var _binding: ActivityMakeOrderBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMakeOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}