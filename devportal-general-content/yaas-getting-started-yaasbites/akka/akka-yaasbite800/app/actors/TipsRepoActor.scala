package actors

import actors.TipsRepoActor._
import akka.actor.{Actor, ActorRef, Props}
import model.domain.Tip
import play.api.libs.ws.WSClient
import utils.configs.NecessaryComponents

/**
  * Created by d066419 on 15/07/16.
  */
class TipsRepoActor(wSClient: WSClient, necessaryComponents: NecessaryComponents) extends Actor {

  private val documentActor: ActorRef = context.actorOf(TipsDocumentActor.props(wSClient, necessaryComponents))

  override def receive: Receive = incoming

  private def incoming: Receive = {
    case "ping" => sender() ! "pong"

    case Add(tip) =>
      documentActor ! TipsDocumentActor.Add(sender(), tip)
    case ListTips =>
      documentActor ! TipsDocumentActor.ListDocs(sender())
    case Update(tip) =>
      documentActor ! TipsDocumentActor.Update(sender(), tip)
    case Delete(id) =>
      documentActor ! TipsDocumentActor.Delete(sender(), id)
    case DeleteAll =>
      documentActor ! TipsDocumentActor.DeleteAll(sender())
    case Get(id) =>
      documentActor ! TipsDocumentActor.Get(sender(), id)
  }
}

object TipsRepoActor {
  def props(wSClient: WSClient, necessaryComponents: NecessaryComponents): Props = Props(new TipsRepoActor(wSClient, necessaryComponents))

  sealed trait TipsOps

  case object ListTips extends TipsOps

  case object DeleteAll extends TipsOps

  case class Add(tip: Tip) extends TipsOps

  case class Update(tip: Tip) extends TipsOps

  case class Delete(id: String) extends TipsOps

  case class Get(id: String) extends TipsOps

}
