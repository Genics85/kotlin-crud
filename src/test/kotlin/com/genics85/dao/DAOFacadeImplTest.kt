package com.genics85.dao

import com.genics85.controllers.ArticleControllerImpl
import com.genics85.models.Article
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import uk.co.jemos.podam.api.PodamFactoryImpl

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DAOFacadeImplTest {

    private lateinit var service:DAOFacade
    private lateinit var di: DI
    private lateinit var underTest:ArticleControllerImpl
    private  val factory:PodamFactoryImpl = PodamFactoryImpl()

    @BeforeAll
    fun setup(){
        service=mockk(relaxed = true)
        di=DI{
            bindSingleton {service}
        }
        underTest=ArticleControllerImpl(di)
    }

    @AfterAll
    fun tearDown(){
        unmockkAll()
    }

//    @Test
//    fun simpleTest(){
//        val some:Int=34
//        assertEquals(some, 34)
//    }
//    @Test
//    fun complexTest(){
//        val nothing:Int=323
//        assertEquals(nothing,323)
//    }

//    @Test
//    fun allArticles() {
//        //GIVEN
//
//        //WHEN
//        //THEN
//    }

//    @Test
//    fun article() {
        //GIVEN

        //WHEN
        //THEN
//    }
//
    @Test
    fun addNewArticle() {
        //GIVEN
        val oneArticle = factory.manufacturePojoWithFullData(Article::class.java);
        every { service.addNewArticle(any()) } returns 1
        //WHEN
        val expected=underTest.createArticle(oneArticle.title,oneArticle.body)
        //THEN
            assertThat(expected.code).isEqualTo("201")
            assertThat(expected.message).isNotEmpty
            assertThat(expected.systemCode).isEqualTo("10")
    }
////
//    @Test
//    fun editArticle() {
//        //GIVEN
//        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
//            every { service.editArticle(any(),any(),any()) } returns Int
//        //WHEN
//        //THEN
//    }
//
//    @Test
//    fun deleteArticle() {
//    }
}