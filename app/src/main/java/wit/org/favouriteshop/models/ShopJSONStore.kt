package wit.org.favouriteshop.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import wit.org.favouriteshop.helpers.*
import java.util.*

val JSON_FILE = "shops.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FavouriteshopModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ShopJSONStore : ShopStore, AnkoLogger {

    val context: Context
    var shops = mutableListOf<FavouriteshopModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FavouriteshopModel> {
        return shops
    }

    override fun create(shop: FavouriteshopModel) {
        shop.id = generateRandomId()
        shops.add(shop)
        serialize()
    }
    override fun delete(shop: FavouriteshopModel){
        shops.remove(shop)
        serialize()
    }

    override fun update(shop : FavouriteshopModel) {
        val shopsList = findAll()as ArrayList<FavouriteshopModel>
        var foundShop: FavouriteshopModel? = shopsList.find {p -> p.id ==shop.id}
        if(foundShop != null){
            foundShop.title = shop.title
            foundShop.description =shop.description
            foundShop.image = shop.image
            foundShop.lat = shop.lat
            foundShop.lng = shop.lng
            foundShop.zoom = shop.zoom
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(shops, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        shops = Gson().fromJson(jsonString, listType)
    }
}