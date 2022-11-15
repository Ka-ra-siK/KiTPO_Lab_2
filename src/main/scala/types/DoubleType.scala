package types

class DoubleType {
  private var doubleTypeValue = .0

  def this(doubleTypeValue: Double) {
    this()
    this.doubleTypeValue = doubleTypeValue
  }

  def getDoubleTypeValue: Double = this.doubleTypeValue

  def setDoubleTypeValue(doubleTypeValue: Double): Unit = {
    this.doubleTypeValue = doubleTypeValue
  }

  override def toString: String = String.valueOf(doubleTypeValue)

}
