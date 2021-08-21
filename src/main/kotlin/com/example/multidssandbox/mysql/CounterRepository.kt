package com.example.multidssandbox.mysql

import com.example.multidssandbox.mysql.models.Counter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CounterRepository : JpaRepository<Counter, String> {
}