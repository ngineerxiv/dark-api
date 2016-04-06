package com.ru.waka

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.ru.waka.repository.Event
import org.joda.time.{DateTime => JodaDateTime}

import scala.collection.JavaConverters._
import scalaz.\/

class CalendarClient(credential: Credential, calendarId: String) {
  private val jsonFactory = JacksonFactory.getDefaultInstance

  private val transport = GoogleNetHttpTransport.newTrustedTransport()

  private val applicationName = "dark-api"

  private val service = new Calendar.Builder(transport, jsonFactory, credential).setApplicationName(applicationName).build()

  def getEvents: Throwable \/ Seq[Event] = \/.fromTryCatchNonFatal {
      service.events().list("primary")
        .setCalendarId(calendarId)
        .setMaxResults(10)
        .setTimeMin(new DateTime(System.currentTimeMillis()))
        .execute().getItems.asScala.map {
        e => Event(
          e.getSummary,
          e.getDescription,
          new JodaDateTime(e.getStart.getDateTime.getValue),
          e.getLocation)
      }
  }
}

