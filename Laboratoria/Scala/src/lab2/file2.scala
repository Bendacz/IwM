package lab2

object file2 extends App {
  
  //EX1---------------------------------------------------------------------------------------------------
  /*//przykładowa klasa z polami xc i yc, oraz metodą move(Int, Int)
  class Point(xc: Int, yc: Int) {
    var x: Int = xc
    var y: Int = yc
    
    def move(dx: Int, dy: Int) {
      x = x + dx
      y = y + dy
      println ("Point x location : " + x);
      println ("Point y location : " + y);
    }
  }
  
  //modyfikator private
 class Outer1 {
    class Inner1 {
      private val x = 0
      private def f() { println("f") }
      
      class InnerMost1 {
        println(x) //ok - dostęp do pola prywatnego z poziomu klasy zagnieżdżonej
        f() //ok - dostęp do metody prywatnej z poziomu klasy zagnieżdżonej
      }
    }
    val foo1 = new Inner1
    //println(foo1.x) //błąd - brak dostępu z zewnątrz do pola prywatnego
    //foo1.f() //błąd - brak dostępu z zewnątrz do metody prywatnej
  }
  
  class Super1 {
    private val x = 0
    private def f() { println("f") }
  }
  
  class Sub1 extends Super1 {
    //println(x) //błąd - brak dostępu do pola prywatnego z poziomu klasy dziedziczącej
    //f() //błąd - brak dostępu do metody prywatnej z poziomu klasy dziedziczacej
  }
   
  class Other1 {
    val foo1 = new Super1
    //println(foo1.x) //błąd - brak dostępu z zewnątrz do pola prywatnego
    //foo1.f() //błąd - brak dostępu z zewnątrz do metody prywatnej
  }

  //modyfikator protected
  class Outer2 {
    class Inner2 {
      protected val x = 0
      protected def f() { println("f") }
      
      class InnerMost2 {
        println(x) //ok - dostęp do pola chronionego z poziomu klasy zagnieżdżonej
        f() //ok - dostęp do metody chronionej z poziomu klasy zagnieżdżonej
      }
    }
    val foo2 = new Inner2
    //println(foo2.x) //błąd - brak dostępu z zewnątrz do pola chronionego
    //foo2.f() //błąd - brak dostępu z zewnątrz do metody chronionej
  }
  
  class Super2 {
    protected val x = 0
    protected def f() { println("f") }
  }
  
  class Sub2 extends Super2 {
    println(x) //ok - dostęp do pola chronionego z poziomu klasy dziedziczącej
    f() //ok - dostęp do metody chronionej z poziomu klasy dziedziczacej
  }
   
  class Other2 {
    val foo1 = new Super2
    //println(foo2.x) //błąd - brak dostępu z zewnątrz do pola chronionego
    //foo2.f() //błąd - brak dostępu z zewnątrz do metody chronionej
  }
  
  //modyfikator public
  class Outer3 {
    class Inner3 {
      val x = 0
      def f() { println("f") }
      
      class InnerMost3 {
        println(x) //ok - dostęp do pola publicznego z poziomu klasy zagnieżdżonej
        f() //ok - dostęp do metody publicznego z poziomu klasy zagnieżdżonej
      }
    }
    val foo3 = new Inner3
    println(foo3.x) //ok - dostęp z zewnątrz do pola publicznego
    foo3.f() //ok - dostęp z zewnątrz do metody publicznej
  }
  
  class Super3 {
    val x = 0
    def f() { println("f") }
  }
  
  class Sub3 extends Super3 {
    println(x) //ok - dostęp do pola publicznego z poziomu klasy dziedziczącej
    f() //ok - dostęp do metody publicznej z poziomu klasy dziedziczacej
  }
   
  class Other3 {
    val foo3 = new Super3
    println(foo3.x) //ok - dostęp z zewnątrz do pola publicznego
    foo3.f() //ok - dostęp z zewnątrz do metody publicznej
  }*/
  
