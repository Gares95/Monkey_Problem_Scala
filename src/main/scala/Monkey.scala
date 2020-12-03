
case class Monkey(var rope: Rope, private var mDir: Int, private var color: String)
  extends Runnable {

  var way: Array[String] = Array("East", "West")

  def run(): Unit = {
    try {
      println(color + " monkey showed up from " + way(1 - mDir) + " wanting to cross to the " +way(mDir) +".")
      Thread.sleep((1000))
      println(color + " monkey ready to cross to the " + way(mDir) +".")
      rope.cross(mDir)
      Thread.sleep((4000))
      println(color + " monkey has almost crossed the rope to the " +way(mDir) + ".")
      rope.leave(mDir)
      println(color + " monkey has crossed the rope.")
    } catch {
      case e: InterruptedException => {
        e.printStackTrace()
        println("Operation interrupted")
      }

    }
  }

}