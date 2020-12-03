object Monkey_Business {
  import java.util.Random
  def main(args: Array[String]): Unit = {
    val rope: Rope = new Rope()
    val colors: Array[String] = Array("Red",
      "Yellow",
      "Blue",
      "Brown",
      "White",
      "Grey",
      "Pink",
      "Orange")
    var nMonkeys: Int = 8
    var direction: Int = 0
    if ((args.length > 0)) {
      try if ((java.lang.Integer.parseInt(args(0)) > 0) && (java.lang.Integer
        .parseInt(args(0)) < 9))
        nMonkeys = java.lang.Integer.parseInt(args(0))
      catch {
        case e: NumberFormatException => {
          System.err.println("Argument must be an integer.")
          System.exit(1)
        }

      }
    }
    val hilos: Array[Thread] = Array.ofDim[Thread](nMonkeys)
    val random: Random = new Random()
    for (i <- 0 until nMonkeys) {
      direction = random.nextInt(2)
      try {
        // To force a minimum inter-monkey spacing of 1 second
        Thread.sleep((random.nextInt((9 - 1) / nMonkeys) + 1) * 1000)
        hilos(i) = new Thread(new Monkey(rope, direction, colors(i)))
        hilos(i).start()
      } catch {
        case e: InterruptedException => e.printStackTrace()

      }
    }
  }

}
