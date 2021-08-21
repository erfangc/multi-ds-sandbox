package com.example.multidssandbox.mysql

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
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
    entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager",
    basePackages = ["com.example.multidssandbox.mysql"]
)
class MySqlDataSourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource-mysql")
    fun mysqlDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource-mysql.configuration")
    fun mysqlDataSource(@Qualifier("mysqlDataSourceProperties") mysqlDataSourceProperties: DataSourceProperties): DataSource {
        return mysqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Primary
    @Bean
    fun mysqlEntityManagerFactory(
        mysqlEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        @Qualifier("mysqlDataSource") mysqlDataSource: DataSource?
    ): LocalContainerEntityManagerFactoryBean {
        val mysqlJpaProperties: MutableMap<String, String?> = HashMap()
        mysqlJpaProperties["hibernate.dialect"] = "org.hibernate.dialect.MySQL5Dialect"
        mysqlJpaProperties["hibernate.hbm2ddl.auto"] = "create-drop"
        return mysqlEntityManagerFactoryBuilder
            .dataSource(mysqlDataSource)
            .packages("com.example.multidssandbox.mysql")
            .persistenceUnit("mysqlDataSource")
            .properties(mysqlJpaProperties)
            .build()
    }

    @Primary
    @Bean
    fun mysqlTransactionManager(
        @Qualifier("mysqlEntityManagerFactory") mysqlEntityManagerFactory: EntityManagerFactory?
    ): PlatformTransactionManager {
        return JpaTransactionManager(mysqlEntityManagerFactory!!)
    }

}
