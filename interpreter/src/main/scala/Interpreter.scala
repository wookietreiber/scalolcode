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

object Interpreter extends LolcodeParser with App {

  for (file <- args) file2script(file)

  def file2script(file: String) = string2script(io.Source.fromFile(file).mkString)

  def string2script(src: String) = parseAll(lolcodeScript, src)

}
