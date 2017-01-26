package app.model

import com.github.takezoe.scala.jdbc._

case class Article(val id: Long, val ms: Long, val author: String, val title: String, val text: String)

object Article {

  def findAll: Seq[Article] = DB.autoClose(conn) { db =>
    db.select("SELECT * FROM articles", Article.apply _)
  }

  def find(id: Long): Option[Article] =
    if (id > 0) {
      DB.autoClose(conn) { db =>
        db.selectFirst(sql"SELECT * FROM articles WHERE id=$id", Article.apply _)
      }
    } else None

  def add(a: Article) = DB.autoClose(conn) { db =>
    db.update(sql"INSERT INTO articles (published,author,title,text) VALUES (${a.ms},${a.author},${a.title},${a.text})")
  }

  def delete(id: Long) = DB.autoClose(conn) { db =>
    db.update(sql"DELETE FROM articles WHERE id=$id")
  }

  def update(a: Article) = DB.autoClose(conn) { db =>
    db.update(sql"UPDATE articles SET published=${a.ms},author=${a.author},title=${a.title},text=${a.text} WHERE id=${a.id}")
  }

  def toHtml = findAll.map(a =>
    s"<tr><td>${a.id}</td><td>${app.util.date(a.ms)}</td><td>${a.author}</td><td>${a.title}</td><td>${a.text}</td></tr>"
  ).mkString

}
