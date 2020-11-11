package toyrobot.orientation

object Orientation extends Enumeration {
  type Orientation = Value
  
  val North = Value("NORTH")
  val East = Value("EAST")
  val South = Value("SOUTH")
  val West = Value("WEST")

  def turnLeft(o: Orientation): Orientation = {
    o match {
      case North => West
      case West => South
      case South => East
      case East => North
    }
  }
  def turnRight(o: Orientation): Orientation = {
    o match {
      case North => East
      case East => South
      case South => West
      case West => North
    }
  }
} // Orientation