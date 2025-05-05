class Admin(
    userId: String,
    name: String,
    email: String,
    address: String,
    val privileges: List<String>
) : User(userId, name, email, address) {

    fun addProduct(product: Product, inventory: MutableList<Product>) {
        inventory.add(product)
    }

    fun removeProduct(product: Product, inventory: MutableList<Product>) {
        inventory.remove(product)
    }

    fun updateProductDetails(product: Product, newPrice: Double, newStock: Int) {
        product.updateStock(newStock - product.stock)
    }

    fun viewOrders(allOrders: List<Order>) {
        allOrders.forEach { println(it.getOrderDetails()) }
    }
}
