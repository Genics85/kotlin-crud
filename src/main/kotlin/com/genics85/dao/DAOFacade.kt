package com.genics85.dao

import com.genics85.database.Article

interface DAOFacade {
    fun allArticles():List<Article>
    fun article(id:Int):Article?
    fun addNewArticle(title:String,body:String):Article?
    fun editArticle(id:Int,body:String,title: String):Boolean
    fun deleteArticle(id:Int):Boolean
}