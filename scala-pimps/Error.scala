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


package scalolcode

/** $ErrorInfo */
object Error extends Error

/** $ErrorInfo
  *
  * @define ErrorInfo Provides LOLCODE syntax for error handling.
  *
  * {{{
  * import scalolcode.Error._
  *
  * try { sys.error("foobar") } catch Y U MAD
  * }}}
  */
trait Error {

  type ErrorHandler = PartialFunction[Throwable,Unit]

  val MAD: ErrorHandler = {
    case e ⇒ Console.err.println(e.getMessage)
  }

  object Y {
    def U(eh: ErrorHandler) = eh
  }

}
