package cycle_list

import types.users.UserType

import java.io.{BufferedReader, BufferedWriter, FileReader, FileWriter, IOException}
import comparator.Comparator

import scala.{::, Boolean}
import scala.language.postfixOps


/**
 * Класс циклического списка.
 * Реализованы основные методы:
 *
 * @see add(Object) вставка в конец
 * @see add(Object, int) вставка по индексу
 * @see remove(int) удаление по индексу
 * @see forEach(Iterator) итератор
 * @see forEachReverse(Iterator) итератор (обратный обход)
 * @see getByIndex(int) получение данных по индексу
 * @see getNode(int) получение узла
 * @see getLength() получение длины списка
 * @see sort(Comparator) сортировка слиянием
 * @see printList() печать списка
 * @see save(UserType, String) сохранение
 * @see load(UserType, String) загрузка
 *
 */

class CycleList {

  private var head: Node = _
  private var length: Int = 0
  private var comparator: Comparator = null


  /**
   * Класс узла списка
   * Хранит Object в виде данных
   * Указатель на следующий и предыдущий
   */
  private class Node(var data: Any) extends Serializable {
    var next: Node = _
    var prev: Node = _

    override def toString: String = data.toString
  }

  def this(comparator: Comparator) {
    this()
    this.comparator = comparator
  }

  def add(data: Any): Unit = {
    if (head == null) {
      var node: Node = new Node(data)
      node.next = node
      node.prev = node
      head = node
      length += 1
    } else {
      var tail: Node = head.prev
      var node: Node = new Node(data)
      node.next = head
      head.prev = node
      node.prev = tail
      tail.next = node
      length += 1
    }
  }

  def add(data: Any, index: Int): Unit = {
    var tmp = getNode(index)
    var newNode = new Node(data)
    var tail = head.prev
    if (tmp ne head) {
      tmp.prev.next = newNode
      newNode.prev = tmp.prev
    }
    else head = newNode
    newNode.next = tmp
    tmp.prev = newNode
    tail.next = head
    head.prev = tail
    length += 1
  }

  /**
   * Сортировка слиянием.
   * Реализовано 3 метода: (mergeSort(), merge(), getMidNode()).
   *
   * @param comparator экземпляр класса Comparator, для сравнения объектов
   * @see mergeSort(Node, Comparator) рекурсивно разделяет список
   * @see merge(Node, Node, Comparator) выполняет слияние
   * @see getMidNode(Node) находит центр списка
   * */

  def sort(comparator: Comparator): Unit = {
    if (head != null && (head.next ne head) && (head.prev ne head)) {
      var tail = head.prev
      tail.next = null
      head.prev = null
      head = mergeSort(head, comparator)
      tail = getNode(length - 1)
      tail.next = head
      head.prev = tail
    }
  }

  private def mergeSort(headNode: Node, comparator: Comparator): Node = {
    if (headNode == null || headNode.next == null) {
      return headNode
    }
    val middle = getMiddle(headNode)
    val middleNext = middle.next
    middle.next = null
    val left = mergeSort(headNode, comparator)
    val right = mergeSort(middleNext, comparator)
    merge(left, right, comparator)
  }

  private def merge(first: Node, second: Node, comparator: Comparator) = {
    var firstNode: Node = first
    var secondNode: Node = second
    val merged = new Node(null)
    var temp = merged
    var tail = head.prev
    while ( {
      firstNode != null && secondNode != null
    }) {
      if (comparator.compare(firstNode.data, secondNode.data) < 0) {
        temp.next = firstNode
        firstNode.prev = temp
        firstNode = firstNode.next
      }
      else {
        temp.next = secondNode
        secondNode.prev = temp
        secondNode = secondNode.next
      }
      temp = temp.next
    }
    while ( {
      firstNode != null
    }) {
      temp.next = firstNode
      firstNode.prev = temp
      firstNode = firstNode.next
      temp = temp.next
    }
    while ( {
      secondNode != null
    }) {
      temp.next = secondNode
      secondNode.prev = temp
      secondNode = secondNode.next
      temp = temp.next
      tail = temp
    }
    merged.next
  }

