package uz.itech.onlineshoping.screen.makeorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import uz.itech.onlineshoping.MapsActivity
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.ActivityMakeOrderBinding
import uz.itech.onlineshoping.databinding.FragmentHomeBinding
import uz.itech.onlineshoping.model.AddressModel
import uz.itech.onlineshoping.model.ProductModel
import uz.itech.onlineshoping.screen.MainActivity
import uz.itech.onlineshoping.utils.Constans
import uz.itech.onlineshoping.utils.LocaleManager

class MakeOrderActivity : AppCompatActivity() {
    private var _binding: ActivityMakeOrderBinding? = null
    private val binding get() = _binding!!
    var address: AddressModel?=null
    lateinit var items:List<ProductModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMakeOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnMakeOrder.setOnClickListener {
            Toast.makeText(this, "Buyurma qabul qilindi", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        items=intent.getSerializableExtra(Constans.EXTA_DATA) as List<ProductModel>

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this)
        }

        binding.tvTotalAmount.setText(items.sumOf { it.cartCount.toDouble()*(it.price.replace(" ","").toDoubleOrNull()?:0.0) }.toString())

        binding.edAddress.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(address:AddressModel){
        this.address=address
        binding.edAddress.setText("${address.latitude}, ${address.longitude}")
    }
    override fun attachBaseContext(newBase: Context?){
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}