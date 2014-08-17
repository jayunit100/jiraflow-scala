package org.apache.bigtop.devtools

import scala.collection.immutable.Nil

object Main extends App {
  object WeekDay extends Enumeration {
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }
  import WeekDay._
  
  def isWorkingDay(d: WeekDay.Value) = ! (d == Sat || d == Sun)

  WeekDay.values filter isWorkingDay foreach println
}

object Main2 extends App {
  object TestResult extends Enumeration {
    type RESULT=Value
    val PASS,FAIL = Value
  }
  import TestResult._

  val result = if(1==1) TestResult.PASS else Nil
   
  val string = result match {
    case TestResult.PASS => "one"
    case TestResult.FAIL => "two"
    case _ => "?"
  }
  
  println(string);
}