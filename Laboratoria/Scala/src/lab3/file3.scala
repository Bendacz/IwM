package lab3

import scalafx.application.JFXApp
import scalafx.Includes._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.scene._
import scalafx.scene.paint._
import scalafx.scene.text._
import scalafx.event.ActionEvent
import scalafx.scene.Parent._
import scalafx.geometry._

object file3 extends JFXApp{
  
  stage = new JFXApp.PrimaryStage{
    title = "Simple temperature converter Program"
    scene = new Scene(390, 400) {
      resizable = false
      fill = Color.grayRgb(0,0.0431)
      
      val subScene1 = new SubScene(400, 60) {
        val label1 = new Label {
          text = "Celsius to Fahrenheit converter"
          font = new Font(18)
          alignment = Pos.Center
          prefHeight = 10
          prefWidth = 50
        }
        root = label1
      }
      
      val textField1 = new TextField {
        layoutX = 50
        layoutY = 80
        prefWidth = 100
        prefHeight = 20
      }
      
      val textField2 = new TextField {
        layoutX = 255
        layoutY = 80
        prefWidth = 100
        prefHeight = 20
        editable = false
      }
      
      val button1 = new Button {
        font = new Font(14)
        layoutX = 155
        layoutY = 140
        text = "Convert"
        prefWidth = 100
        prefHeight = 30
      }
      
      button1.onAction = (e:ActionEvent) => {
        val temp = textField1.getText()
        val C = temp.toDouble
        val F = 1.8*C +32
        textField2.setText(F.toString)
      }
      
      val subScene2 = new SubScene(400, 60) {
        layoutY = 210
        val label2 = new Label {
          text = "Fahrenheit to Celsius converter"
          font = new Font(18)
          alignment = Pos.Center
        }
        root = label2
      }
      
      val textField3 = new TextField {
        layoutX = 50
        layoutY = 290
        prefWidth = 100
        prefHeight = 20
      }
      
      val textField4 = new TextField {
        layoutX = 255
        layoutY = 290
        prefWidth = 100
        prefHeight = 20
        editable = false
      }
      
      val button2 = new Button {
        font = new Font(14)
        layoutX = 155
        layoutY = 350
        text = "Convert"
        prefWidth = 100
        prefHeight = 30
      }
      
      button2.onAction = (e:ActionEvent) => {
        val temp = textField3.getText()
        val F = temp.toDouble
        val C = (F-32)/1.8
        textField4.setText(C.toString)
      }
      
      content = List(subScene1, textField1, textField2, button1, subScene2, textField3, textField4, button2)
    }
  }
}