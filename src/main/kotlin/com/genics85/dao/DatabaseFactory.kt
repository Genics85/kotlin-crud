package com.genics85.dao


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

object DatabaseFactory {
    private val log = LoggerFactory.getLogger(this::class.java)

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            this.jdbcUrl = "jdbc:h2:mem:~/test"
            this.driverClassName = "org.h2.Driver"
            this.username = "sa"
            this.password = ""
        }
        config.validate()
        return HikariDataSource(config)
    }
    fun connect() {
        log.info("Initialising database")
        val pool = hikari()
        Database.connect(pool)
        //runFlyway(pool)
    }

}
