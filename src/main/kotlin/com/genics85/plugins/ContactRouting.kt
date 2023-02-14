package com.genics85.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.contactUsRoute(){
    routing{
        get("/contact"){
            call.respondText("hello contacts")
        }
    }
}
