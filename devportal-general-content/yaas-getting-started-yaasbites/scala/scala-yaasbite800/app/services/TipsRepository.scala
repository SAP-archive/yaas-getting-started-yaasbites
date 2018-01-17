package services

import javax.inject.{Inject, Singleton}

import exceptions.YaasException
import model.domain.{Tip, TipBodyRequest}
import utils.commons.EitherOps._
import utils.configs.ThreadPools

import scala.concurrent.Future

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class TipsRepository @Inject()(docServiceWrapper: DocServiceWrapper) {
  private implicit val executionContext = ThreadPools.appExecContext

  def create(tipAddRequest: TipBodyRequest, tenant: String): Future[Either[YaasException, Tip]] = docServiceWrapper.add(Tip(tipAddRequest, tenant = tenant))


  def list: Future[Either[YaasException, List[Tip]]] = docServiceWrapper.getDocuments

  def update(tip: Tip): Future[Either[YaasException, String]] = docServiceWrapper.update(tip)

  def delete(id: String): Future[Either[YaasException, String]] = docServiceWrapper.delete(id)

  def deleteAll(): Future[Either[YaasException, String]] = docServiceWrapper.deleteAll

  def find(id: String): Future[Either[YaasException, Option[Tip]]] = docServiceWrapper.get(id)

  def exists(id: String): Future[Either[YaasException, Boolean]] = docServiceWrapper.get(id) map (_.map(_.isDefined))

}
