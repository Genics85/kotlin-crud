package com.genics85.dao

import com.genics85.database.Articles
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

public object DatabaseFactory {

    var db: DataSource=connect()

    fun connect():DataSource{
        val config=HikariConfig()
        config.jdbcUrl = "jdbc:mysql://localhost:3306/tut"
        config.username = "root"
        config.password = "TechyGenics85"
        config.driverClassName = "com.mysql.jdbc.Driver"

        transaction(Database.connect(db)){
            SchemaUtils.create(Articles)
        }

        return HikariDataSource(config)
    }

}