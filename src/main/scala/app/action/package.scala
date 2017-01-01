package app

import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization
import unfiltered.response.{JsonContent, ResponseString}

package object action {

  implicit val formats = Serialization.formats(NoTypeHints)
  def toJson(o: AnyRef) = Serialization.write(o)

  def responseJson(m: Map[String,Any]) = {
    JsonContent ~> ResponseString(toJson(m))
  }

}
