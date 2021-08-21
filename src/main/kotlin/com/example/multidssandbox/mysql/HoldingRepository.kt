package com.example.multidssandbox.mysql

import com.example.multidssandbox.mysql.models.Holding
import com.example.multidssandbox.mysql.models.HoldingId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HoldingRepository : JpaRepository<Holding, HoldingId>