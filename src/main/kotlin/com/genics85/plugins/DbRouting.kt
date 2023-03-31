package com.genics85.plugins

import com.genics85.controllers.ArticleControllerImpl
import com.genics85.dao.DAOFacadeImpl
import com.genics85.models.Article
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.DI
import org.kodein.di.bindSingleton

private const val BASE_URL:String="/API"
fun Application.dbRouting() {

    val di= DI{
        bindSingleton{DAOFacadeImpl()}
    }
    var dbFun = ArticleControllerImpl(di)

    routing {
        route(BASE_URL){
            get("/ping"){
                call.respond("pong")
            }
            post ("/add"){
                val dataFrom=call.receive<Article>()
                var news = dbFun.createArticle(dataFrom.title,dataFrom.body)
                call.respond(news.message)
            }
            get("/get"){
                var articles=dbFun.getArticles()
                call.respond(articles.data)
            }
            get("/get/{id}"){
                var article=dbFun.getArticle(call.parameters["id"]?.toInt() ?: 1)
                call.respond(article.data)
            }
            delete("/del/{id}"){
                var art=dbFun.deleteArticle(call.parameters["id"]?.toInt() ?: 1)
                call.respond(art.message)
            }
            post("/edit"){
                var dataFrom=call.receive<Article>()
                if(dataFrom.id !=null){
                    var art=dbFun.editArticle(dataFrom.id!!,dataFrom.title,dataFrom.body)
                    call.respond(art.message)
                }
                call.respond("no id found")
            }
        }
    }
}