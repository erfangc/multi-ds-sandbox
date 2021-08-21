package com.example.multidssandbox.postgres

import com.example.multidssandbox.postgres.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int>