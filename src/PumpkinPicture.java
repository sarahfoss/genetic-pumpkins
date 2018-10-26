import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PumpkinPicture extends Pane{
	Image pumpkin;
	Image eyes;
	Image mouth;
	PumpkinDNA DNA;
	int[] genes;
	Timeline animation;
	Text score;
	
	public PumpkinPicture(PumpkinDNA DNA) throws FileNotFoundException{
		this.DNA = DNA;
		this.genes = DNA.getGenes();
		String pFile ="res/p" + genes[0] +".png";
		String mFile = "res/m" + genes[1] + ".png";
		String eFile ="res/e"+ genes[2] +".png";
		
		pumpkin = new Image(new FileInputStream(pFile));		
		ImageView pumpkinView = new ImageView(pumpkin);


		double centerX = pumpkin.getWidth()/2;
		double centerY = pumpkin.getHeight()/2;
		
		eyes = new Image(new FileInputStream(eFile));
		ImageView eyeView = new ImageView(eyes);
		
		eyeView.setX(centerX-45);
		eyeView.setY(centerY-30);
		
		mouth = new Image(new FileInputStream(mFile));
		ImageView mouthView = new ImageView(mouth);
		
		mouthView.setX(centerX-45);
		mouthView.setY(centerY+5);
		
		score = new Text(""+DNA.score);
		score.setX(centerX-15);
		score.setY(pumpkin.getHeight()-20);
		score.setFont(Font.font ("Verdana", 20));
		
		getChildren().clear();
		getChildren().addAll(pumpkinView, eyeView, mouthView, score);	
		
		animation = new Timeline(new KeyFrame(Duration.millis(50), e -> updateScore()));
		animation.setCycleCount(Timeline.INDEFINITE);
		
		
		ScaleTransition stBig = new ScaleTransition(new Duration(500), this);

	    ScaleTransition stSmall = new ScaleTransition(new Duration(500), this);
		
		setOnMouseEntered(e ->{
			
           if (stSmall.getStatus() == Animation.Status.RUNNING){
              stSmall.stop();
              stBig.setFromX(stSmall.getNode().getScaleX());
              stBig.setFromY(stSmall.getNode().getScaleY());
           }else{
              stBig.setFromX(1.0);
              stBig.setFromY(1.0);
           }

           stBig.setToX(1.5);
           stBig.setToY(1.5);
           stBig.playFromStart();
           animation.play();
          
        });

		setOnMouseExited(e ->{
			
          if (stBig.getStatus() == Animation.Status.RUNNING){
             stBig.stop();
             stSmall.setFromX(stBig.getNode().getScaleX());
             stSmall.setFromY(stBig.getNode().getScaleY());
          }else{
             stSmall.setFromX(1.5);
             stSmall.setFromY(1.5);
          }

          stSmall.setToX(1.0);
          stSmall.setToY(1.0);

          stSmall.playFromStart();
          animation.stop();
       });
		
	}
	protected void updateScore() {
		DNA.score += 1;
		score.setText(""+DNA.score);
		
	}

}
