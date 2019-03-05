package wit.org.favouriteshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.activity_shop_list.*
import kotlinx.android.synthetic.main.card_shop.view.*
import org.jetbrains.anko.startActivityForResult
import wit.org.favouriteshop.R
import wit.org.favouriteshop.main.MainApp
import wit.org.favouriteshop.models.FavouriteshopModel

class ShopListActivity: AppCompatActivity() {
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        app = application as MainApp
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ShopAdapter(app.shops)

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }
}

class ShopAdapter constructor(private var shops: List<FavouriteshopModel>) : RecyclerView.Adapter<ShopAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_shop, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val shop = shops[holder.adapterPosition]
        holder.bind(shop)
    }

    override fun getItemCount(): Int = shops.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(shop: FavouriteshopModel) {
            itemView.shopTitle.text = shop.title
            itemView.description.text = shop.description
        }
    }
}