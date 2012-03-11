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


package lolcode

import scala.util.parsing.combinator._

/** Parses LOLCODE scripts.
  *
  * LOLCODE scripts begin with `"HAI"`, end with `"KTHXBYE"` and can include a
  * variable amount of statements. Statements are separated by either a dot
  * (`"."`) or a line separator (`"\n"`, `"\r"` or `"\r\n"`).
  */
class LolcodeParser extends JavaTokenParsers {

  /** `"HAI"` followed by a variable amount of statements and ending with `"KTHXBYE"`. */
  def lolcodeScript: Parser[Any] = hai ~ statements ~ kthxbye

  // -----------------------------------------------------------------------
  // essentials
  // -----------------------------------------------------------------------

  /** Beginning of a LOLCODE script, `"HAI"`. */
  def hai: Parser[Any] = "HAI" ~ statementSeparator

  /** A variable amount of statements, separated by one or more statement separators. */
  def statements: Parser[Any] = rep ( statement ~ statementSeparator )

  /** End of a LOLCODE script, `"KTHXBYE"`, exiting with the default status code. */
  def kthxbye: Parser[Any] = "KTHXBYE" ~ ( statementSeparator | eol )

  // -----------------------------------------------------------------------
  // statements
  // -----------------------------------------------------------------------

  /** May be any one of the implemented statements. */
  def statement: Parser[Any] = byes | diaf

  /** `"BYES"` exits the script at any point.
    *
    * It optionally takes an unsigned integer as status code and a string
    * literal which is printed to stdout.
    */
  def byes: Parser[Any] = "BYES" ~ opt ( unsignedDecimalNumber ~ opt ( stringLiteral ) )

  /** `"DIAF"` exits the script at any point with failure.
    *
    * It optionally takes an unsigned integer, which may not be `"0"`, as status
    * code and a string literal which is printed to stderr.
    */
  def diaf: Parser[Any] = "DIAF" ~ opt ( unsignedDecimalNumberNotZero ~ opt ( stringLiteral ) )

  // -----------------------------------------------------------------------
  // utilities
  // -----------------------------------------------------------------------

  /** Returns `"""[ \t\x0B\f]""".r`. */
  override protected val whiteSpace = """[ \t\x0B\f]+""".r

  /** Returns `"""\d+""".r`. */
  def unsignedDecimalNumber: Parser[String] = """\d+""".r

  /** Returns `"""[123456789]\d*""".r`. */
  def unsignedDecimalNumberNotZero: Parser[String] = """[123456789]\d*""".r

  /** Returns `"""\z""".r`. */
  def eol: Parser[String] = """\z""".r

  /** Statements are separated by either a dot or a line separator.
    *
    * Valid line separators are `"\n"`, `"\r"` and `"\r\n"`.
    */
  def statementSeparator: Parser[Any] = """[.\r\n]+[.\s]*""".r

}
