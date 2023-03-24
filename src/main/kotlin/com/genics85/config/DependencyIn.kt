package com.genics85.config

import com.genics85.controllers.ArticleControllerImpl
import com.genics85.dao.DAOFacadeImpl
import io.ktor.server.application.*
import org.kodein.di.bindSingleton
import org.kodein.di.DI

fun Application.configureDI(){
    DI{
        bindSingleton{ ArticleControllerImpl(di)}
        bindSingleton{DAOFacadeImpl()}
    }
}

