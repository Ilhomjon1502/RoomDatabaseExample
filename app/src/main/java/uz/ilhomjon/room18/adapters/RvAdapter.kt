package uz.ilhomjon.room18.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ilhomjon.room18.databinding.ItemRvBinding
import uz.ilhomjon.room18.models.User

class RvAdapter(var list: ArrayList<User>, var rvAction: RvAction) : Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : ViewHolder(itemRvBinding.root){
        fun onBind(user: User, position: Int){
            itemRvBinding.tvName.text = user.name
            itemRvBinding.tvNumber.text = user.number

            itemRvBinding.btnMore.setOnClickListener {
                rvAction.moreClick(user, position, itemRvBinding.btnMore)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface RvAction{
        fun moreClick(user: User, position: Int, imageView: ImageView)
    }
}