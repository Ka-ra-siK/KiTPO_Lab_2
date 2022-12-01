package types.users

import java.io.InputStreamReader
import comparator.Comparator

/**
 * Интерфейс для создания объектов,
 * пользавательских типов данных.
 *
 * @see UserType#typeName() Получение имя типа
 * @see UserType#create() Создание объекта
 * @see UserType#clone(Object) Клонирование текущего объекта
 * @see UserType#readValue(InputStreamReader) Чтение объекта
 * @see UserType#parseValue(String) Парсинг содержимого из стоки
 * @see UserType#getTypeComparator() Получение экземпляра компаратора
 */
trait UserType {
  def typeName: String

  def create: Any
  def create(num: Any): Any

  def clone(`object`: Any): Any

  def readValue(in: InputStreamReader): Any

  def parseValue(ss: String): Any

  def getTypeComparator: Comparator

  def toString(`object`: Any): String


}

