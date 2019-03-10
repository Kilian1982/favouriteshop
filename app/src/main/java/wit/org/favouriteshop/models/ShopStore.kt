package wit.org.favouriteshop.models

interface ShopStore {
    fun findAll(): List<FavouriteshopModel>
    fun create(shop:FavouriteshopModel)
    fun update(shop: FavouriteshopModel)

}