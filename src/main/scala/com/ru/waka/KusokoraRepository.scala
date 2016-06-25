package com.ru.waka

import twitter4j.{Query, Twitter}

import scala.collection.JavaConverters._
import scalaz.\/

class KusokoraRepository(twitter: Twitter, private var urls: Seq[String] = Nil) {
  private val query = new Query("#papixクソコラグランプリ")

  def update(): Throwable \/ Unit = \/.fromTryCatchNonFatal({
    urls = twitter.search(query).getTweets.asScala.flatMap(tweet => {
      tweet.getMediaEntities.map(_.getMediaURL)
    }).toSet.toSeq
  })

  def get: Seq[String] = Seq(urls:_*)
}

