import sbt._

object Dependencies {

  val test = Seq(
    "com.typesafe"         % "config"                  % "1.4.2"    % Test,
    "com.vladsch.flexmark" % "flexmark-all"            % "0.62.2"   % Test,
    "org.scalatest"       %% "scalatest"               % "3.2.13"   % Test,
    "org.scalatestplus"   %% "selenium-4-2"            % "3.2.13.0" % Test,
    "uk.gov.hmrc"         %% "webdriver-factory"       % "0.46.0"   % Test,
    "com.typesafe.play"   %% "play-ahc-ws-standalone"  % "2.1.2"    % Test,
    "org.slf4j"            % "slf4j-simple"            % "1.7.25"   % Test,
    "com.typesafe.play"   %% "play-ws-standalone-json" % "2.1.2"    % Test,
    "com.typesafe.play"   %% "play-json"               % "2.10.0-RC8"
  )

}
