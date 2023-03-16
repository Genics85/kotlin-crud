package com.genics85.plugins

import com.genics85.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.dbRouting(){
    var dbFun = DAOFacadeImpl()
    routing {
        get("/db"){
            call.respond("db active")
        }
        post ("/db"){
           var news = dbFun.addNewArticle("Something great","This is not even a new anymore")
            call.respond(news.toString())
        }
    }
}