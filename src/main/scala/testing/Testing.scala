package testing

import cycle_list.CycleList
import factory.UserFactory
import types.users.{DoubleUserType, PointUserType, UserType}

class Testing {

  //var userFactory : UserFactory
 // var userType : UserType
  //var cycleList : CycleList

  private val Double_FILE_SAVE = "saveDouble.dat"
  private val POINT_FILE_SAVE = "savePoint.dat"

  def testDoubleType(): Unit = {
    val userFactory = new UserFactory
    System.out.println("\n--------------TEST FOR Double-------------")
    var userType = userFactory.getBuilderByName("Double")
    var cycleList = new CycleList(userType.getTypeComparator)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    System.out.println("---------PRINT CYCLE LIST---------")
    cycleList.printList()
    System.out.println("-----SAVE TO FILE .DAT----")
    try {
      cycleList.save(userType, Double_FILE_SAVE)
      System.out.println("Saving is successful!")
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
    System.out.println("\n----GET NODE BY INDEX 3, 4, 5, 6----")
    System.out.println("3) = " + cycleList.getByIndex(3).toString)
    System.out.println("4) = " + cycleList.getByIndex(4).toString)
    System.out.println("5) = " + cycleList.getByIndex(5).toString)
    System.out.println("6) = " + cycleList.getByIndex(6).toString)
    System.out.println("\n---DELETE NODE BY INDEX 3, 4, 5, 6--")
    cycleList.remove(3)
    cycleList.remove(3)
    cycleList.remove(3)
    cycleList.remove(3)
    cycleList.printList()
    System.out.println("-----------SORTING----------")
    cycleList.sort(userType.getTypeComparator)
    cycleList.printList()
    System.out.println("---LOAD FROM FILE----")
    cycleList.load(userType, Double_FILE_SAVE)
    cycleList.printList()
    System.out.println("-----------SORTING BY FUNC STYLE----------")
    cycleList.sortFuncStyle(userType.getTypeComparator)
    cycleList.printList()
    System.out.println("---------ITERATOR-----------")
    cycleList.forEach(System.out.println)
    System.out.println("---------ITERATOR REVERSE-----------")
    cycleList.forEachReverse(System.out.println)
  }

  def testPointType(): Unit = {
    val userFactory = new UserFactory
    System.out.println("\n--------------TEST FOR Point-------------")
    var userType = userFactory.getBuilderByName("Point")
    var cycleList = new CycleList(userType.getTypeComparator)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    cycleList.add(userType.create)
    System.out.println("---------PRINT CYCLE LIST---------")
    cycleList.printList()
    System.out.println("-----SAVE TO FILE .DAT----")
    try {
      cycleList.save(userType, POINT_FILE_SAVE)
      System.out.println("Saving is successful!")
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
    System.out.println("\n----GET NODE BY INDEX 3, 4, 5, 6----")
    System.out.println("3) = " + cycleList.getByIndex(3).toString)
    System.out.println("4) = " + cycleList.getByIndex(4).toString)
    System.out.println("5) = " + cycleList.getByIndex(5).toString)
    System.out.println("6) = " + cycleList.getByIndex(6).toString)
    System.out.println("\n---DELETE NODE BY INDEX 3, 4, 5, 6--")
    cycleList.remove(3)
    cycleList.remove(4)
    cycleList.remove(5)
    cycleList.remove(6)
    cycleList.printList()
    System.out.println("-----------SORTING----------")
    cycleList.sort(userType.getTypeComparator)
    cycleList.printList()
    System.out.println("---LOAD FROM FILE----")
    cycleList.load(userType, POINT_FILE_SAVE)
    cycleList.printList()
    System.out.println("-----------SORTING BY FUNC STYLE----------")
    cycleList.sortFuncStyle(userType.getTypeComparator)
    cycleList.printList()
    System.out.println("---------ITERATOR-----------")
    cycleList.forEach(System.out.println)
    System.out.println("---------ITERATOR REVERSE-----------")
    cycleList.forEachReverse(System.out.println)
  }

}
