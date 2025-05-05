
package com.example.bookstore.controller

import com.example.bookstore.model.Product
import com.example.bookstore.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val service: ProductService) {

    @GetMapping
    fun getAll(): List<Product> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Product = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody product: Product): Product = service.create(product)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody product: Product): Product =
        service.update(id, product)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}
