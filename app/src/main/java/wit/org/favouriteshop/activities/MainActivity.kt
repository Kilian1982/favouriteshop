package wit.org.favouriteshop.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import wit.org.favouriteshop.R
import wit.org.favouriteshop.helpers.readImage
import wit.org.favouriteshop.helpers.readImageFromPath
import wit.org.favouriteshop.helpers.showImagePicker
import wit.org.favouriteshop.main.MainApp
import wit.org.favouriteshop.models.FavouriteshopModel
import wit.org.favouriteshop.models.Location

class MainActivity : AppCompatActivity(), AnkoLogger {

    var shop = FavouriteshopModel()

    lateinit var app: MainApp

    var edit = false

    val IMAGE_REQUEST = 1

    val LOCATION_REQUEST = 2

    //var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        info("MainActivity has started")
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        app = application as MainApp
        //edit = true
        if (intent.hasExtra("shop_edit")) {
            edit = true
            shop = intent.extras.getParcelable<FavouriteshopModel>("shop_edit")
            shopTitle.setText(shop.title)
            description.setText(shop.description)
            shopImage.setImageBitmap(readImageFromPath(this, shop.image))
            if (shop.image != null) {
                chooseImage.setText(R.string.change_shop_image)
            }
            btnAdd.setText(R.string.save_shop)

            }

            btnAdd.setOnClickListener {
                shop.title = shopTitle.text.toString()
                shop.description = description.text.toString()
                if (shop.title.isEmpty()) {
                    toast(R.string.enter_shop_title)
                } else {
                    if (edit) {
                        app.shops.update(shop.copy())
                    } else {
                        app.shops.create(shop.copy())
                    }
                }
                info("add Button Pressed: $shopTitle")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            shopLocation.setOnClickListener {
                val location = Location(52.245696, -7.139102, 14f)
                if(shop.zoom != 0f){
                    location.lat =shop.lat
                    location.lng =shop.lng
                    location.zoom = shop.zoom
                }
                startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
                info ("Set Location Pressed")
            }
            chooseImage.setOnClickListener {
                showImagePicker(this, IMAGE_REQUEST)
                info("Select image")
            }
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_shop, menu)
            if(edit && menu != null) menu.getItem(0).setVisible(true)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.item_delete-> {
                    app.shops.delete(shop)
                    finish()
                }
                R.id.item_cancel ->{
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
                        shop.image = data.data.toString()
                        shopImage.setImageBitmap(readImage(this, resultCode, data))
                        chooseImage.setText(R.string.change_shop_image)
                    }
                }
                LOCATION_REQUEST -> {
                    if (data != null) {
                        val location = data.extras.getParcelable<Location>("location")
                        shop.lat = location.lat
                        shop.lng = location.lng
                        shop.zoom= location.zoom
                    }
                }
            }
        }
    }


