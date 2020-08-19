package com.seb.akka.kamon

import akka.actor.{Actor, ActorLogging, Props}
import kamon.Kamon
import kamon.trace.Span

object SampleActor {
  def props: Props = Props(new SampleActor())
  case class Echo(s: String)
}

class SampleActor() extends Actor with ActorLogging {
  def receive: Receive = {
    case SampleActor.Echo(s) => log.info("received {}", s)
    case s: String =>
      val origin = sender()
      println("aha")
      origin ! SampleActor.Echo(s)
  }
}
