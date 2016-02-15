package models.mbase

import slick.driver.MySQLDriver.api._

/**
  * Created by gk on 2016/2/14.
  */
object BaseTable {

  class Directory(tag: Tag) extends Table[(Int, String, Int)](tag, "Directory") {
    def id = column[Int]("id", O.PrimaryKey)

    def name = column[String]("name")

    def father_id = column[Int]("father_id")

    def * = (id, name, father_id)
  }

  val TabDirectory = TableQuery[Directory]
}
