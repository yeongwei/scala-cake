package com.example

trait A {
  def msg: String
}

// A is the Super Class of B
trait B extends A {
  val hint: String
  def msg: String = s"Message from B -> ${hint}"
  def run: String = "This is B"
}

trait C {
  val zoo: String = "Zoo from C"
}

// C is the Super Class of D
// D inherits methods from C
// BUT depends on implementation that inherits A
trait D extends C {
  this: A =>
  def exec = s"${this.msg} and ${this.zoo}"
  // def exec2 = s"${this.run}" // This will get compilation error because run is a local method to B
}

object Main {
  def main(args: Array[String]): Unit = {
    val d = new D with B {
      val hint = "Hello World!"
    }

    println(d.exec) // Message from B -> Hello World! and Zoo from C
  }
}