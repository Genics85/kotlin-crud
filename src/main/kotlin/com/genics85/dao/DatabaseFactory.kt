package com.genics85.dao


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

object DatabaseFactory {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun connect() {
        log.info("Initialising database")
        val pool = hikari()
        Database.connect(pool)
        //runFlyway(pool)
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {

            this.jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tut"
            this.driverClassName = "com.mysql.cj.jdbc.Driver"
            this.username = "root"
            this.password = "TechyGenics85"
        }

        config.validate()
        return HikariDataSource(config)
    }



}
