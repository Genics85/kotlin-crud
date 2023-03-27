package com.genics85.dao

import com.genics85.models.Article

interface DAOFacade {
    fun allArticles():List<Article>
    fun article(id:Int): Article?
    fun addNewArticle(art:Article): Int
    fun editArticle(id:Int,body:String,title: String):Int
    fun deleteArticle(id:Int):Boolean
}