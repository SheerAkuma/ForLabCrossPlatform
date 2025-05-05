class BookStoreSystem {
    val inventory = mutableListOf<Product>()
    val orders = mutableListOf<Order>()
    val users = mutableListOf<User>()

    fun registerUser(user: User) {
        users.add(user)
    }

    fun addProduct(product: Product) {
        inventory.add(product)
    }

    fun findProductById(id: String): Product? {
        return inventory.find { it.productId == id }
    }
}
