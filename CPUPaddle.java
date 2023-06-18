import java.awt.Color;
import java.awt.Point;

//Basic CPUPaddle
//If ball is moving towards human, move to center of screen
//Otherwise, move to match ball's y coordinate
public class CPUPaddle{
  
  //Paddle length, speed, and color
  private static final int CPU_DEFAULT_LENGTH = 50;
  private static final int CPU_DEFAULT_SPEED = 7;
  
  //Helpful values for the various CPU paddles' determinePaddleMove methods
  protected static final int MOVE_THRESHOLD = 10;  
  protected static final int MOVE_UP = -1;
  protected static final int MOVE_DOWN = 1;
  protected static final int MOVE_NEUTRAL = 0;

  
  //current location of this paddle
  //the y coordinate represents the centermost point of the paddle.
  private int x, y;
  
  //the current score of the CPU player
  private int score;
  
  //The current paddle length and paddle speed of the CPU paddle
  protected int paddleLength, paddleSpeed;
  
  
  
  //Constructor
  
  public CPUPaddle(){
    this.x = JPongWindow.CPU_DEFAULT_X;
    this.y = JPongWindow.WINDOW_HEIGHT / 2;
    this.paddleLength = CPU_DEFAULT_LENGTH;
    this.paddleSpeed = CPU_DEFAULT_SPEED;
  }
  
  
  
  //Called automatically whenever EITHER the player or CPU scores a goal.
  //argument boolean indicates if it was the CPU who scored (true) or
  //the human (false).
  public void goalScored(boolean didCPUScore){
    if (didCPUScore){
      score++;
    }
  }  
  
  
  
  //Determines the direction the paddle should move per the ball's current behavior
  //and the paddle's respective AI logic.
  //
  //Called by JPongWindow on every update of the game.
  //
  //Returns:
  //   -1 if the paddle should move UP
  //    0 if the paddle should stay in its current place
  //    1 if the paddle should move DOWN 
  public int determinePaddleMove(int ballX, int ballY, int ballXVel, int ballYVel, int windowHeight){
  if (ballXVel > 0){
    if (Math.abs(ballY - this.y) <= MOVE_THRESHOLD){
      return MOVE_NEUTRAL;
    }
    else if (ballY > this.y){
          return MOVE_DOWN;
        }
    else{
        return MOVE_UP;
      }
    }  
  else{
      if (Math.abs(windowHeight/2  - this.y) <= MOVE_THRESHOLD){
        return MOVE_NEUTRAL;
      }
      else{
        if (this.y > windowHeight / 2){
          return MOVE_UP;
        }
        else if (this.y < windowHeight / 2){
          return MOVE_DOWN;
        }
        else{
          return MOVE_NEUTRAL;
        }
      }
    }
}  
    
    
  //Given a ball at the specified coordinates at the specified speed, this function returns
  //the y coordinate that the ball will be at when it reaches this paddle's location
  //
  public int calculateTargetY(int ballStartX, int ballStartY, int xSpeed, int ySpeed){
    if (xSpeed == 0)
      return 0;
    int expectY = ((this.getX() - ballStartX) / xSpeed) * ySpeed;    
    expectY = ballStartY + expectY;
    return expectY;
  }    
  
  
  //Returns the an 2D int array of all the points where the ball will collide and bounce off the top or bottom
  //of the window given its current position and trajectory.
  //Each index of the returned array is a length 2 int[] containing the x,y coordinate of a wall/ceiling colission.
  //
  //For example, if this function returned:
  //     {{250, 0},  {380, 480}, {510, 0}}, that would mean that a ball with the provided starting coordinates/speed
  //     would bounce 3 times before it reached the CPU paddle at coordinates: (250,0), (380,480), and (510,0) 
  //
  //If an empty array is returned, the ball will not hit a ceiling/wall before reaching the CPUPaddle's x coord

  public int[][] getWallBounceCoords(int ballStartX, int ballStartY, int xSpeed, int ySpeed, int windowHeight){
    int yDiff = 0;
    int[][] toReturn = {};
    if (xSpeed <= 0 || ySpeed == 0)
      return toReturn;
    
    if (ySpeed > 0)
      yDiff = windowHeight - (ballStartY + Ball.BALL_DIAMETER);
    else 
      yDiff = ballStartY;
    int collideX = (yDiff / Math.abs(ySpeed)) * xSpeed + ballStartX;
    if (collideX > this.getX())
      return toReturn;
    if (ySpeed > 0)
      ballStartY = windowHeight - Ball.BALL_DIAMETER;
    else
      ballStartY = 0;    
    int[][] next = getWallBounceCoords(collideX, ballStartY, xSpeed, -ySpeed, windowHeight);
    toReturn = new int[next.length + 1][2];
    int ct = 1;
    for (int[] p : next)
      toReturn[ct++] = p;
    toReturn[0] = new int[]{collideX, ballStartY};
    return toReturn;
  }  
  
  
  //****************   ACCESSOR METHODS  *************
  
  public int getX(){
    return this.x;
  }
  
  //Gets the y coordinate of the centermost point of the Paddle
  public int getY(){
    return this.y;
  } 
  
  //Gets the y coordinate of the topmost point of the Paddle  
  public int getTopY(){
    return this.y - this.paddleLength / 2;
  }  
  
  //Gets the y coordinate of the bottommost point of the Paddle
  public int getBottomY(){
    return this.y + this.paddleLength / 2;
  }

  public Color getPaddleColor(){   
    return Color.green;
  }  
  
  public int getScore(){
    return score;
  }  
  
  public int getPaddleLength(){
    return this.paddleLength;    
  } 
  
  public int getPaddleSpeed(){
    return this.paddleSpeed;    
  }    
  
  //************************************************** 
  
  

  
        
  //called by JPongWindow to move this paddle

  public void movePaddle(int dir){
      this.y += (this.paddleSpeed * dir);
  }  
  
  
}