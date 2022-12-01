package types.users

import comparator.{Comparator, DoubleComparator}
import types.DoubleType

import java.io.{IOException, InputStreamReader}
import java.util.Random

class DoubleUserType extends UserType {
  private val MAX = 1000.0
  private val MIN = -1000.0

  override def typeName = "Double"

  override def create: Any = {
    val random = new Random
    val doubleTypeValue = new DoubleType(random.nextDouble(MAX - MIN) + MIN)
    doubleTypeValue
  }

  override def create(num:Any): Any = {
    val doubleTypeValue = new DoubleType(num.toString.toDouble)
    doubleTypeValue
  }

  override def clone(`object`: Any): Any = {
    val copyDoubleTypeValue = new DoubleType(`object`.asInstanceOf[DoubleType].getDoubleTypeValue)
    copyDoubleTypeValue
  }

  override def readValue(in: InputStreamReader): Any = try in.read
  catch {
    case e: IOException =>
      throw new RuntimeException(e)
  }

  override def parseValue(doubleString: String) = new DoubleType(doubleString.toDouble)


  override def getTypeComparator: Comparator = {
    val comparator = new DoubleComparator
    return comparator
  }

  override def toString(`object`: Any): String = `object`.toString


}
