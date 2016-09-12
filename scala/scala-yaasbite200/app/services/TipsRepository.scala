package services

import javax.inject.{Inject, Singleton}

import model.{Tip, TipBodyRequest}
import play.api.db.slick.DatabaseConfigProvider
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class TipsRepository @Inject()(databaseConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) {
  private val dbConfig: DatabaseConfig[JdbcProfile] = databaseConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  private class TipsTable(tag: Tag) extends Table[Tip](tag, "tips") {
    def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def tip: Rep[String] = column[String]("tip")

    override def * : ProvenShape[Tip] = (id, tip) <>((Tip.apply _).tupled, Tip.unapply)
  }

  private val tips: TableQuery[TipsTable] = TableQuery[TipsTable]

  def create(tipAddRequest: TipBodyRequest): Future[Tip] = db.run {
    (tips.map((x: TipsTable) => x.tip)
      returning tips.map((x: TipsTable) => x.id)
      into ((tip, id) => Tip(id, tip))
      ) += tipAddRequest.tip
  }

  def create2(tipAddRequest: TipBodyRequest): Future[String] = db.run {
    tips += Tip(0, tipAddRequest.tip)
  }.map(_ => "success").recover {
    case NonFatal(e) => Option(e.getCause.getMessage).getOrElse("some error")
  }

  def list(): Future[Seq[Tip]] = db.run(tips.result)

  def update(tip: Tip) = db.run {
    val filter = tips.filter(_.id === tip.id)
    val action = filter.update(tip)
    action
  }

  def delete(id: Long): Future[Int] = db.run {
    tips.filter(_.id === id).delete
  }

  def find(id: Long): Future[Option[Tip]] = db.run(tips.filter(_.id === id).result.headOption)

  def exists(id: Long): Future[Boolean] = db.run(tips.filter(_.id === id).exists.result)

}
