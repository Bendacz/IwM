package lab1

object file1 extends App{
  
  //EX1----------------------------------------------------------------------------------------------------
  /*rectangle(3,8) 
  def rectangle(row:Int, col:Int): Unit = for {i <- 1 to row} println("*"*col)*/
  
  //EX2----------------------------------------------------------------------------------------------------
  /*import scala.math._
  val w = pow(2.0*log(521.0) + 7.5e8/(128.0*422.0),1.0/3.0)
  printf("Wartość wyrażenia: %f",w) // Wartość wyrażenia: 24,042336*/
  
  //EX3----------------------------------------------------------------------------------------------------
  /*import scala.math._
  val x = List.range(100,1000)
  check(x) // 153, 370, 371, 407
  def check(x:List[Int]): Any = {
    if (!x.isEmpty){
      val v:Int = x.head
      if (v == (pow((v/100)%10,3) + pow((v/10)%10,3) + pow(v%10,3))){
        println(v)
      }
    check(x.tail)
    }
  }*/
  
}