package uz.itech.onlineshop.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.CategoryItemLayoutBinding
import uz.itech.onlineshoping.model.CategoryModel

//interface CategoryAdapterCallback{
//    fun onClickItem(item: CategoryModel)
//}
class CategoryAdapter(var items:List<CategoryModel> /*,val callback:CategoryAdapterCallback */):RecyclerView.Adapter<CategoryAdapter.ItemHolder> (){
   inner class ItemHolder( val binding: CategoryItemLayoutBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       val binding =CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item=items[position]
        holder.binding.tvTitle.text=item.title
        holder.itemView.setOnClickListener {
            items.forEach {
                it.checked=false
            }
            item.checked=true
         //   callback.onClickItem(item)
            notifyDataSetChanged()
        }
        if (item.checked){
            holder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorPrimary))
            holder.binding.tvTitle.setTextColor(Color.WHITE)
        }else{
            holder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.gray))
            holder.binding.tvTitle.setTextColor(Color.BLACK)
        }

    }

    override fun getItemCount(): Int {
       return items.count()
    }
}