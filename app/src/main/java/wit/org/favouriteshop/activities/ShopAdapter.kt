package wit.org.favouriteshop.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_shop.view.*
import wit.org.favouriteshop.R
import wit.org.favouriteshop.models.FavouriteshopModel

interface ShopListener {
    fun onShopClick(shop: FavouriteshopModel)
}

class ShopAdapter constructor(private var shops: List<FavouriteshopModel>, private val listener: ShopListener) : RecyclerView.Adapter<ShopAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_shop, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val shop = shops[holder.adapterPosition]
        holder.bind(shop, listener)
    }

    override fun getItemCount(): Int = shops.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(shop: FavouriteshopModel, listener: ShopListener) {
            itemView.shopTitle.text = shop.title
            itemView.description.text = shop.description
            itemView.setOnClickListener { listener.onShopClick(shop) }
        }
    }
}