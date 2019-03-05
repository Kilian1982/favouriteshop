package wit.org.favouriteshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

        btnAdd.setOnClickListener() {
            shop.title = shopTitle.text.toString()
            shop.description= description.text.toString()
            if (shop.title.isNotEmpty()) {
                app.shops.add(shop.copy())
                info("add Button Pressed: $shopTitle")
                app.shops.forEach{info("add Button Pressed: ${it.title}")}
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a Shop Name")
            }
        }
    }
}