  /**
   * Сортировка рекурсивным слиянием
   * Разделение - функциональный стиль
   * Слияние - императивный стиль
   *
   * @param xs массив элементов списка
   * @param comparator
   * @return
   */
  def mergeSortFuncStyle(comparator: Comparator): CycleList = {
    if (this.length <= 1)
      this
    else {
      val sortedList = new CycleList
      var leftList = new CycleList
      var rightList = new CycleList
      val middle = this.length / 2;
      forEachListLeft(x => {
        leftList.add(x)
      }, 0, middle)

      forEachListRight(x => {
        rightList.add(x)
      }, middle, this.length)

      leftList = leftList.mergeSortFuncStyle(comparator)
      rightList = rightList.mergeSortFuncStyle(comparator)

      //Итератор
      var left = leftList.head
      var right = rightList.head
      var leftHead = 0
      var rightHead = 0

      if (leftList.length == 1 && rightList.length == 1) {
        if (comparator.compare(left.data, right.data) > 0) {
          sortedList.add(right.data)
          sortedList.add(left.data)
        }
        if (comparator.compare(left.data, right.data) <= 0) {
          sortedList.add(left.data)
          sortedList.add(right.data)
        }
      }
      else {
        while (leftHead < leftList.length && rightHead < rightList.length) {
          if (comparator.compare(left.data, right.data) > 0) {
            sortedList.add(right.data)
            right = right.next
            rightHead = rightHead + 1
          }
          if (comparator.compare(left.data, right.data) <= 0) {
            sortedList.add(left.data)
            left = left.next
            leftHead = leftHead + 1
          }
        }

        if (rightHead == rightList.length) {
          while (leftHead < leftList.length) {
            sortedList.add(left.data)
            left = left.next
            leftHead = leftHead + 1
          }
        }

        if (leftHead == leftList.length) {
          while (rightHead < rightList.length) {
            sortedList.add(right.data)
            right = right.next
            rightHead = leftHead + 1
          }
        }
      }
      sortedList
    }
  }
  private def getMiddle(h: Node) = {
    var fast = h.next
    var slow = h
    while (fast != null) {
      fast = fast.next
      if (fast != null) {
        slow = slow.next
        fast = fast.next
      }
    }
    slow
  }

  def remove(index: Int): Unit = {
    var tmp = getNode(index)
    var tail: Node = head.prev
    if (tmp != head) {
      tmp.prev.next = tmp.next
    }
    else {
      head = tmp.next
    }
    if (tmp != tail) {
      tmp.next.prev = tmp.prev
    }
    else {
      tail = tmp.prev
    }
    tmp.next = null
    tmp.prev = null
    tail.next = head
    head.prev = tail
    length -= 1
  }

  def getByIndex(index: Int): Any = getNode(index).data

  def getLength: Int = length

  private def getNode(index: Int): Node = {
    if (index < 0 || index >= length) throw new IndexOutOfBoundsException()
    var tmp = head
    for (_ <- 0 until index) {
      tmp = tmp.next
    }
    tmp
  }

  /**
   * Обход циклического списка с головного элемента,
   * проход до головного включительно
   *
   * @param iterator
   */
  def forEach(iterator: Any => Unit): Unit = {
    var tmp = head
    for (i <- 0 until length) {
      iterator(tmp.data)
      tmp = tmp.next
    }
  }

  def forEachListLeft(iterator: Any => Unit, idBegin: Int, idEnd: Int): Unit = {
    var tmp = head
    for (i <- idBegin until idEnd) {
      iterator(tmp.data)
      tmp = tmp.next
    }
  }

  def forEachListRight(iterator: Any => Unit, idBegin: Int, idEnd: Int): Unit = {
    var tmp = head
    for (i <- 0 until idBegin) {
      tmp = tmp.next
    }
    for (i <- idBegin until idEnd) {
      iterator(tmp.data)
      tmp = tmp.next
    }
  }


  /**
   * Обход циклического списка, в обратном направлении,
   * проход до головного включительно
   *
   * @param iterator
   */
  def forEachReverse(iterator: Any => Unit): Unit = {
    var tmp = head
    for (i <- 0 until length) {
      iterator(tmp.data)
      tmp = tmp.prev
    }
  }

  def printList(): Unit = {
    var tmp = head
    for (i <- 0 until length) {
      System.out.print(i + ") ")
      System.out.println(tmp.data)
      tmp = tmp.next
    }
  }

  override def toString: String = {
    var str = ""
    var tmp: Node = head
    for (i <- 0 until length) {
      str = str + tmp.data + "\n"
      tmp = tmp.next
    }
    str
  }

  def clearList(): Unit = {
    head = null
    length = 0
  }

  /**
   * Сохранение в файл
   *
   * @param userType тип данных
   * @param fileName название файла для загрузки
   */
  def save(userType: UserType, fileName: String): Unit = {
    try {
      val writer = new BufferedWriter(new FileWriter(fileName))
      try {
        writer.write(userType.typeName + "\n")
        this.forEach((el: Any) => {
          def foo(el: Any) = {
            try writer.write(userType.toString(el) + "\n")
            catch {
              case e: IOException =>
                e.printStackTrace()
            }
          }

          foo(el)
        })
      } catch {
        case e: IOException =>
          e.printStackTrace()
      } finally if (writer != null) writer.close()
    }
  }

  /**
   * Загрузка из файла
   *
   * @param userType тип данных
   * @param fileName название файла для загрузки
   */
  def load(userType: UserType, fileName: String): Unit = {
    clearList()
    val br = new BufferedReader(new FileReader(fileName))
    var line: String = null
    line = br.readLine
    if (!(userType.typeName == line)) throw new Exception("Wrong file structure")
    line = br.readLine
    while (line != null) {
      add(userType.parseValue(line))
      line = br.readLine
    }
  }

}