  //EX2----------------------------------------------------------------------------------------------------
  /*//object Person(firstName: String, lastName:String, age: Int) {
    //def greet() {
      //println(s"Hello, I'm $firstName $lastName and I am $age years old. Nice to meet you.")
    //}
  //} //bląd - obiekt nie może przyjmować parametrów
  
  object JakubBenduch{
    val firstName = "Jakub"
    val lastName = "Benduch"
    def fullName: String = firstName + " " + lastName
    def greet():String = {
      "Hello, my name is " + fullName + "."
    }
  }
  
  class Person(firstName: String, lastName: String, age: Int) {
    def greet() {
      println(s"Hello, I'm $firstName $lastName and I am $age years old. Nice to meet you.")
    }
  }
  
  //object JakubBenduch{} //błąd - nie mogą istnieć dwa obiekty Scali o takiej samej nazwie
  val a = new Person("Jakub", "Benduch", 23)
  val b = new Person("Małgosia", "Dąbrowa", 21)
  val c = new Person("Martin", "Odersky", 50)
  a.greet()
  b.greet()
  c.greet()
  
  object ex1{
    def main(args: Array[String]) {
        println("Hello, world")
    }
  }
  
  class ex2{
    def main(args: Array[String]) {
        println("Hello, world")
    }
  } //jest możliwe utworzenie klasy z metodą main, jednak po kompilacji program nie zadziała*/
  
  //EX3----------------------------------------------------------------------------------------------------
  /*class Point(xc: Int, yc: Int) {
    val x: Int = xc
    val y: Int = yc
  }
  
  case class Note(name: String, duration: String, octave: Int)
  
  val point1 = new Point(1,1)
  //val point2 = Point(1,1) //błąd, bo nie ma słowa "new"
  val note1 = Note("C", "Quarter", 3) //brak "new" ale nie zwraca komunikatu o błędzie
  val note2 = new Note("C", "Quarter", 3) //stosowanie "new" nie jest zabronione, ale jest niepraktykowane
  
  val point3 = new Point(1,1)
  println(point1 == point3) //zwraca fałsz
  println(note1 == note2) //zwraca prawdę
  
  //point1 match {
    //case Point(xc, yc) => s"The xc of point1 is $xc" //klasa regularna nie może być wykorzystana w mechnizmie Pattern Matching
  //}
  
  note1 match {
    case Note(name, duration, octave) => s"The duration of note1 is $duration" //case class jest wykorzystywana w Pattern Matching
  }
  
  class Point3D(xc: Int, yc: Int, zc: Int) extends Point(xc, yc) {
    val x: Int = xc
    val y: Int = yc
    val z: Int = zc
  }
  
  //case class BigNote(name: String, duration: String, octave: Int, size: Int) extends Note(name: String, duration: String, octave: Int) //błąd - zabronione*/
  
  //EX4----------------------------------------------------------------------------------------------------
  /*abstract class Xcor(xc: Int) {
    val x: Int = xc
  }
  
  abstract class Ycor(yc: Int) {
    val y: Int = yc
  }
  
  abstract class Zcor(zc: Int) {
    val z: Int = zc
  }
  
  trait Rang {
    val R_angle: Int 
  }
  
  trait Pang {
    val P_angle: Int
  }
  
  trait Yang {
    val Y_angle: Int
  }
  
  //class LocationByAbstractClasses(xc: Int, yc: Int, zc: Int) extends Xcor(xc: Int), Ycor(yc: Int), Zcor(zc: Int){
    //val x: Int = xc
    //val y: Int = yc
    //val z: Int = zc
  //} //Nie jest możliwe dziedziczenie od wielu klas abstrakcyjnych
  
  class OrientationByTraits extends Rang with Pang with Yang {
    val R_angle = 20
    val P_angle = 10
    val Y_angle = 150
  } //klasa może korzystać z wielu cech
  
  abstract class Location(xc: Int, yc: Int, zc: Int) {
    val x: Int = xc
    val y: Int = yc
    val z: Int = zc
  }
  
  class LocationAndOrientation(xc: Int, yc: Int, zc: Int) extends Location(xc: Int, yc: Int, zc: Int) with Rang with Pang with Yang {
    val x: Int = xc
    val y: Int = yc
    val z: Int = zc
    val R_angle = 20
    val P_angle = 10
    val Y_angle = 150
  } //klasa może rozszerzać jedną klasę abstrakcyjną oraz wiele cech
  
  abstract class ClassWithoutConstructorParameters(){}
  abstract class ClassWithConstructorParameters(parameter1: Any, parameter2: Any){}
  trait TraitWithoutConstructorParameters {}
  //trait TraitWithConstructorParameters(parameter: Any) {}; //błąd - cecha nie może przyjmować parametrów konstruktora*/
  
