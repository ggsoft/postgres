package app.action

import io.netty.channel.ChannelHandler.Sharable
import unfiltered.request._
import unfiltered.response._
import unfiltered.netty.cycle._
import unfiltered.netty._
import app.model._
import app.util._
import app.view.Index

@Sharable
object Site extends Plan with ThreadPool with ServerErrorResponse {

  def intent = {

    case GET(Path("/")) => Ok ~> ResponseString(Index.page("main"))
    case GET(Path("/about")) => Ok ~> ResponseString(Index.page("about"))
    case GET(Path("/artlist")) => Ok ~> ResponseString(Index.page("artlist"))

    case GET(Path("/list")) => {
      val map =  Article.findAll.map(a => (a.id.toString, a)).toMap
      Ok ~> responseJson(map)
    }

    case POST(Path("/add") & Params(params))  => {
      val (date, author, title, text) = (
        getStr(params.get("date")).trim,
        getStr(params.get("author")).trim,
        getStr(params.get("title")).trim,
        getStr(params.get("text")).trim
      )
      Article.add(new Article(0l,app.util.msec(date),author,title,text))
      Ok ~> ResponseString(Article.toHtml)
    }

    case POST(Path("/upd") & Params(params))  => {
       val (id, date, author, title, text) = (
         getLong(params.get("id")),
         getStr(params.get("date")).trim,
         getStr(params.get("author")).trim,
         getStr(params.get("title")).trim,
         getStr(params.get("text")).trim
       )
       Article.update(new Article(id,app.util.msec(date),author,title,text))
       Ok ~> ResponseString(Article.toHtml)
     }

     case GET(Path("/del") & Params(params)) => {
       val id =  getLong(params.get("id"))
       Article.delete(id)
       Ok ~> ResponseString(Article.toHtml)
     }

    case GET(Path("/css/style.css")) => {
      CssContent ~> ResponseString(load("style.css","public/css"))
    }

    case GET(Path("/js/script.js")) => {
      JsContent ~> ResponseString(load("script.js","public/js"))
    }

    case _ => NotFound
  }
}
