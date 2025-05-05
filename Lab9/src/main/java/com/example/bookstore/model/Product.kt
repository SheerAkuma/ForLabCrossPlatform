
package com.example.bookstore.model

import jakarta.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var stock: Int = 0
)
