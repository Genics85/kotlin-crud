package com.genics85.dao

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DAOFacadeImplTest {

    @Test
    fun simpleTest(){
        val some:Int=34
        assertEquals(some, 34)
    }
    @Test
    fun complexTest(){
        val nothing:Int=323
        assertEquals(nothing,323)
    }

//    @Test
//    fun allArticles() {
//    }
//
//    @Test
//    fun article() {
//    }
//
//    @Test
//    fun addNewArticle() {
//    }
//
//    @Test
//    fun editArticle() {
//    }
//
//    @Test
//    fun deleteArticle() {
//    }
}