package cycle_list

trait Iterator[T] {
  def toDo(data: T): Unit
}
