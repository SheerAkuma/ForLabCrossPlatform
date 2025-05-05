class ShoppingCart {
    private val cartItems = mutableMapOf<Product, Int>()

    fun addItem(product: Product, quantity: Int) {
        cartItems[product] = cartItems.getOrDefault(product, 0) + quantity
    }

    fun removeItem(product: Product) {
        cartItems.remove(product)
    }

    fun viewCart() {
        cartItems.forEach { (product, qty) ->
            println("${product.name} x$qty = ${product.price * qty} UAH")
        }
        println("Total: ${calculateTotal()} UAH")
    }

    fun calculateTotal(): Double =
        cartItems.entries.sumOf { (product, qty) -> product.price * qty }

    fun getItems(): Map<Product, Int> = cartItems.toMap()

    fun clear() = cartItems.clear()
}
