package com.example.multidssandbox.mssql

import com.example.multidssandbox.mssql.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, String>