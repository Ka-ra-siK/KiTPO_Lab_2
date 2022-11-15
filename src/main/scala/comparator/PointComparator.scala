package comparator

import types.PointType

class PointComparator extends Comparator {

  /**
   * Разница межды объектами вычисляется
   * с помощью разности, между длиннами векторов
   * от заданной точки до цетра координатной оси.
   * Вычисляется по формуле: sqrt(x^2+y^2).
   *
   * @param o1 первая точка
   * @param o2 вторая точка
   * @return разницу между точками
   */

  override def compare(o1: Any, o2: Any): Double = {
    val firstX = o1.asInstanceOf[PointType].getX
    val secondX = o2.asInstanceOf[PointType].getX
    val firstY = o1.asInstanceOf[PointType].getY
    val secondY = o2.asInstanceOf[PointType].getY
    getVectorLength(firstX, firstY) - getVectorLength(secondX, secondY)
  }

  /**
   * @param x Точка Х
   * @param y Точка Y
   * @return Длина вектора от точки (x;y) до координатоной оси
   */
  def getVectorLength(x: Double, y: Double): Double = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))
}
