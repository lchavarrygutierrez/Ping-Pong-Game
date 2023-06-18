public class Launcher{
  
  //Set to true to enable debug mode
  //Debug mode will show the ball's next wall bounce and 
  //expected trajectories as RED SQUARES when moving towards
  //the CPU paddle.  
  //The expected trajectory will only work
  //properly if the ball is going to bounce ZERO or ONE times.
  public static final boolean DEBUG_SHOW_BOUNCES = true;  
  
  //Set to true to force the game to run at one third speed
  //useful for debugging your collisions/wall bounces!
  public static final boolean DEBUG_SLOW_MODE = false; 
  
  
  //Run this to launc the JPong Game!
  public static void main(String[] args){
    
    //Change these two values to test your different paddles!
    HumanPaddle p1 = new HumanPaddle();
    CPUPaddle p2 = new CPUExpert();
    //*******************************************************
    
    
    JPongWindow game = new JPongWindow(p1, p2);
    game.playGame();
  }
  

  
  
}