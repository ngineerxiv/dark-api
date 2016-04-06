package com.ru.waka

import com.ru.waka.repository.{Event, EventRepository}

import scalaz._

class EventServer(eventRepository: EventRepository) {
  def getEvents: Seq[Event] = eventRepository.getAll

  def update: Throwable \/ Unit = eventRepository.update
}
