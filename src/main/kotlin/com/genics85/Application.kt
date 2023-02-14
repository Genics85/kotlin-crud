package com.genics85

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.genics85.plugins.*


fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0"){
        homeRouting()
        contactUsRoute()
    }
        .start(wait = true)
}


