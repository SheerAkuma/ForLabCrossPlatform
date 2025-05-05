data class Product(
    val productId: String,
    val name: String,
    val description: String,
    val price: Double,
    var stock: Int
) {
    fun updateStock(quantity: Int) {
        stock += quantity
    }

    fun isInStock(): Boolean = stock > 0

    fun getDetails(): String =
        "ID: $productId | $name - $description | $price UAH | Stock: $stock"
}
