package com.genics85.controllers

import com.genics85.database.Article

interface ArticleController {

    /**
     * function to create an article in the database
    * */
    fun createArticle(title:String,body:String):Article?

    /**
     * function to delete specific article in the database
     * **/
    fun deleteArticle(id:Int):Int

    /**
     * this function is to delete specific article from the database
     * **/
    fun editArticle(id:Int,title: String,body: String):Boolean
}