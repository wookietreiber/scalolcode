/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *  This file is part of the project 'scalolcode'.                           *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *  This project is free software. It comes without any warranty, to the     *
 *  extent permitted by applicable law. You can redistribute it and/or       *
 *  modify it under the terms of the Do What The Fuck You Want To Public     *
 *  License, Version 2, as published by Sam Hocevar.                         *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                           *
 *  See http://sam.zoy.org/wtfpl/COPYING for more details.                   *
 *                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


import sbt._
import Keys._

import Resolvers._
import Dependencies._
import BuildSettings._

object BuildSettings {
  lazy val buildOrganization = "com.github.scalolcode"
  lazy val buildVersion      = "0.1.0-SNAPSHOT"
  lazy val buildScalaVersion = "2.9.2"

  lazy val baseSettings = Defaults.defaultSettings ++ Seq (
    crossScalaVersions := Seq ( "2.9.1", "2.9.1-1", "2.9.2" )
  )

  lazy val buildSettings = baseSettings ++ Seq (
    organization         := buildOrganization,
    version              := buildVersion,
    scalaVersion         := buildScalaVersion,
    resolvers           ++= Seq ( sonatype ),
    libraryDependencies ++= Seq ( specs2 )
  )
}

object ScalolcodeBuild extends Build {

  lazy val root = Project (
    id        = "scalolcode",
    base      = file("."),
    aggregate = Seq ( pimps, interpreter ),
    settings  = baseSettings
  )

  lazy val pimps = Project (
    id       = "scalolcode-scala-pimps",
    base     = file("scala-pimps"),
    settings = buildSettings ++ Seq (
      initialCommands in Compile in console += """
        import scalolcode._
      """
    )
  )

  lazy val interpreter = Project (
    id       = "lolcode-interpreter",
    base     = file("lolcode-interpreter"),
    settings = buildSettings ++ Seq (
      initialCommands in Compile in console += """
        import lolcode._
      """
    )
  )

}

object Dependencies {
  lazy val specs2 = "org.specs2" %% "specs2" % "1.9" % "test"
}

object Resolvers {
  lazy val sonatype = "releases" at "http://oss.sonatype.org/content/repositories/releases"
}
