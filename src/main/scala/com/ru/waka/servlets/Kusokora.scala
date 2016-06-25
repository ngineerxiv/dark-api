package com.ru.waka.servlets

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.ru.waka.KusokoraRepository
import com.typesafe.scalalogging.LazyLogging
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import twitter4j.TwitterFactory

import scalaz.{-\/, \/-}

class Kusokora extends HttpServlet with LazyLogging {
  implicit val format = DefaultFormats
  val tw = TwitterFactory.getSingleton
  val repository = new KusokoraRepository(tw, Nil)

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    val res = Map(
      "kusokora" -> repository.get
    )
    response.setStatus(200)
    response.setContentType("application/json;charset=UTF-8")
    response.getWriter.println(Serialization.write(res))
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse) {
    repository.update() match {
      case \/-(_) =>
        response.setStatus(200)
      case -\/(ex) =>
        logger.error(ex.getMessage, ex)
        response.setStatus(500)
    }
    response.setContentType("application/json;charset=UTF-8")
    response.getWriter.println("{}")
  }
}

