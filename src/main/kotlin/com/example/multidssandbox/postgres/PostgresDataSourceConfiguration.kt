package com.example.multidssandbox.postgres

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager",
    basePackages = ["com.example.multidssandbox.postgres"]
)
class PostgresDataSourceConfiguration {

    @Bean
    fun postgresDataSource(): DataSource {
        return HikariDataSource().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            username = "postgres"
            password = "mysecretpassword"
            driverClassName = "org.postgresql.Driver"
        }
    }

    @Bean
    fun postgresEntityManagerFactory(
        postgresEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        @Qualifier("postgresDataSource") postgresDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val postgresJpaProperties: MutableMap<String, String?> = HashMap()
        postgresJpaProperties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        postgresJpaProperties["hibernate.hbm2ddl.auto"] = "create-drop"
        return postgresEntityManagerFactoryBuilder
            .dataSource(postgresDataSource)
            .packages("com.example.multidssandbox.postgres")
            .persistenceUnit("postgresDataSource")
            .properties(postgresJpaProperties)
            .build()
    }

    @Bean
    fun postgresTransactionManager(
        postgresEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(postgresEntityManagerFactory)
    }

}
