import java.awt.Color;
import java.awt.Point;
import java.lang.Math;

public class CPUExpert extends CPUAdvanced {
    public Color getPaddleColor(){   
        return Color.orange;
      }
    public CPUExpert(){
        super();
    }
    
    public int determinePaddleMove(int ballX, int ballY, int ballXVel, int ballYVel, int windowHeight){
        if (ballXVel < 0){
          return super.determinePaddleMove(ballX, ballY, ballXVel, ballYVel, windowHeight);
        }
        else{
            int[][] bounces = getWallBounceCoords(ballX, ballY, ballXVel, ballYVel, windowHeight);
            if (bounces.length >= 1){
                int xcoord = bounces[bounces.length - 1][0];
                int ycoord = bounces[bounces.length - 1][1];
                if (ycoord == windowHeight){
                    ballYVel = ballYVel * -1;
                }
                int finaly = calculateTargetY(xcoord, ycoord, ballXVel, ballYVel);
                return super.determinePaddleMove(xcoord, finaly, ballXVel, ballYVel, windowHeight);
            }
            else{
                return super.determinePaddleMove(ballX, ballY, ballXVel, ballYVel, windowHeight);
            }
            }       
        }
    }
