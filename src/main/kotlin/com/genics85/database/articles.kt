package com.genics85.database

import org.jetbrains.exposed.sql.Table

data class Article(val id:Int,val title:String,val body:String);

object Articles:Table(){
    val id=integer("id").autoIncrement()
    val title=varchar("title",128)
    val body=varchar ("body",2048)
    val something=varchar("something",256)

    override val primaryKey=PrimaryKey(id)
}
