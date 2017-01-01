package app.model

import anorm._
import anorm.SqlParser._

case class Article(id: Long, date: String, author: String, title: String, text: String)

object Article {
  val parser = {
    get[Long]("id") ~
    get[Long]("published") ~
    str("author") ~
    str("title") ~
    str("text") map {
      case id~published~author~title~text =>
        Article(id,app.util.date(published),author,title,text)
    }
  }

  def findAll: List[Article] =
    DB.withConnection { implicit con =>
      SQL("SELECT * FROM articles") as parser.*
    }

  def find(id: Long): Option[Article] =
    if (id > 0) {
      DB.withConnection { implicit con =>
        (SQL("SELECT * FROM articles WHERE id={id}").on("id" -> id) as parser.*).headOption
      }
    } else None

  def add(a: Article) =
    DB.withConnection { implicit con =>
      SQL("INSERT INTO articles (published, author, title, text) VALUES ({date},{author},{title},{text})").on(
        "date" -> app.util.msec(a.date),
        "author" -> a.author,
        "title" -> a.title,
        "text" -> a.text
      ).executeInsert(scalar[Long].single)
    }

  def delete(id: Long) =
    DB.withConnection { implicit con =>
      SQL("DELETE FROM articles WHERE id={id}").on("id" -> id).executeUpdate()
    }

  def update(a: Article) =
    DB.withConnection { implicit con =>
      SQL("UPDATE articles SET published={date},author={author},title={title},text={text} WHERE id={id}").on(
        "id" -> a.id,
        "date" -> app.util.msec(a.date),
        "author" -> a.author,
        "title" -> a.title,
        "text" -> a.text
      ).executeUpdate()
    }

  def toHtml = findAll.map(a =>
    s"<tr><td>${a.id}</td><td>${a.date}</td><td>${a.author}</td><td>${a.title}</td><td>${a.text}</td></tr>"
  ).mkString

}
