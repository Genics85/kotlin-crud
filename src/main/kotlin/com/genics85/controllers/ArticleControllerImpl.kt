package com.genics85.controllers

import com.genics85.dao.DAOFacadeImpl
import com.genics85.database.Article
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class ArticleControllerImpl(override val di: DI) :ArticleController, DIAware {

    private val daoFacade: DAOFacadeImpl by di.instance()

    /**
     * function to create an article in the database
     * */
    override fun createArticle(title: String, body: String): Article? {
        return daoFacade.addNewArticle(title,body)
    }

    /**
     * function to delete specific article in the database
     * **/
    override fun deleteArticle(id: Int): Int {
        TODO("Not yet implemented")
    }

    /**
     * this function is to delete specific article from the database
     * **/
    override fun editArticle(id: Int, title: String, body: String): Boolean {
        TODO("Not yet implemented")
    }
}