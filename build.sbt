name := "dark api"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.google.api-client" % "google-api-client" % "1.21.0",
  "com.google.oauth-client" % "google-oauth-client-jetty" % "1.21.0",
  "com.google.apis" % "google-api-services-calendar" % "v3-rev173-1.21.0",
  "org.scalaz" %% "scalaz-core" % "7.1.1",
  "joda-time" % "joda-time" % "2.7",
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.json4s" % "json4s-ext_2.11" % "3.3.0",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.twitter4j" % "twitter4j-core" % "4.0.4"
)
