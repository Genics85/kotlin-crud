package com.genics85.plugins

import com.genics85.controllers.ArticleControllerImpl
import com.genics85.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import org.kodein.di.DI
fun Application.dbRouting(){

    val log = LoggerFactory.getLogger(this::class.java)

    var dbFun = DAOFacadeImpl()
    var artFun = ArticleControllerImpl(DI)

    routing {
        get("/db"){
            log.info("#######################################")
            call.respond("db active")
        }
        post ("/db"){
            log.info("*********************************")
           var news = dbFun.addNewArticle("Something great","This is not even a new anymore")
            call.respond(news.toString())
        }
        get("/allArticles"){
            var articles=dbFun.allArticles()
            call.respond("successfully got all articles")
        }
        get("/article"){
            var art=dbFun.article(1)
            call.respond(art.toString())
        }
    }
}