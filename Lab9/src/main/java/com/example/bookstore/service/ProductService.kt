
package com.example.bookstore.service

import com.example.bookstore.model.Product
import com.example.bookstore.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val repository: ProductRepository) {

    fun getAll(): List<Product> = repository.findAll()

    fun getById(id: Long): Product = repository.findById(id).orElseThrow {
        NoSuchElementException("Product not found with id: $id")
    }

    fun create(product: Product): Product = repository.save(product)

    fun update(id: Long, updated: Product): Product {
        val product = getById(id)
        product.name = updated.name
        product.description = updated.description
        product.price = updated.price
        product.stock = updated.stock
        return repository.save(product)
    }

    fun delete(id: Long) = repository.deleteById(id)
}
