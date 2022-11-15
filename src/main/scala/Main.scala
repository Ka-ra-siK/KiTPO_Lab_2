import gui.GUI
import testing.Testing

object Main {
  def main(args: Array[String]): Unit = {
    val test: Testing = new Testing
    test.testDoubleType()
    test.testPointType()
  }
}