package com.example.multidssandbox.postgres

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
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
    @ConfigurationProperties("spring.datasource-postgres")
    fun postgresDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties("spring.datasource-postgres.configuration")
    fun postgresDataSource(@Qualifier("postgresDataSourceProperties") postgresDataSourceProperties: DataSourceProperties): DataSource {
        return postgresDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Bean
    fun postgresEntityManagerFactory(
        postgresEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        @Qualifier("postgresDataSource") postgresDataSource: DataSource?
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
        @Qualifier("postgresEntityManagerFactory") postgresEntityManagerFactory: EntityManagerFactory?
    ): PlatformTransactionManager {
        return JpaTransactionManager(postgresEntityManagerFactory!!)
    }
}