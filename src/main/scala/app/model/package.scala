package app

//import java.sql.{Connection, DriverManager}
import com.zaxxer.hikari._

import app.util._

package object model {

  /*Class.forName(conf("db.driver"))

  val url = conf("db.url")

  val props = new java.util.Properties
  props.setProperty("user", conf("db.user"))
  props.setProperty("password", conf("db.password"))

  def conn =  DriverManager.getConnection(url, props)*/

  val cfg = new HikariConfig()
  cfg.setJdbcUrl(conf("db.url"))
  cfg.setUsername(conf("db.user"))
  cfg.setPassword(conf("db.password"))
  cfg.setMaximumPoolSize(30)
  cfg.addDataSourceProperty("cachePrepStmts", "true")
  cfg.addDataSourceProperty("prepStmtCacheSize", "250")
  cfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

  val ds = new HikariDataSource(cfg)

  def conn = ds.getConnection

}
