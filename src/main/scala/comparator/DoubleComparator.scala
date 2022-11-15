package comparator

import types.DoubleType

class DoubleComparator extends Comparator{
  override def compare(o1: Any, o2: Any): Double =
    o1.asInstanceOf[DoubleType].getDoubleTypeValue - o2.asInstanceOf[DoubleType].getDoubleTypeValue

}
