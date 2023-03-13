package com.genics85.plugins

import com.genics85.models.Contact
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.contactUsRoute(){
    routing{
        get("/contact"){
            val sandra=Contact("Sandra","Ghana",987632)
            call.response.header("something","nice")
            call.response.headers.append("nothing","bad")
            call.respond(sandra)

        }
        get("/contact/{page}"){
            val pageNumber:String? =call.parameters["page"]
            call.respondText("hello mothersucker this is your reply $pageNumber")
        }
        post("/contact"){
            var contactInfo=call.receive<Contact>()
            call.respondText(contactInfo.toString())
        }
    }
}
