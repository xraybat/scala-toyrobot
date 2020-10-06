package orientation

object Orientation extends Enumeration {
  type Orientation = Value
  
  val North = Value("NORTH")
  val East = Value("EAST")
  val South = Value("SOUTH")
  val West = Value("WEST")
}