  //EX5----------------------------------------------------------------------------------------------------
  /*class A {
    val firstAInt = 1
    val secondAInt = 2
    private val thirdADouble = 1.23
    def greetClass():Unit = {
      println("Hello from class A")
    }
  }
  
  trait firstTrait {
    val firstTraitDouble = 77.7
    val firstTraitString = "I'm first trait"
    def greetTrait():Unit = {
      println("Hello from first Trait")
    }
  }
    
  class B extends A with firstTrait {
    override val firstAInt = 32
    //val thirdA = 4.32
  }
  
  trait secondTrait extends firstTrait {
    val secondTraitChar = 'K'
    override def greetTrait():Unit = {
      println("Hello from second Trait")
    }
  }
  
  class C extends A with firstTrait with secondTrait {
    val firstCDouble = 4.5234
    override def greetClass():Unit = {
      println("Hello from class C")
    }
  }
  
  trait thirdTrait {
    val thirdTraitLong = 7.5e31
    def greetTrait():Unit = {
      println("Hello from third Trait")
    }
  }
  
  //class D extends C with thirdTrait { //błąd - dwa razy ta sama metoda greetTrait() - jedna od secondTrait z klasy C, druga od thirdTrait
    //def greetClass():Unit = {
      //println("Hello form class D")s
    //} //błąd - nie można stworzyć takiej samej metody bez słowa kluczowego "override"
  //}
  
  class D extends C with firstTrait {
    val firstDLong = 4.21e21
    override def greetClass():Unit = {
      println("Hello from class D")
    }
  }
  
  class E extends C {}
  
  class F extends A {}
  
  class G extends B {}
  
  val a = new B
  println(a.firstAInt) //wyświetla 32
  //println(a.thirdA) //błąd - thirdA jest polem prywatnym klasy A
  a.greetClass() //"wyświetla "Hello from class A"
  a.greetTrait() //wyświetla "Hello from first Trait"
  println("------------------------------------------------------------------------------------------------------------------")
  
  val b = new C
  println(b.secondTraitChar) //wyświetla K
  b.greetClass() //wyświetla "Hello from class A"
  b.greetTrait() //wyświetla "Hello from second Trait"
  println("------------------------------------------------------------------------------------------------------------------")
  
  val c = new D
  println(c.firstAInt) //wyświetla 1
  println(c.secondAInt) //wyświetla 2
  println(c.firstCDouble) //wyświetla 4.5234
  println(c.firstDLong) // wyświetla 4.21E21
  c.greetClass() //wyświetla "Hello from class D"
  c.greetTrait() //wyświetla "Hello from second Trait"
  println("------------------------------------------------------------------------------------------------------------------")
  
  val d = new E
  d.greetClass() //wyświetla "Hello from class C"
  d.greetTrait() //wyświetla "Hello from second Trait"
  println("------------------------------------------------------------------------------------------------------------------")
  
  val e = new F
  e.greetClass() //wyświetla "Hello from class A"
  //e.greetTrait() //błąd - klasa F nie rozszerza żadnej cechy
  
  /* 			A
   * 		/	|	\
   * 	/		|		\
   * 	B		F		C
   * 	|			/		\
   * 	G			D		 E
   */
  
  //polimorfizm
  trait Animal {
    def makeVoice(): Unit
  }
  
