package models.mbase

import models.mbase.BaseTable._
import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by gk on 2016/2/14.
  */
object BaseDao {
  private val db = DatabaseConfigProvider.get[JdbcProfile](Play.current).db
  private val treeDirId = new ArrayBuffer[Int]()

  import BaseTable._

  def getDirectoryList(id: Int) = {
    val rs = TabDirectory.filter(_.father_id === id).map(p => (p.name, p.id)).result
    Await.result(db.run(rs), Duration.Inf)
  }

  def getDirectoryName(id: Int) = {
    val rs = TabDirectory.filter(_.id === id).map(_.name).result
    Await.result(db.run(rs), Duration.Inf)
  }

  def getTree(id: Int) = {
    treeDirId.clear
    setTree(id)
    treeDirId.reverse
  }

  def setTree(id: Int): Unit = {
    if (id != 0) {
      treeDirId += id
      val rs = TabDirectory.filter(_.id === id).map(_.father_id).result.head
      val father_id = Await.result(db.run(rs), Duration.Inf)
      setTree(father_id)
    }
  }

  def addDirectory(father_id: Int, name: String) = {
    println("add " +  name)
    val insertActions = DBIO.seq(TabDirectory.map(c => (c.name, c.father_id)) +=(name, father_id))
    Await.result(db.run(insertActions), Duration.Inf)
  }

}