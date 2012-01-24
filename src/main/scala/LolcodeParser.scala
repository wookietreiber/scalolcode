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

import scala.util.parsing.combinator._

/** Parses LOLCODE scripts.
  *
  * LOLCODE scripts begin with `HAI`, end with `KTHXBYE` and can include a
  * variable amount of statements. Statements are separated by either a dot,
  * `.`, or a line separator, `\n`, `\r` or `\r\n`.
  */
class LolcodeParser extends JavaTokenParsers {

  /** Returns `"""[ \t\x0B\f]""".r`. */
  override protected val whiteSpace = """[ \t\x0B\f]+""".r

  /** `HAI` followed by a variable amount of statements and ending with `KTHXBYE`. */
  def lolcodeScript: Parser[Any] = hai~kthxbye

  /** Beginning of a LOLCODE script, `HAI`. */
  def hai: Parser[Any] = "HAI"~statementSeparator

  /** End of a LOLCODE script, `KTHXBYE`, exiting with the default status code. */
  def kthxbye: Parser[Any] = "KTHXBYE"

  /** Statements are separated by either a dot or a line separator.
    *
    * Valid line separators are `\n`, `\r` and `\r\n`.
    */
  def statementSeparator: Parser[Any] = """[.\r\n]+[.\s]*""".r

}
