package com.genics85.dao

import com.genics85.database.Article

class DAOFacadeImpl : DAOFacade {
    override fun allArticles(): List<Article> {
        TODO("Not yet implemented")
    }

    override fun article(id: Int): Article? {
        TODO("Not yet implemented")
    }

    override fun addNewArticle(title: String, body: String): Article? {
        TODO("Not yet implemented")
    }

    override fun editArticle(id: Int, body: String, title: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteArticle(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}