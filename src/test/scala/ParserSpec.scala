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


package scalolcode

import org.specs2._
import org.specs2.matcher._

import scala.io.Source

import Interpreter._

class ParserSpec extends Specification with ParserMatchers with ResultMatchers { def is =

  // -----------------------------------------------------------------------
  // fragments
  // -----------------------------------------------------------------------

  "LOLCODE Parser Specification"                                              ^
                                                                             p^
  "empty programs (just 'HAI' and 'KTHXBYE')"                                 ^
    "must be accepted when separated by ..."                                  ^
      "a single dot"          ! fromStr("HAI.KTHXBYE")                        ^
      "multiple dots"         ! fromStr("HAI...KTHXBYE")                      ^
                                                                             p^
    "must be accepted when separated by the line separator ..."               ^
      "\\n"                   ! fromStr("HAI\nKTHXBYE")                       ^
      "\\r"                   ! fromStr("HAI\rKTHXBYE")                       ^
      "\\r\\n"                ! fromStr("HAI\r\nKTHXBYE")                     ^
                                                                             p^
    "must not be accepted when separated by ..."                              ^
      "a single space"        ! (fromStr("HAI KTHXBYE")  must beFailing)      ^
      "a single tab"          ! (fromStr("HAI\tKTHXBYE") must beFailing)      ^
                                                                            end

  // -----------------------------------------------------------------------
  // tests
  // -----------------------------------------------------------------------

  def fromStr(src: String) = string2script(      src ) must beASuccess
  def fromRes(res: String) = string2script(fetch(res)) must beASuccess

  // -----------------------------------------------------------------------
  // utils
  // -----------------------------------------------------------------------

  val parsers = Interpreter

  def fetch(res: String) = Source.fromURL(getClass.getResource(res)).mkString

}
