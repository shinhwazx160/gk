package controllers

import play.api.mvc._

class Base extends Controller {

  def index(id: Int = 0) = Action {
    Ok(views.html.gkbase(id))
  }

  def addDirectory(id: Int, name: String) = Action {
    println("okadd " +  name)
    println("okadd " +  id)
    models.mbase.BaseDao.addDirectory(id, name)
    println("abc")
    Ok(views.html.gkbase(id))
  }
}
