package wit.org.favouriteshop.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ShopMemStore : ShopStore, AnkoLogger {
    val shops = ArrayList<FavouriteshopModel>()

    override fun findAll(): List<FavouriteshopModel>{
        return shops
    }

    override fun create(shop: FavouriteshopModel) {
        shop.id = getId()
        shops.add(shop)
        logAll()
    }

    override fun update(shop:FavouriteshopModel){
        var foundShop:FavouriteshopModel? = shops.find {p-> p.id == shop.id}
        if(foundShop != null){
            foundShop.title = shop.title
            foundShop.description = shop.description
            logAll()
        }
    }
    fun logAll(){
        shops.forEach{ info("${it}")}
    }
}