  class Cat extends Animal{
    def makeVoice(): Unit = println("Meow")
  }
  class Dog extends Animal{
    def makeVoice(): Unit = println("Woof")
  }

  class Box(animal:Animal) {
    def makeSound() = animal.makeVoice()
  }
  
  val cat = new Cat
  val dog = new Dog
  val box1 = new Box(cat)
  val box2 = new Box(dog)
  box1.makeSound() //wyświetla "Meow" 
  box2.makeSound() //wyświetla "Woof"*/
  
  //EX6----------------------------------------------------------------------------------------------------
  /*val a1 = new A
  val a2 = new A
  val b1 = new B
  val b2 = new B
  val c1 = new C
  val c2 = new C
  val d1 = new D
  val d2 = new D
  val e1 = new E
  val e2 = new E
  val f1 = new F
  val f2 = new F
  val g1 = new G
  val g2 = new G
  
  val list = List(a1,a2,b1,b2,c1,c2,d1,d2,e1,e2,f1,f2,g1,g2)
  val vector = Vector(a1,a2,b1,b2,c1,c2,d1,d2,e1,e2,f1,f2,g1,g2)
  val set = Set(a1,a2,b1,b2,c1,c2,d1,d2,e1,e2,f1,f2,g1,g2)
  val map = Map(1->a1, 2-> a2, 3->b1, 4->b2, 5->c1, 6->c2, 7->d1, 8->d2, 9->e1, 10->e2, 11->f1, 12->f2, 13->g1, 14->g2)
  val tuple = Tuple14(a1,a2,b1,b2,c1,c2,d1,d2,e1,e2,f1,f2,g1,g2)
  
  println(list)
  println(set)
  println(map)
  println(tuple)*/
  
  //EX7----------------------------------------------------------------------------------------------------
  /*val s = Set("red","blue","orange","blue", "brown", "red") //powtarzające się stringi zostały usunięte
  val m = Map(1 -> "red", 2 -> "blue", 3 -> "orange", 4 -> "blue", 5 -> "brown", 6 -> "red") //powtarzające się wartości, ale to dopuszczalne, bo ich klucze są różne
  val m1 = Map(1 -> "red", 2 -> "blue", 3 -> "orange", 1 -> "blue") //nadpisanie pary 1 -> "red" parą 1 -> "blue"
  
  println(s) //wyświetla Set(red, blue, orange, brown)
  println(m) //wyświetla Map(5 -> brown, 1 -> red, 6 -> red, 2 -> blue, 3 -> orange, 4 -> blue)
  println(m1) //wyświetla Map(1 -> blue, 2 -> blue, 3 -> orange)*/
  
  //EX8----------------------------------------------------------------------------------------------------
  /*def matchValue(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
  println(matchValue(1) + " " + matchValue(2) + " " + matchValue(3)) //wyświetla one two many
  
  /*class SomeClass(name: String, id: Int)
  object SomeObject1
  object SomeObject2
  
  def matchObject(obj: Any): Any = obj match {
    case SomeObject1 => println("Object1")
    case SomeObject2 => println("Object2")
    case _ => println("What?")
  }
  matchObject(SomeObject1)
  matchObject(SomeObject2)
  matchObject("Boring")
  matchObject(2.34)*/
  
  abstract class Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification
  def matchObject(notification: Notification): String = notification match {
      case Email(email, title, body) =>
        s"You got an email from: $email \nTitle: $title \nContent:\n$body"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"you received a Voice Recording from $name! Click the link to hear it: $link"
    }
  
  val someSms = SMS("Frank", "Are you there?")
  val someEmail = Email("Tom", "WR OLYH LV WR GLH", "ZKHQ D PDQ OLHV KH PXUGHUV VRPH SDUW RI WKH ZRUOG \nWKHVH DUH WKH SDOH GHDWKV ZKLFK PHQ PLVFDOO WKHLU OLYHV \nDOO WKLV L FDQQRW EHDU WR ZLWQHVV DQB ORQJHU \nFDQQRW WKH NLQJGRP RI VDOYDWLRQ WDNH PH KRPH")
  println(matchObject(someSms))
  println(matchObject(someEmail))

