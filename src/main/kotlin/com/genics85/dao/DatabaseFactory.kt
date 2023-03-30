package com.genics85.dao


import com.genics85.database.Articles
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object DatabaseFactory {
    private val log = LoggerFactory.getLogger(this::class.java)

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            this.jdbcUrl = "jdbc:mysql://localhost:3306/tut"
            this.driverClassName = "com.mysql.cj.jdbc.Driver"
            this.username = "root"
            this.password = "TechyGenics85"
            this.isAutoCommit=true
        }
        config.validate()
        return HikariDataSource(config)
    }
    fun connect() {
            log.info("Initialising database")
            val pool = hikari()
            Database.connect(pool)
            transaction{
                SchemaUtils.createMissingTablesAndColumns(Articles)
            }
            log.info("Database connected successfully")
        //runFlyway(pool)
    }

}
