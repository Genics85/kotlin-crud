package com.genics85.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.homeRouting(){
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}

