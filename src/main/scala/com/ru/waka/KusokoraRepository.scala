package com.ru.waka

import twitter4j.{Query, Twitter}

import scala.collection.JavaConverters._
import scala.util.matching.Regex
import scalaz.\/

class KusokoraRepository(twitter: Twitter, private var urls: Seq[String] = Nil) {
  private val query = new Query("#papixクソコラグランプリ")
  private val urlPattern = new Regex("""(http|https)://(.*)\.([a-zA-Z0-9/]+)""", "url")

  def update(): Throwable \/ Unit = \/.fromTryCatchNonFatal({
    urls = twitter.search(query).getTweets.asScala.flatMap(tweet => {
      urlPattern.findAllIn(tweet.getText).matchData.map(_.toString())
    }).toSet.toSeq
  })

  def get: Seq[String] = Seq(urls:_*)
}
