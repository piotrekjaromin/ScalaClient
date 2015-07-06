import scala.util.control.Breaks._

/**
 * Created by piotr on 06.07.15.
 */
object DatabaseUse {
  def main(args: Array[String]) {
    val client  = new ClientTest()

    breakable {
      while (true) {
        println("1 - add to database")
        println("2 - update database")
        println("3 - remove from database")
        println("4 - exit")

        val choice: Int = Console.readInt()
        if (choice == 4) break

        matchTest(client, choice)
      }
    }


    client.end()
  }

    def matchTest(client : ClientTest, x: Int): Any = x match {
      case 1 => {
        println("write a index number: ")
        client.add(Console.readInt())
        println("added to database")
      }
      case 2 => {
        println("write a index number to update and new name")
        client.update(Console.readInt(), Console.readLine)
        println("update database")
      }
      case 3 => {
        println("write a index number to remove")
        client.delete(Console.readInt())
        println("deleted")
      }

    }
}