  def matchType(x: Any): Any = x match {
      case i: Int => println("Integer: " + i)
      case s: String => println("String: " + s)
      case f: Double => println("Double: " + f)
      case other => println("other: " + other)
  }
  matchType(1)
  matchType("Hello")
  matchType(2.0)
  matchType('k')*/
  
  //EX9=---------------------------------------------------------------------------------------------------
  /*val t1 = ("Hello", 3.4, 't', 5, Console)
  val t2 = new Tuple5("Hello", 3.4, 't', 5, Console) //to samo co powyżej z użyciem konstruktora
  val t3 = (1,2,3,4)
  val sum = t3._1 + t3._2 + t3._3 + t3._4 //sumowanie elementów
  println("Suma: " + sum)
  t3.productIterator.foreach{ i => println("Value = " + i)} //iterowanie zawartości krotki
  println("Połączone stringi: " + t2.toString()) //metoda toString()
  val t4 = ("Hello","World")
  println("Zmieniona kolejność: " + t4.swap) //zamiana kolejności, koniecznie ten sam typ danych
  
  println("Pattern matching on Tuples")
  val strawberryDonut = Tuple3("Strawberry Donut", "Very Tasty", 2.50)
  val glazedDonut = Tuple3("Glazed Donut", "Very Tasty", 2.50)
  val plainDonut = Tuple3("Plain Donut", "Tasty", 2)

  val donutList = List(glazedDonut, strawberryDonut, plainDonut)

  val priceOfPlainDonut = donutList.foreach { tuple => {
    tuple match {
      case ("Plain Donut", taste, price) => println(s"Donut type = Plain Donut, price = $price")
      case d if d._1 == "Glazed Donut" => println(s"Donut type = ${d._1}, price = ${d._3}")
      case _ => None
      }
    }
  }*/
  
  //EX10---------------------------------------------------------------------------------------------------
  /*//zad1
  rectangle(4,4)
  def rectangle(row:Int , col:Int):Unit = {
    val list = List.range(1, row+1).map(n => println(List.fill(col)("*").reduce(_+_)))
  }
  
  //zad2
  import scala.math._
  val array = Array((2.0,log(521.0)),(128.0,422.0)) map { x => x._1 * x._2 }
  val list = 7.5::List.fill(8)(10.0)
  val w = pow(List(array(0),List(list.reduce(_*_),array(1)).reduce(_/_)).reduce(_+_),1.0/3.0)
  println(w) //liczenie tego w ten sposób to chyba trochę sztuka dla sztuki
  
  //zad3
  val v = Vector.range(100,1000)
  check(v)
  def check(v: Vector[Int]):Any = {
    for { i <- v 
      val list = List((i/100)%10, (i/10)%10, i%10).map(x=>pow(x,3)).reduce(_+_)
    }
    if (i == list) println(list)
  }*/
  
  //EX11---------------------------------------------------------------------------------------------------
  /*import scala.math._
  val v = Vector.range(100,1000)
  print(pow3sum(v))
  def pow3sum(v:Vector[Int]):Vector[Int] = {
    var result = Vector[Int]()
    for {
      n <- v
      if (n == (pow((n/100)%10,3) + pow((n/10)%10,3) + pow(n%10,3)))
    } result = result :+ n
    result
  }*/
    
  //EX12---------------------------------------------------------------------------------------------------
  /*println(sumIt(List(10, 20, 30, 40, 50))) //wynik: 150
  def sumIt(toSum:List[Int], sum:Int= 0):Int = {
    if(toSum.isEmpty)
      sum
    else
      sumIt(toSum.tail, sum + toSum.head)
  }

  println(silnia(5)) //wynik: 120
  def silnia(x:Int):Int = {
    if (x < 2) {
      return 1
    }
    else {
      return x*silnia(x-1)
    }
  }*/

}