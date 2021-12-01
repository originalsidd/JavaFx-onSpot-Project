import javafx.application.Application;
import javafx.application.Platform;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;
import java.io.*;
import java.lang.Math;
import javafx.util.Duration;
import javafx.beans.property.*;
import javafx.stage.Modality;

// import javafx.scene.PerspectiveCamera;

public class JavaFx extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		Pane root = new Pane();
		int height = 80;
		int breadth = 200;
		int pX = 0;
		int pY = height;

		// OUR CAT
		Circle cat = new Circle();
		cat.setCenterX(150);
		cat.setCenterY(200);
		cat.setRadius(30);

		// IMAGING & BACKGROUNDS-----

		FileInputStream input = new FileInputStream("C:\\Users\\User\\Desktop\\sky.png");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);

		Image image1 = new Image("C:\\Users\\User\\Desktop\\gameover1.jpg");
		BackgroundImage backgroundimage1 = new BackgroundImage(image1,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background1 = new Background(backgroundimage1);

		Image pillarImage = new Image("C:\\Users\\User\\Desktop\\brick.jpg");
		ImagePattern pattern = new ImagePattern(pillarImage, 20, 20, 40, 40, false);

		Image catImage = new Image("C:\\Users\\User\\Desktop\\cat.jpg");
		ImagePattern pattern1 = new ImagePattern(catImage, 50, 50, 50, 50, false);

		// --------------------------

		Rectangle pillar1 = new Rectangle(pX, pY, height, breadth);
		pillar1.setFill(pattern);
		cat.setFill(pattern1);

		TranslateTransition TT = new TranslateTransition();
		TT.setDuration(Duration.millis(5000));
		TT.setNode(pillar1);
		TT.setFromX(1000);
		TT.setToX(-100);
		TT.setCycleCount(200);
		TT.play();

		Rectangle pillar2 = new Rectangle(pX, pY + 400, height, breadth);

		TranslateTransition TT2 = new TranslateTransition();
		TT2.setDuration(Duration.millis(4000));
		TT2.setNode(pillar2);
		TT2.setFromX(900);
		TT2.setToX(-100);
		TT2.setCycleCount(1000);
		TT2.play();

		Rectangle pillar3 = new Rectangle(pX, pY + 200, height, breadth);

		TranslateTransition TT3 = new TranslateTransition();
		TT3.setDuration(Duration.millis(3500));
		TT3.setNode(pillar3);
		TT3.setFromX(900);
		TT3.setToX(-100);
		TT3.setCycleCount(1000);
		TT3.play();

		pillar2.setFill(pattern);
		pillar3.setFill(pattern);
		EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				cat.setCenterX(me.getX());
				cat.setCenterY(me.getY());
				if (cat.getBoundsInParent().intersects(pillar1.getBoundsInParent()) ||
						cat.getBoundsInParent().intersects(pillar2.getBoundsInParent())) {
					System.out.println("Collision");
					TT.stop();
					TT2.stop();
					TT3.stop();
					VBox child = new VBox(20);

					Label gg = new Label("GameOver");

					child.getChildren().addAll();

					Stage stage2 = new Stage();

					Scene scene2 = new Scene(child, 682, 384, Color.rgb(30, 30, 30, 1));
					child.setBackground(background1);
					stage2.initModality(Modality.WINDOW_MODAL);
					stage2.setX(stage.getX() + 100);
					stage2.setY(stage.getY() + 100);
					stage2.initOwner(stage);
					stage2.setTitle("Game Over");
					stage2.setScene(scene2);
					stage2.show();
					stage2.setOnCloseRequest(ev -> {

						TT.play();
						TT2.play();
						TT3.play();
						cat.setCenterX(150);
						cat.setCenterY(200);
					});
				}
			}
		};

		cat.addEventHandler(MouseEvent.MOUSE_MOVED, event);
		cat.addEventHandler(MouseEvent.MOUSE_ENTERED, event);
		cat.addEventHandler(MouseEvent.MOUSE_EXITED, event);

		root.getChildren().addAll(cat, pillar1, pillar2, pillar3);

		Scene scene = new Scene(root, 800, 800, Color.rgb(100, 100, 150, 0.5));
		root.setBackground(background);
		stage.setTitle("CatCopter");
		stage.setScene(scene);
		stage.show();

	}
}