package wit.org.favouriteshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import wit.org.favouriteshop.R
import wit.org.favouriteshop.main.MainApp
import wit.org.favouriteshop.models.FavouriteshopModel

class MainActivity : AppCompatActivity(), AnkoLogger {

    var shop = FavouriteshopModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp
        if (intent.hasExtra("shop_edit")) {
            shop = intent.extras.getParcelable<FavouriteshopModel>("shop_edit")
            shopTitle.setText(shop.title)
            description.setText(shop.description)
        }
        btnAdd.setOnClickListener() {
            shop.title = shopTitle.text.toString()
            shop.description= description.text.toString()
            if (shop.title.isNotEmpty()) {
                app.shops.create(shop.copy())
                info("add Button Pressed: $shopTitle")
                //app.shops.findAll().forEach{info("add Button Pressed: ${it.title}")}
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a Shop Name")
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shop, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
