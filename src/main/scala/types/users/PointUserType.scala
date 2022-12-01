package types.users

import types.PointType

import java.io.InputStreamReader
import comparator.{Comparator, PointComparator}

import java.util.Random
import java.util.regex.{Matcher, Pattern}

class PointUserType extends UserType {

  private val MAX = 10.0F
  private val MIN = -10.0F
  private val REGULAR_EXPRESSION = "\\(([-]?[0-9]+(?:[.,][0-9]+){0,1});([-]?[0-9]+(?:[.,][0-9]+){0,1})\\)"

  override def typeName: String = "Point"

  override def create: Any = {
    val random = new Random
    val x = random.nextDouble(MAX - MIN) + MIN
    val y = random.nextDouble(MAX - MIN) + MIN
    val pointTypeValue = new PointType(x, y)
    pointTypeValue
  }

  override def clone(`object`: Any): Any = {
    val copyPointTypeValue = new PointType(`object`.asInstanceOf[PointType].getX, `object`.asInstanceOf[PointType].getY)
    return copyPointTypeValue
  }

  override def readValue(in: InputStreamReader): Any = {
    return in.read
  }

  override def parseValue(pointString: String): Any = {
    val patternString = Pattern.compile(REGULAR_EXPRESSION)
    val matcher = patternString.matcher(pointString)
    if (matcher.find) {
      val pointType = new PointType((matcher.group(1)).toDouble, (matcher.group(2)).toDouble)
      return pointType
    }
    else {
      return null
    }
  }

  override def getTypeComparator: Comparator = {
    val comparator = new PointComparator
    return comparator
  }

  override def toString(`object`: Any): String = `object`.toString

  override def create(num: Any): Any = 1
}
