package com.seb.akka.kamon

import akka.actor.ActorSystem
import kamon.Kamon

import scala.concurrent.duration._

object Main extends App {

  Kamon.init()

  val actorSystem: ActorSystem = ActorSystem.create("helloAkka")
  import actorSystem.dispatcher
  val receiver = actorSystem.actorOf(SampleActor.props, "receiver")
  val sender = actorSystem.actorOf(SampleActor.props, "sender")
  actorSystem.scheduler.schedule(1.seconds, 100.millis) { receiver.tell("Hello", sender) }
}
