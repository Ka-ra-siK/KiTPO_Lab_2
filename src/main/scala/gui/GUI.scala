package gui

import cycle_list.CycleList
import factory.UserFactory
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.{Alert, Button, ComboBox, TextArea, TextField}
import scalafx.scene.paint.Color.{White}
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.text.Font

object GUI extends JFXApp {
  val userFactory = new UserFactory
  var userType = userFactory.getBuilderByName("Double")
  var cycleList = new CycleList(userType.getTypeComparator)
  var defaultType = "Double"
  val FILE_NAME_DOUBLE = "CycleListDouble.dat"
  val FILE_NAME_POINT = "CycleListPoint.dat"
  stage = new PrimaryStage {
    title = "MainMenu"
    scene = new Scene(600, 563) {

      fill = White

      val findBtn = new Button("Найти")
      findBtn.layoutX = 25
      findBtn.layoutY = 135
      findBtn.setPrefWidth(150)
      findBtn.setPrefHeight(25)

      val delBtn = new Button("Удалить")
      delBtn.layoutX = 25
      delBtn.layoutY = 220
      delBtn.setPrefWidth(150)
      delBtn.setPrefHeight(25)

      val insertByIdBtn = new Button("Вставить")
      insertByIdBtn.layoutX = 25
      insertByIdBtn.layoutY = 315
      insertByIdBtn.setPrefWidth(150)
      insertByIdBtn.setPrefHeight(25)

      val insertBtn = new Button("Сгенерировать")
      insertBtn.layoutX = 25
      insertBtn.layoutY = 435
      insertBtn.setPrefWidth(150)
      insertBtn.setPrefHeight(25)

      val sortBtn = new Button("Сортировать")
      sortBtn.layoutX = 25
      sortBtn.layoutY = 375
      sortBtn.setPrefWidth(150)
      sortBtn.setPrefHeight(25)

      val saveBtn = new Button("Сохранить")
      saveBtn.layoutX = 25
      saveBtn.layoutY = 465
      saveBtn.setPrefWidth(150)
      saveBtn.setPrefHeight(25)

      val loadBtn = new Button("Загрузить")
      loadBtn.layoutX = 25
      loadBtn.layoutY = 490
      loadBtn.setPrefWidth(150)
      loadBtn.setPrefHeight(25)

      val clearBtn = new Button("Очистить")
      clearBtn.layoutX = 25
      clearBtn.layoutY = 520
      clearBtn.setPrefWidth(150)
      clearBtn.setPrefHeight(25)

      val delByIdField = new TextField
      delByIdField.layoutX = 25
      delByIdField.layoutY = 190
      delByIdField.setPrefWidth(150)
      delByIdField.setPrefHeight(25)

      val findByIdField = new TextField
      findByIdField.layoutX = 25
      findByIdField.layoutY = 105
      findByIdField.setPrefWidth(150)
      findByIdField.setPrefHeight(25)

      val insertByIdField = new TextField
      insertByIdField.layoutX = 25
      insertByIdField.layoutY = 280
      insertByIdField.setPrefWidth(150)
      insertByIdField.setPrefHeight(25)

      val outTextField = new TextArea
      outTextField.layoutX = 190
      outTextField.layoutY = 20
      outTextField.setPrefWidth(345)
      outTextField.setPrefHeight(525)
      outTextField.setEditable(false)
      outTextField.setFont(new Font("Arial", 14))

      val factoryListItems = new ComboBox(userFactory.getTypeNameList)
      factoryListItems.layoutX = 25
      factoryListItems.layoutY = 50
      factoryListItems.setPrefWidth(150)
      factoryListItems.setPrefHeight(25)

      content = List(findBtn, delBtn,
        insertByIdBtn, insertBtn, saveBtn, loadBtn, clearBtn,
        delByIdField, findByIdField, insertByIdField, sortBtn,
        outTextField, factoryListItems)

      factoryListItems.onAction = (e: ActionEvent) => {
        var item = factoryListItems.selectionModel.apply.getSelectedItem
        if (defaultType != item) {
          defaultType = item
          userType = userFactory.getBuilderByName(defaultType)
          cycleList = new CycleList(userType.getTypeComparator)
          outTextField.setText(cycleList.toString)
        }
      }

      // обработчик добавления элемента
      insertBtn.onAction = (e: ActionEvent) => {
        cycleList.add(userType.create)
        outTextField.setText(cycleList.toString)
      }

      // обработчик удаления элемента
      delBtn.onAction = (e: ActionEvent) => {
        if (!delByIdField.getText.isEmpty) {
          cycleList.remove(Integer.parseInt(delByIdField.getText));
          outTextField.setText(cycleList.toString)
        }
        else
          new Alert(AlertType.Information) {
            title = "Ошибка!"
            headerText = "Ошибка при удалении!"
            contentText = "Введите значение индекса, не оставляйте поле пустым!"
          }.showAndWait()
      }


      //обработка вставка по индекс
      insertByIdBtn.onAction = (e: ActionEvent) => {
        if (!insertByIdField.getText.isEmpty) {
          cycleList.add(userType.create, insertByIdField.getText.toInt)
          outTextField.setText(cycleList.toString)
        }
        else
          new Alert(AlertType.Information) {
            title = "Ошибка!"
            headerText = "Ошибка при вставке по индексу!"
            contentText = "Поле пустое!"
          }.showAndWait()
      }

      // обработчик поиска элемента по индексу
      findBtn.onAction = (e: ActionEvent) => {
        if (!findBtn.getText.isEmpty) {
          val findValue = cycleList.getByIndex(findByIdField.getText.toInt).toString
          new Alert(AlertType.Information) {
            title = "Результат поиска"
            headerText = "Результат поиска по индексу"
            contentText = "Значение = " + findValue + "\nпо индексу = " + findBtn.getText
          }.showAndWait()
        }
        else
          new Alert(AlertType.Information) {
            title = "Ошибка!"
            headerText = "Ошибка при поиске!"
            contentText = "Поле пустое!"
          }.showAndWait()
      }

      sortBtn.onAction = (e: ActionEvent) => {
        cycleList.sort(userType.getTypeComparator)
        outTextField.setText(cycleList.toString)
      }

      // обработчик сохранения
      saveBtn.onAction = (e: ActionEvent) => {
        if (defaultType eq "Float") {
          cycleList.save(userType, FILE_NAME_DOUBLE)
          new Alert(AlertType.Information) {
            title = "Успех!"
            headerText = "Список успешно сохранен!"
            contentText = "Список сохранен в файл " + FILE_NAME_DOUBLE
          }.showAndWait()
        }
        else {
          cycleList.save(userType, FILE_NAME_POINT)
          new Alert(AlertType.Information) {
            title = "Успех!"
            headerText = "Список успешно сохранен!"
            contentText = "Список сохранен в файл " + FILE_NAME_POINT
          }.showAndWait()
        }
      }

      // обработчик загрузки
      loadBtn.onAction = (e: ActionEvent) => {
        if (defaultType eq "Float") {
          cycleList.load(userType, FILE_NAME_DOUBLE)
          new Alert(AlertType.Information) {
            title = "Успех!"
            headerText = "Список успешно загружен!"
            contentText = "Список загружен из файла " + FILE_NAME_DOUBLE
          }.showAndWait()
        }
        else {
          cycleList.load(userType, FILE_NAME_POINT)
          new Alert(AlertType.Information) {
            title = "Успех!"
            headerText = "Список успешно загружен!"
            contentText = "Список загружен из файла " + FILE_NAME_POINT
          }.showAndWait()
        }
        outTextField.setText(cycleList.toString)
      }

      clearBtn.onAction = (e: ActionEvent) => {
        cycleList = new CycleList(userType.getTypeComparator)
        outTextField.setText(cycleList.toString)
      }
    }
  }

}
