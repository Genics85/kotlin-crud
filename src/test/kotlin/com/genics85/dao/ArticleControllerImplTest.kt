package com.genics85.dao

import com.genics85.controllers.ArticleControllerImpl
import com.genics85.models.Article
import io.ktor.util.reflect.*
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
import java.sql.SQLException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ArticleControllerImplTest {

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

    @Test
    fun addNewArticle() {
        //GIVEN
        val oneArticle = factory.manufacturePojoWithFullData(Article::class.java)
        every { service.addNewArticle(any()) } returns 1
        //WHEN
        val expected=underTest.createArticle(oneArticle.title,oneArticle.body)
        //THEN
            assertThat(expected.code).isEqualTo("201")
            assertThat(expected.message).isNotEmpty
            assertThat(expected.systemCode).isEqualTo("10")
    }

    @Test
    fun `can't add new article because there was an SQLException`(){
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        every{service.addNewArticle(any())} throws SQLException()
        //WHEN
        val expected = underTest.createArticle(oneArticle.title,oneArticle.body)
        //THEN
        assertThat(expected.code).isEqualTo("500")
    }

    @Test
    fun getAllArticles(){
        //GIVEN
        val listOfArticle: MutableList<Article> = mutableListOf()
        for (i in 1..10){
            listOfArticle.add(factory.manufacturePojoWithFullData(Article::class.java))
        }
        every{service.allArticles()} returns listOfArticle
        //WHEN
        val expected=underTest.getArticles()
        //THEN
        assertThat(expected.data).isNotEmpty
        assertThat(expected.data.size).isGreaterThan(0)
        assertThat(expected.code).isEqualTo("201")
    }

    @Test
    fun `returns an empty list because there is no data in the database`(){
        //GIVEN
        val articles:List<Article> = listOf()
        every{service.allArticles()} returns articles
        //WHEN
        val expected=underTest.getArticles()
        //THEN
        assertThat(expected.code).isEqualTo("200")
        assertThat(expected.data).isEmpty()
        assertThat(expected.data.size).isEqualTo(0)
    }

    @Test
    fun editArticle() {
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
            every { service.editArticle(any(),any(),any()) } returns 2
        //WHEN
        val expected= oneArticle.id?.let { underTest.editArticle(it,oneArticle.title,oneArticle.body) }
        //THEN
        if (expected != null) {
            assertThat(expected.code).isEqualTo("201")
        }
    }

    @Test
    fun getArticle(){
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        every{service.getArticle(any())} returns oneArticle
        //WHEN
        val expected= oneArticle.id?.let { underTest.getArticle(it) }
        //THEN
        if (expected != null) {
            assertThat(expected.code).isEqualTo("201")
            assertThat(expected.data).isNotEmpty
            assertThat(expected.data.size).isEqualTo(1)
            assertThat(expected.data.first()).isInstanceOf(Article::class.java)
        }
    }

    @Test fun `could not get an article because no article has that id`(){
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        every { service.getArticle(any()) } returns null
        //WHEN
        val expected = oneArticle.id?.let { underTest.getArticle(it) }
        //THEN
        if (expected != null) {
            assertThat(expected.code).isEqualTo("200")
            assertThat(expected.data).isEmpty()
            assertThat(expected.data.size).isEqualTo(0)
        }
    }

    @Test
    fun deleteArticle() {
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        every { service.deleteArticle(any()) } returns true
        //WHEN
        val expected= oneArticle.id?.let { underTest.deleteArticle(it) }
        //THEN
        if (expected != null) {
            assertThat(expected.code).isEqualTo("201")
        }
    }

    @Test
    fun `could not delete article because it is not found`(){
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        every{service.deleteArticle(any())} returns false
        //WHEN
        val expected= oneArticle.id?.let { underTest.deleteArticle(it) }
        //THEN
        if (expected != null) {
            assertThat(expected.code).isEqualTo("200")
        }
    }

    @Test
    fun `could not delete article from database because of server error`(){
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        every{service.deleteArticle(any())} throws SQLException()
        //WHEN
        val expected= oneArticle.id?.let { underTest.deleteArticle(it) }
        //THEN
        if (expected != null) {
            assertThat(expected.code).isEqualTo("500")
        }
    }



}