class Customer(
    userId: String,
    name: String,
    email: String,
    address: String
) : User(userId, name, email, address) {

    private val cart = ShoppingCart()
    private val orders = mutableListOf<Order>()

    fun addToCart(product: Product, quantity: Int) {
        cart.addItem(product, quantity)
    }

    fun placeOrder(): Order {
        val order = Order("ORD${orders.size + 1}", this, cart.getItems())
        orders.add(order)
        cart.clear()
        return order
    }

    fun viewOrderHistory(): List<Order> = orders
}
