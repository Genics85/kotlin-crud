package com.genics85.controllers

import com.genics85.core.APIResponse
import com.genics85.dao.DAOFacade
import com.genics85.models.Article
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.slf4j.LoggerFactory
import java.sql.SQLException

class ArticleControllerImpl(override val di: DI) :ArticleController, DIAware {

    private val daoFacade: DAOFacade by di.instance()
    private val log= LoggerFactory.getLogger(this::class.java)

    /**
     * functions to get all articles in databases**/
    override fun getArticles(): APIResponse<List<Article>> {
        val artsOne:APIResponse<List<Article>> = try{
            val arts: List<Article> = daoFacade.allArticles()
            if(arts.isNotEmpty()){
                APIResponse("40","201","All article received with success", listOf(arts))
            }else{
                APIResponse("40","200","No article found in database",listOf())
            }
        }catch (se:SQLException){
            log.error("Error occurred while getting all articles from database")
            APIResponse("40","500","Error occurred in system when getting articles",listOf())
        }
        return artsOne
    }

    /**
     * function to create an article in the database
     * */
    override fun createArticle(title: String, body: String): APIResponse<Article?> {
        val newArt:APIResponse<Article?> = try{
            val tempArt=Article(null,title,body)
          val created:Int=daoFacade.addNewArticle(tempArt)
            if(created==1){
                APIResponse("10","201","Article created with success",listOf())
            }else{
                APIResponse("10","200","failed while creating article", listOf())
            }
        }catch (se: SQLException){
            log.error("Article creation failed on server")
            APIResponse("10","500","Error occured while creating Article", listOf())
        }
        return newArt
    }

    /**
     * function to delete specific article in the database
     * **/
    override fun deleteArticle(id: Int): APIResponse<Boolean> {
        val delOne:APIResponse<Boolean> = try{
            val delVal:Boolean=daoFacade.deleteArticle(id)
            if(delVal){
                APIResponse("20","201","Article deletion success", listOf())
            }else{
                APIResponse("20","200","Article creation failed",listOf())
            }
        }catch (se:SQLException){
            log.error("Error occurred while deleting article")
            APIResponse("20","500","Error occured in article deletion",listOf())
        }
        return delOne
    }

    /**
     * this function is to delete specific article from the database
     * **/
    override fun editArticle(id: Int, title: String, body: String): APIResponse<Int> {
        val editOne:APIResponse<Int> = try{
            val editedArt:Int = daoFacade.editArticle(id,body,title)
            if(editedArt > 0){
                APIResponse("30","201","Changes in article done with success",listOf())
            }else{
                APIResponse("30","200","Changes in article was not successful",listOf())
            }
        }catch (se:SQLException){
            log.error("Error occurred while editing article")
            APIResponse("30","500","Error occurred while updating article",listOf())
        }
        return editOne
    }
}