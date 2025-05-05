data class Order(
    val orderId: String,
    val customer: Customer,
    val items: Map<Product, Int>,
    val status: String = "PROCESSING"
) {
    val totalPrice: Double = items.entries.sumOf { (product, qty) -> product.price * qty }

    fun updateStatus(newStatus: String) {
        // статус можна змінити
    }

    fun getOrderDetails(): String {
        val itemDetails = items.entries.joinToString("\n") {
            "${it.key.name} x${it.value} = ${it.key.price * it.value} UAH"
        }
        return "Order: $orderId\nStatus: $status\nItems:\n$itemDetails\nTotal: $totalPrice"
    }
}
