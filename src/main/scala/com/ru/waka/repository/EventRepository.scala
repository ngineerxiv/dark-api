package com.ru.waka.repository

import com.ru.waka.CalendarClient
import org.joda.time.DateTime

import scala.collection.mutable.{Seq => MutableSeq}
import scalaz.\/

trait EventRepository {
  def getAll: Seq[Event]

  def update: Throwable \/ Unit
}

class EventRepositoryOnMemory(calendarClient: CalendarClient, var data: MutableSeq[Event] = MutableSeq.empty) extends EventRepository {
  def getAll: Seq[Event] = Seq(data: _*)

  def update: Throwable \/ Unit = {
    for {
      events <- calendarClient.getEvents
    } yield data = MutableSeq(events: _*)

  }

}

case class Event(title: String, description: String, startAt: DateTime, location: String) {

  def toMap: Map[String, String] = Map(
    "title" -> title,
    "description" -> description,
    "startAt" -> startAt.toLocalDateTime.toString,
    "location" -> location
  )
}
