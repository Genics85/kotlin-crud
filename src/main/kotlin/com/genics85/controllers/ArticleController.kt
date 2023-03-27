package com.genics85.controllers

import com.genics85.core.APIResponse
import com.genics85.database.Article

interface ArticleController {
    /**
     * functions to get all articles in databases**/
    fun getArticles():APIResponse<List<Article>>

    /**
     * function to create an article in the database
    * */
    fun createArticle(title:String,body:String): APIResponse<Article?>

    /**
     * function to delete specific article in the database
     * **/
    fun deleteArticle(id:Int):APIResponse<Boolean>

    /**
     * this function is to delete specific article from the database
     * **/
    fun editArticle(id:Int,title: String,body: String):APIResponse<Int>
}