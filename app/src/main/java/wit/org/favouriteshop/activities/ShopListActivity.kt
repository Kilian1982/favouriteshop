package wit.org.favouriteshop.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_shop_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import wit.org.favouriteshop.R
import wit.org.favouriteshop.main.MainApp
import wit.org.favouriteshop.models.FavouriteshopModel

class ShopListActivity: AppCompatActivity(), ShopListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadShops()

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }
    override fun onShopClick(shop: FavouriteshopModel) {
        startActivityForResult(intentFor<MainActivity>().putExtra("shop_edit", shop), 0)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(200)
            R.id.item_map -> startActivity<ShopMapsActivity>()
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadShops()
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun loadShops(){
        showShops(app.shops.findAll())
    }
    fun showShops(shops: List<FavouriteshopModel>){
        recyclerView.adapter = ShopAdapter(shops, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

