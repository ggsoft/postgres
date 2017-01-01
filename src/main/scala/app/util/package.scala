package app

import java.io.{File, FileReader}
import java.text.SimpleDateFormat
import java.util.{Date, Properties}

import scala.io.Source

package object util {

  private val fconf: Option[File] = {
    List(".", "conf").map(s => new File(s+"/config.txt")).find(_.exists)
  }

  private val cfg = new Properties
  init()

  def init(fo: Option[File] = fconf) =
    try {
      fo.map(f => cfg.load(new FileReader(f)))
    }
    catch {
      case _: Throwable =>
    }

  private val DF = "MM/dd/yyyy HH:mm:ss"
  def msec: Long = (new Date).getTime
  def msec(s: String) = try{ new SimpleDateFormat(DF).parse(s).getTime } catch {case _: Throwable => 0l}
  def date: String = new SimpleDateFormat(DF).format(new Date)
  def date(t: Long) = new SimpleDateFormat(DF).format(new Date(t))

  def getVal(key: String): Option[String] = {
    Option(cfg.getProperty(key))
  }

  def getValInt(key: String): Option[Int] = {
    try {
      Some(cfg.getProperty(key).toInt)
    }
    catch {
      case _: Throwable => None
    }
  }

  def conf(key: String, default: String = ""): String = getVal(key).getOrElse(default)
  def confInt(key: String, default: Int = 0): Int = getValInt(key).getOrElse(default)

  private def getT[B](a: Any, f: Any => B, default: B): B = {
    try( f(a) ) catch { case  _: Throwable => default }
  }

  def getType[B](a: Any, f: Any => B, default: B): B = {
    a match {
      case Some(b) =>
        b match {
          case s: Seq[Any] => s.headOption.map(x => getT(x,f,default)).getOrElse(default)
          case _ => getT(b,f,default)
        }
      case None => default
      case _ => getT(a,f,default)
    }
  }

  def fs(x: Any) = x.toString
  def fi(x: Any) = x.toString.toInt
  def fl(x: Any) = x.toString.toLong

  def getStr(a: Any, default: String = ""): String = {
    getType(a,fs,default)
  }

  def getInt(a: Any, default: Int = 0): Int = {
    getType(a,fi,default)
  }

  def getLong(a: Any, default: Long = 0l): Long = {
    getType(a,fi,default)
  }

  def load(fn: String, dir: String): String = {
    try {
      Source.fromFile(dir+"/"+fn).mkString
    } catch {case _: Throwable => ""}
  }

}
