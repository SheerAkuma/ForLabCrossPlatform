data class Payment(
    val paymentId: String,
    val order: Order,
    val amount: Double,
    val paymentMethod: String,
    var status: String = "PENDING"
) {
    fun processPayment() {
        status = "COMPLETED"
        println("Payment of $amount UAH completed via $paymentMethod.")
    }

    fun getPaymentDetails(): String =
        "Payment ID: $paymentId | Method: $paymentMethod | Amount: $amount | Status: $status"
}
