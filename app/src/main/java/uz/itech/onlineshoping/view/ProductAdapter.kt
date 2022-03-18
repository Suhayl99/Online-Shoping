package uz.itech.onlineshop.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.ProductItemLayoutBinding
import uz.itech.onlineshoping.model.ProductModel

class ProductAdapter(val items:List<ProductModel>):RecyclerView.Adapter<ProductAdapter.ItemHolder>() {
   inner class ItemHolder(val binding:ProductItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding=ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
//        holder.itemView.setOnClickListener {
//            val intent=Intent(it.context,ProductDetailActivity::class.java)
//            intent.putExtra(Constans.EXTA_DATA,item)
//            holder.itemView.context.startActivity(intent)
//        }
        Glide.with(holder.binding.imgProduct).load("http://osonsavdo.devapp.uz/images/${items[position].image}").into(holder.binding.imgProduct)
        holder.binding.tvName.text=item.name
        holder.binding.tvPrice.text=item.price
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}