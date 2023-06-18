import java.awt.Color;
import java.awt.Point;

class HumanChallenging extends HumanPaddle{
    public Color getPaddleColor(){   
        return Color.cyan;
      }

    private int startingPaddleLength;
    private int paddleChange;
    
    public HumanChallenging(int startingPaddleLength, int paddleChange){
        super(Math.max(MIN_PADDLE_LENGTH, startingPaddleLength));
        this.paddleChange = Math.max(1, paddleChange);
        this.startingPaddleLength = Math.max(MIN_PADDLE_LENGTH, startingPaddleLength);
    } 

    public void goalScored(boolean didHumanScore){
        super.goalScored(didHumanScore);
        paddleLength = startingPaddleLength;
    }

    public void handleBallCollision(){
        super.handleBallCollision();
        if (paddleLength - paddleChange >= 5){
            paddleLength -= paddleChange;
        }
    }

}

