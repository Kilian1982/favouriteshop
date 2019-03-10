package wit.org.favouriteshop.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import wit.org.favouriteshop.models.ShopMemStore

class MainApp : Application(), AnkoLogger{

    val shops = ShopMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Favourite Shop Application started")
        //shops.add(FavouriteshopModel("Argos","Everything"))
        //shops.add(FavouriteshopModel("Gamestop","Video games and Merchandice"))
        //shops.add(FavouriteshopModel("Golden Discs","CD's, DVD and Vinyl"))
    }
}