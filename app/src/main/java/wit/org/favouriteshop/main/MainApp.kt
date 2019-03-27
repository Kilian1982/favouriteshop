package wit.org.favouriteshop.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import wit.org.favouriteshop.models.ShopJSONStore
//import wit.org.favouriteshop.models.ShopMemStore
import wit.org.favouriteshop.models.ShopStore

class MainApp : Application(), AnkoLogger{

    lateinit var shops : ShopStore

    override fun onCreate() {
        super.onCreate()
        //this is to help if you want to use the phones memory but its not persistent
        //shops= ShopMemStore()
        shops = ShopJSONStore(applicationContext)
        info("Favourite Shop Application started")
        //test shops for the list
        //shops.add(FavouriteshopModel("Argos","Everything"))
        //shops.add(FavouriteshopModel("Gamestop","Video games and Merchandice"))
        //shops.add(FavouriteshopModel("Golden Discs","CD's, DVD and Vinyl"))
    }
}