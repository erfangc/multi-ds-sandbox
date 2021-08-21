package com.example.multidssandbox.mssql

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "mssqlEntityManagerFactory",
    transactionManagerRef = "mssqlTransactionManager",
    basePackages = ["com.example.multidssandbox.mssql"]
)
class MsSqlConfiguration {

    @Bean(name = ["mssqlDataSource"])
    fun mssqlDataSource(): DataSource {
        return HikariDataSource().apply {
            jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=master"
            username = "sa"
            password = "MyPassword#"
            driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
        }
    }

    @Bean(name = ["mssqlEntityManagerFactory"])
    fun mssqlEntityManagerFactory(
        mssqlEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        @Qualifier("mssqlDataSource") mssqlDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val mssqlJpaProperties: MutableMap<String, String?> = HashMap()
        mssqlJpaProperties["hibernate.dialect"] = "org.hibernate.dialect.SQLServerDialect"
        mssqlJpaProperties["hibernate.hbm2ddl.auto"] = "update"
        return mssqlEntityManagerFactoryBuilder
            .dataSource(mssqlDataSource)
            .packages("com.example.multidssandbox.mssql")
            .persistenceUnit("mssqlDataSource")
            .properties(mssqlJpaProperties)
            .build()
    }

    @Bean(name = ["mssqlTransactionManager"])
    fun mssqlTransactionManager(
        @Qualifier("mssqlEntityManagerFactory") mssqlEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(mssqlEntityManagerFactory)
    }

}
