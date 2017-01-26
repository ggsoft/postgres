package app

import java.sql.{Connection, DriverManager}

import app.util._

package object model {

  Class.forName(conf("db.driver"))

  val url = conf("db.url")

  val props = new java.util.Properties
  props.setProperty("user", conf("db.user"))
  props.setProperty("password", conf("db.password"))

  def conn: Connection =  DriverManager.getConnection(url, props)

}
