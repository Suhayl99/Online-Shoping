package uz.itech.onlineshoping.screen.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.ActivityProductDetailBinding
import uz.itech.onlineshoping.model.ProductModel
import uz.itech.onlineshoping.utils.Constans
import uz.itech.onlineshoping.utils.PrefUtils

class ProductDetailActivity : AppCompatActivity() {
    lateinit var item :ProductModel
    lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cardViewBack.setOnClickListener {
            finish()
        }
        binding.cardViewFavorite.setOnClickListener {
           PrefUtils.setFavorite(item)
            if (PrefUtils.checkFavorite(item)){
                binding.imageFavorite.setImageResource(R.drawable.ic_heart)
            }else{
                binding.imageFavorite.setImageResource(R.drawable.ic__favorite)
            }
        }
        item=intent.getSerializableExtra(Constans.EXTA_DATA) as ProductModel

        binding.tvTitle.text=item.name
        binding.tvProductName.text=item.name
        binding.tvProductPrice.text=item.price
        binding.tvProductComment.text= item.name+" -- "+item.price
        if (PrefUtils.getCartCount(item)>0){
            binding.Add2Cart.visibility= View.GONE
        }
        Glide.with(this).load(Constans.HOST_IMAGE+item.image).into(binding.imageProduct)
        if (PrefUtils.checkFavorite(item)){
            binding.imageFavorite.setImageResource(R.drawable.ic_heart)
        }else{
            binding.imageFavorite.setImageResource(R.drawable.ic__favorite)
        }

        binding.Add2Cart.setOnClickListener {
            PrefUtils.setCart(item)
            Toast.makeText(this,"Product added to cart!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}