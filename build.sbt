name := "Stratego"
organization  := "de.htwg.se.stratego"
version       := "0.2.0"
scalaVersion  := "2.12.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.3"

libraryDependencies += "com.google.inject" % "guice" % "4.2.3"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.10"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

coverageExcludedPackages := ".*gui.*;.*Stratego.*"