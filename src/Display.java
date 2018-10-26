import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Display extends Application {
	PumpkinPop pop;
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		double mutation = 0.30;
		int maxPop = 6;
		
		pop = new PumpkinPop(mutation, maxPop);
		
		PumpkinDNA[] pumpkins = pop.getPop();
		PumpkinPicture[] pPics = new PumpkinPicture[pumpkins.length];
		
		HBox pumpkinsHBox = new HBox();
		
		for(int i = 0; i < pumpkins.length; i++) {
			
			pPics[i]= new PumpkinPicture(pumpkins[i]);
			pumpkinsHBox.getChildren().add(pPics[i]);
		}

		Button rep = new Button("Reproduce");
		HBox bot = new HBox();
		VBox bp = new VBox();;
		bot.setAlignment(Pos.BOTTOM_CENTER);
		
		rep.setOnAction(e -> {
			try {
				reproduce(pumpkinsHBox );
				System.out.println("Stuff");
			}catch(FileNotFoundException e1) {
				System.out.print(e1);
			}
		});
		
		bot.getChildren().add(rep);
		bp.getChildren().addAll(pumpkinsHBox, bot);
		
		
		
		Scene scene = new Scene(bp,200*maxPop,250);
		
		primaryStage.setTitle("Genetic Pumpkin");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

	public void reproduce(HBox pumpkinsHBox) throws FileNotFoundException{
		pop.fitness();
		pop.reproduction();
		
		PumpkinDNA[] pumpkins = pop.getPop();
		PumpkinPicture[] pPics = new PumpkinPicture[pumpkins.length];
		
		pumpkinsHBox.getChildren().clear();
		
		for(int i = 0; i < pumpkins.length; i++) {
			
			pPics[i]= new PumpkinPicture(pumpkins[i]);
			pumpkinsHBox.getChildren().add(pPics[i]);
		}
		
	}

}
