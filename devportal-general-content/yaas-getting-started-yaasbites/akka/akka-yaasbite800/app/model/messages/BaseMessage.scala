package model.messages

import akka.actor.ActorRef

/**
  * Created by d066419 on 15/07/16.
  */
trait BaseMessage {
  def controllerActor: ActorRef
}
