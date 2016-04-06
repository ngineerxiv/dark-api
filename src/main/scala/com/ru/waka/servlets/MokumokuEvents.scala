package com.ru.waka.servlets

import java.io.{File, FileInputStream}
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.services.calendar.CalendarScopes
import com.ru.waka.CalendarClient
import com.ru.waka.repository.EventRepositoryOnMemory
import com.typesafe.scalalogging.LazyLogging
import org.json4s.DefaultFormats
import org.json4s.ext.JodaTimeSerializers
import org.json4s.native.Serialization

import scala.collection.JavaConverters._

class MokumokuEvents extends HttpServlet with LazyLogging {
  import MokumokuEvents._

  implicit val format = DefaultFormats ++ JodaTimeSerializers.all

  val credential = {
    if (new File(credentialJsonPath).exists()) {
      logger.info(s"$credentialJsonPath File was found")
      GoogleCredential.fromStream(new FileInputStream(credentialJsonPath))
    } else {
      logger.info(s"$credentialJsonPath File was not found. Default Credential will be used")
      GoogleCredential.getApplicationDefault()
    }
  }.createScoped(Seq(CalendarScopes.CALENDAR_READONLY).asJava)

  val calendarClient = new CalendarClient(credential, eventCalendarId)

  val eventRepository = new EventRepositoryOnMemory(calendarClient)

  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    eventRepository.update.leftMap(th => logger.error(th.getMessage, th))
    val body = Serialization.write(eventRepository.getAll.map(_.toMap))
    response.setContentType("application/json;charset=UTF-8")
    response.getWriter.println(body)
  }
}

object MokumokuEvents {
  val credentialJsonPath = "dark-google-calendar.json"

  val eventCalendarId = "h2j0hj6rh0kadoi561c03amv84@group.calendar.google.com"
}
