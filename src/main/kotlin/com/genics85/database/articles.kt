package com.genics85.database

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


object Articles:Table(name="articles"){
    val id:Column<Int> = integer("id").autoIncrement().uniqueIndex()
    val title:Column<String> = varchar("title",512)
    val body:Column<String> = varchar ("body",2048)

    override val primaryKey=PrimaryKey(id)
}
