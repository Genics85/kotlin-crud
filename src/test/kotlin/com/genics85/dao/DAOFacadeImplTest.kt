package com.genics85.dao

import com.genics85.database.Articles
import com.genics85.models.Article
import io.ktor.util.reflect.*
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import uk.co.jemos.podam.api.PodamFactoryImpl
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DAOFacadeImplTest {

    private val factory: PodamFactoryImpl = PodamFactoryImpl()
    private lateinit var underTest:DAOFacadeImpl
    private lateinit var articles: List<Article>

    @BeforeAll
    fun setUp() {
        DatabaseFactory.connect()
        underTest=DAOFacadeImpl()
        articles= factory.manufacturePojoWithFullData(List::class.java,Article::class.java) as List<Article>
        articles.forEach{
            underTest.addNewArticle(it)
        }
    }

    @AfterAll
    fun tearDown() {
        transaction {
            SchemaUtils.drop(Articles)
        }
    }

    @Test
    fun allArticles() {
        //WHEN
        val expected=underTest.allArticles()
        //THEN
        assertThat(expected.size).isNotEqualTo(0)
        assertThat(expected.first()).instanceOf(Article::class)
        assertThat(expected.isNotEmpty())
    }

    @Test
    fun getArticle() {
        //GIVEN
        val oneArticle = underTest.allArticles()
        //WHEN
        val expected = (oneArticle[Random.nextInt(0,oneArticle.size)]).id?.let { underTest.getArticle(it) }
        //THEN
        assertThat(expected).instanceOf(Article::class)
        assertThat(expected).isNotNull
    }

    @Test
    fun `can't get article because ID does not exist in database`(){
        //GIVEN
        val oneArticle=articles.first()
        //WHEN
        val expected= oneArticle.id?.let { underTest.getArticle(it) }
        //THEN
        assertThat(expected).isNull()

    }

    @Test
    fun addNewArticle() {
        //GIVEN
        val oneArticle=factory.manufacturePojoWithFullData(Article::class.java)
        //WHEN
        val expected=underTest.addNewArticle(oneArticle)
        //THEN
        assertThat(expected).isNotEqualTo(0)

    }

    @Test
    fun editArticle() {
        //GIVEN
        val list=underTest.allArticles()
        val oneArticle = list[Random.nextInt(1,list.size)]
        oneArticle.title= "Something new here"
        oneArticle.body="nice body for your bae"
        //WHEN
        val expected = oneArticle.id?.let { underTest.editArticle(it,oneArticle.body,oneArticle.title) }
        //THEN
        assertThat(expected).isGreaterThan(0)
    }

    @Test
    fun `can not update article because ID is not found in database`(){
        //GIVEN
        val oneArticle=articles.first()
        //WHEN
        val expected = oneArticle.id?.let { underTest.editArticle(it,oneArticle.body,oneArticle.title) }
        //THEN
        assertThat(expected).isEqualTo(0)
    }

    @Test
    fun deleteArticle() {
        //GIVEN
        val oneArticle=underTest.allArticles()
        //WHEN
        val expected= oneArticle.first().id?.let { underTest.deleteArticle(it) }
        //THEN
        assertThat(expected).isTrue
        assertThat(expected).instanceOf(Boolean::class)
    }

    @Test
    fun `can not delete article because ID is not in database`(){
        //GIVEN
        val oneArticle=articles.first()
        //WHEN
        val expected= oneArticle.id?.let {underTest.deleteArticle(it)}
        //THEN
        assertThat(expected).isFalse
        assertThat(expected).instanceOf(Boolean::class)
    }
}