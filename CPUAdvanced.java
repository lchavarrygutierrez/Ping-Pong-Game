import java.awt.Color;
import java.awt.Point;

class CPUAdvanced extends CPUPaddle {    
    public Color getPaddleColor(){   
        return Color.yellow;
      }
    public CPUAdvanced(){
      super();
    }

    public int determinePaddleMove(int ballX, int ballY, int ballXVel, int ballYVel, int windowHeight){
      if (ballXVel <= 0){
        return super.determinePaddleMove(ballX, ballY, ballXVel, ballYVel, windowHeight);
      }
      else{
        if (Math.abs(super.calculateTargetY(ballX, ballY, ballXVel, ballYVel) - getY()) <= MOVE_THRESHOLD){
          return MOVE_NEUTRAL;
        }
        else{
          if (getY() < super.calculateTargetY(ballX, ballY, ballXVel, ballYVel)){
              return MOVE_DOWN;
            }
          else{
            return MOVE_UP;
          }
        }
      }
    }
}
   