package app.model

//import scalikejdbc.{ConnectionPool, ConnectionPoolSettings}
import java.sql.{Connection, DriverManager}

import app.util._

object DB {

  Class.forName(conf("db.driver"))

/*  val settings = ConnectionPoolSettings (
    initialSize = 10,
    maxSize = 30,
    connectionTimeoutMillis = 3000L,
    validationQuery = "select 1 from dual"
  )

  ConnectionPool.singleton(conf("db.url"), conf("db.user"), conf("db.password"), settings)*/

  def getConnection(): Connection = {
    var props = new java.util.Properties
    props.setProperty("user", conf("db.user"))
    props.setProperty("password", conf("db.password"))
    DriverManager.getConnection(conf("db.url"), props)
  }

  def getConnection(autocommit: Boolean = true): Connection = {
    val connection = this.getConnection
    connection.setAutoCommit(autocommit)
    connection
  }

  def withConnection[A](block: Connection => A): A = {
    val c: Connection =  getConnection //ConnectionPool.borrow()

    try {
      block(c)
    } finally {
      if (!c.isClosed)
        try {
          c.close()
        } catch {
          case e: Throwable =>
        }
    }
  }
}
