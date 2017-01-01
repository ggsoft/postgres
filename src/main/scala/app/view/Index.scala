package app.view

import app.model._
import scalatags.Text.all._

object Index {

  def page(pg: String) =
  "<!DOCTYPE html>"+
    html(lang:="en",
      head(
        meta(name:="viewport", content:="width=device-width, initial-scale=1.0"),
        meta(httpEquiv:="Content-Type", content:="text/html; charset=UTF-8"),
        scalatags.Text.tags2.title("Articles and authors CRUD application"),
        meta(name:="decription", content:="Test project for postgres database"),
        link(rel:="stylesheet", href:="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"),
        link(rel:="stylesheet", href:="/css/style.css"),
        script(src:="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"),
        script(src:="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"),
        script(src:="/js/script.js")
      ),
      body(
        div(`class`:="navbar navbar-default navbar-fixed-top",
          div(`class`:="container",
            div(`class`:="navbar-header",
              button(`type`:="button",`class`:="navbar-toggle")
            ),
            ul(`class`:="nav navbar-nav",
              li(a(href:="/","Main")),
              li(a(href:="/artlist","List")),
              li(a(href:="/about","About"))
            )
          )
        ),
        div(`class`:="container",
          getCnt(pg)
        )
      )
    )

  def getCnt(pg: String) =
    pg match {
      case "main" => mainPage
      case "artlist" => listPage
      case "about" => aboutPage
      case _  =>  p("")
    }

  def mainPage = p("Main page of project will be here")

  def listPage = {
    div(`class`:="row",
      table(`class`:="table table-bordered",
        thead(tr(th("Id"), th("Date"), th("Author"), th("Title"), th("Text"))),
        tbody(id:="list", raw(Article.toHtml))
      ),
      label(`for`:="id","id:"),
      input(id:="id", `type`:="text", size:="3"),br,
      label(`for`:="date", "date:"),
      input(id:="date", `type`:="text", size:="20"),br,
      label(`for`:="author", "author:"),
      input(id:="author", `type`:="text", size:="40"),br,
      label(`for`:="title", "title:"),
      input(id:="title", `type`:="text", size:="60"),br,
      label(`for`:="text", "text:"),
      input(id:="text", `type`:="text", size:="100"),br,
      div(`class`:="btn-group buttons",
        a(id:="add", title:="Add", href:="#", span(`class`:="glyphicon glyphicon-plus")),
        a(id:="upd", title:="Change", href:="#", span(`class`:="glyphicon glyphicon-ok")),
        a(id:="del", title:="Delete", href:="#", span(`class`:="glyphicon glyphicon-remove"))
      )
    )
  }

  def aboutPage = p("About page of project will be here")

}


