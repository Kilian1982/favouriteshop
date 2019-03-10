package wit.org.favouriteshop.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import wit.org.favouriteshop.R
import wit.org.favouriteshop.helpers.readImage
import wit.org.favouriteshop.helpers.readImageFromPath
import wit.org.favouriteshop.helpers.showImagePicker
import wit.org.favouriteshop.main.MainApp
import wit.org.favouriteshop.models.FavouriteshopModel

class MainActivity : AppCompatActivity(), AnkoLogger {

    var shop = FavouriteshopModel()

    lateinit var app : MainApp

    var edit = false

    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info("MainActivity has started")
        //toolbarAdd.title = title
        //setSupportActionBar(toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("shop_edit")) {
            edit = true
            shop = intent.extras.getParcelable<FavouriteshopModel>("shop_edit")
            shopTitle.setText(shop.title)
            description.setText(shop.description)
            shopImage.setImageBitmap(readImageFromPath(this, shop.image))
            btnAdd.setText(R.string.save_shop)

        }
        btnAdd.setOnClickListener() {
            shop.title = shopTitle.text.toString()
            shop.description= description.text.toString()
            if (shop.title.isEmpty()) {
                toast (R.string.enter_shop_title)
            } else {
                if(edit){
                    app.shops.update(shop.copy())
                }else{
                    app.shops.create(shop.copy())
                }
            }
            info("add Button Pressed: $shopTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
            info ("Select image")
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    shop.image = data.getData().toString()
                    shopImage.setImageBitmap(readImage(this, resultCode, data))
                    }
                }
            }
        }
    }

