package com.genics85.dao

import com.genics85.database.Article
import com.genics85.database.Articles
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow)=Article(
        id =row[Articles.id],
        title =row[Articles.title],
        body =row[Articles.body]
    )

    override fun allArticles(): List<Article>  =transaction{
        Articles.selectAll().map(::resultRowToArticle).toList()
    }

    override fun article(id: Int): Article? = transaction {
         Articles.select( Articles.id eq id )
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override fun addNewArticle(title: String, body: String): Article? = transaction{
        Articles.insert{
            it[Articles.title]=title
            it[Articles.body]=body
        }.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override fun editArticle(id: Int, body: String, title: String): Int {
        return Articles.update ({Articles.id eq id}){
            it[Articles.title]=title
            it[Articles.body]=body
        }
    }

    override fun deleteArticle(id: Int): Boolean {
       return Articles.deleteWhere{Articles.id eq id} > 1
    }
}