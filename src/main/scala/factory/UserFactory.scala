package factory

import types.users.{UserType, PointUserType, DoubleUserType}
class UserFactory {
  val typeList : List[UserType] = List(new DoubleUserType, new PointUserType)
  def getTypeNameList: List[String] = {
    var list: List[String] = List()
    for (t <- typeList) {
      list = list ++: List(t.typeName)
    }
    list
  }
  def getBuilderByName(name: String): UserType = {
    if (name == null) throw new NullPointerException
    for (userType <- typeList) {
      if (name == userType.typeName) return userType
    }
    throw new IllegalArgumentException
  }
}
