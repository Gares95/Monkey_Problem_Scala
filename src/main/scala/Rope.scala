
case class Rope() {

  var numMonkeysWaiting: Array[Int] = Array(0, 0)

  var monkeysCrossing: Array[Int] = Array(0, 0)

  var directionPriority: Array[Int] = Array(1, 1)

  var way: Array[String] = Array("East", "West")

  def cross(direction: Int): Unit = {
    synchronized {
      { numMonkeysWaiting(direction) += 1; numMonkeysWaiting(direction) - 1 }
      println(
        numMonkeysWaiting(direction) + " monkey/s waiting to cross to the " +
          way(direction) +
          ".")
      var auxMessage: Int = 0
      while ((monkeysCrossing(1 - direction) > 0) || ((directionPriority(direction) == 0) && (directionPriority(1 - direction) == 1)))
        try {
          if (monkeysCrossing(1 - direction) > 0) {
            directionPriority(direction) = 1
            directionPriority(1 - direction) = 0
          }
          if (auxMessage == 0) {
            if (monkeysCrossing(1 - direction) > 0) {
              println("Monkey waiting to other monkey finish crossing in opposite direction.")
            }
            if ((monkeysCrossing(direction) > 0) && (directionPriority(1 - direction) == 1))
              println("Monkey waiting because there are other monkeys in the opposite side waiting already.")
            auxMessage += 1;

          }
          wait()
      } catch {
        case e: InterruptedException => {
          e.printStackTrace()
          println("Operation Interrupted.")
        }

      }
      numMonkeysWaiting(direction) -= 1
      monkeysCrossing(direction) += 1
      notifyAll()
      println(
        numMonkeysWaiting(direction) + " monkey/s waiting to cross to the " + way(direction) + ".")
      println(
        monkeysCrossing(direction) + " monkey/s crossing to the " + way(direction) + ".")
    }
    // If it is necessary to make the monkeys go one by one in case there are monkeys waiting in both sides
    /*if(numMonkeysWaiting[1- direction]>0)
			directionPriority[1-direction]=1;*/

    // If we want to force 1 second inter-monkey spacing in the rope as well
    /*Thread.sleep(1000);*/

    // And reduce to 3 seconds the wait in Monkey class
    // If it is necessary to make the monkeys go one by one in case there are monkeys waiting in both sides
    /*if(numMonkeysWaiting[1- direction]>0)
			directionPriority[1-direction]=1;*/

    // If we want to force 1 second inter-monkey spacing in the rope as well
    /*Thread.sleep(1000);*/

    // And reduce to 3 seconds the wait in Monkey class
  }

  def leave(direction: Int): Unit = {
    synchronized {
      monkeysCrossing(direction) -= 1
      notifyAll()
      println(
        "1 monkey has finished crossing to the " + way(direction) +
          ". " +
          monkeysCrossing(direction) +
          " monkey/s left crossing to the " +
          way(direction))
    }
  }

}