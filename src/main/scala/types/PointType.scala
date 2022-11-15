package types

class PointType {
  private var x = .0
  private var y = .0

  def this(x: Double, y: Double) {
    this()
    this.x = x
    this.y = y
  }

  def setX(x: Double): Unit = {
    this.x = x
  }

  def getX: Double = x

  def setY(y: Double): Unit = {
    this.y = y
  }

  def getY: Double = y

  override def toString: String = "(" + String.valueOf(this.x) + ";" + String.valueOf(this.y) + ")"

}
