package com.genics85.dao

import com.genics85.database.Article
import com.genics85.database.Articles
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow)=Article(
        id=row[Articles.id],
        title=row[Articles.title],
        body=row[Articles.body]
    )

    override fun allArticles(): List<Article>  {
        return Articles.selectAll().map(::resultRowToArticle)

    }

    override fun article(id: Int): Article? {
        return Articles.select( Articles.id eq id )
            .map(::resultRowToArticle)
            .singleOrNull()
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