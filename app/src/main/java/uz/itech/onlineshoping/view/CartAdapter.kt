package uz.itech.onlineshoping.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.CartItemLayoutBinding
import uz.itech.onlineshoping.model.ProductModel
import uz.itech.onlineshoping.utils.Constans
import uz.itech.onlineshoping.utils.PrefUtils

class CartAdapter(val items:List<ProductModel>):RecyclerView.Adapter<CartAdapter.ItemHolder>() {
    class ItemHolder(val binding: CartItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding=CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        holder.binding.tvPrice.text=item.price
        holder.binding.tvName.text=item.name
        Glide.with(holder.itemView).load(Constans.HOST_IMAGE+item.image).into(holder.binding.imgProduct)
        holder.binding.tvCount.text=item.cartCount.toString()
        holder.binding.imgminus.setOnClickListener {
            if (item.cartCount>0) {
                item.cartCount--
                holder.binding.tvCount.text = item.cartCount.toString()
                PrefUtils.setCart(item)
            }
        }
        holder.binding.imgplus.setOnClickListener {
            if (item.cartCount>=0) {
                item.cartCount++
                holder.binding.tvCount.text = item.cartCount.toString()
                PrefUtils.setCart(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}