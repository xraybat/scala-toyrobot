package commands

object Commands extends Enumeration {
  type Commands = Value
  
  val Place = Value("PLACE")
  val Move = Value("MOVE")
  val Left = Value("LEFT")
  val Right = Value("RIGHT")
  val Report = Value("REPORT")
}

//import Commands._