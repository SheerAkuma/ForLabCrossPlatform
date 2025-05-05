open class User(
    val userId: String,
    val name: String,
    val email: String,
    val address: String
) {
    open fun getDetails(): String =
        "User: $name | Email: $email | Address: $address"
}
