/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  This file is part of the project 'scalolcode'.                                               *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  This project is free software. It comes without any warranty, to the extent permitted by     *
 *  applicable law. You can redistribute it and/or modify it under the terms of the Do What The  *
 *  Fuck You Want To Public License, Version 2, as published by Sam Hocevar.                     *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                                               *
 *  See http://sam.zoy.org/wtfpl/COPYING for more details.                                       *
 *                                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


import sbt._
import Keys._

object ScalolcodeBuild extends Build {

  lazy val baseSettings = Defaults.defaultSettings ++ Seq (
    organization         := "com.github.scalolcode",
    version              := "0.1.0-SNAPSHOT",
    scalaVersion         := "2.10.0-M7"
  )

  lazy val root = Project (
    id        = "scalolcode",
    base      = file("."),
    aggregate = Seq ( pimps, interpreter ),
    settings  = baseSettings
  )

  lazy val pimps = Project (
    id       = "scala-pimps",
    base     = file("scala-pimps"),
    settings = baseSettings ++ Seq (
      name := "scalolcode-scala-pimps",
      initialCommands in Compile in console += """
        import scalolcode.LOLCODE._
      """
    )
  )

  lazy val interpreter = Project (
    id       = "interpreter",
    base     = file("interpreter"),
    settings = baseSettings ++ Seq (
      name := "lolcode-interpreter",
      libraryDependencies ++= Seq (
        "org.specs2" % "specs2_2.10.0-M7" % "1.12.1.1" % "test"
      ),
      initialCommands in Compile in console += """
        import lolcode._
      """
    )
  )

}
