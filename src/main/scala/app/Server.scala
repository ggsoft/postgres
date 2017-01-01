package app

import app.action.Site
import unfiltered.netty
import app.util.confInt

object Server {
  def main(args: Array[String]) {
    netty.Server
      .http(confInt("port", 8000))
      .plan(Site)
      .run()
  }
}