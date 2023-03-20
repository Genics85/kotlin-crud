package com.genics85

import com.genics85.dao.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.genics85.plugins.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*


fun main() {

    embeddedServer(Netty, port = 8000, host = "0.0.0.0"){
        install(ContentNegotiation){
            json()
        }
        DatabaseFactory.connect()
        contactUsRoute()
        dbRouting()
        homeRouting()
    }
        .start(wait = true)
}


