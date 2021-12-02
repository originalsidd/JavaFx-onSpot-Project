import javafx.application.Application;
import javafx.animation.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import java.io.*;
import java.lang.Math;
import javafx.util.Duration;
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

		String path = "D:\\JavaFx Project\\JavaFx-Project\\helicopter.mp3";
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer heliSound = new MediaPlayer(media);
		heliSound.play();

		String path2 = "D:\\JavaFx Project\\JavaFx-Project\\over.wav";
		Media media2 = new Media(new File(path2).toURI().toString());
		MediaPlayer overSound = new MediaPlayer(media2);

		String path3 = "D:\\JavaFx Project\\JavaFx-Project\\game.mp3";
		Media media3 = new Media(new File(path3).toURI().toString());
		MediaPlayer gameSound = new MediaPlayer(media3);
		gameSound.setVolume(3);
		gameSound.play();

		String path4 = "D:\\JavaFx Project\\JavaFx-Project\\bonus.wav";
		Media media4 = new Media(new File(path4).toURI().toString());
		MediaPlayer bonusSound = new MediaPlayer(media4);

		boolean f = true;
		// OUR CAT
		Circle cat = new Circle();
		cat.setCenterX(150);
		cat.setCenterY(200);
		cat.setRadius(30);

		// IMAGING & BACKGROUNDS-----

		FileInputStream input = new FileInputStream("D:\\JavaFx Project\\JavaFx-Project\\sky.jpg");
		Image image = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(image,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);

		Image image1 = new Image("D:\\JavaFx Project\\JavaFx-Project\\gameover1.jpg");
		BackgroundImage backgroundimage1 = new BackgroundImage(image1,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background background1 = new Background(backgroundimage1);

		Image pillarImage = new Image("D:\\JavaFx Project\\JavaFx-Project\\brick.jpg");
		ImagePattern pattern = new ImagePattern(pillarImage, 20, 20, 40, 40, false);

		Image catImage = new Image("D:\\JavaFx Project\\JavaFx-Project\\cat.jpg");
		ImagePattern pattern1 = new ImagePattern(catImage, 60, 60, 60, 60, false);

		// --------------------------
		Label l = new Label(Integer.toString(0));
		l.setScaleX(5);
		l.setScaleY(5);
		l.setLayoutX(20);
		l.setLayoutY(20);
		l.setTextAlignment(TextAlignment.RIGHT);
		l.setTextFill(Color.rgb(255, 255, 255, 1));

		Rectangle screen = new Rectangle(0, 0, 1200, 800);
		screen.setOpacity(0);

		Rectangle pillar1 = new Rectangle(pX, pY, height, breadth);
		Rectangle pillar2 = new Rectangle(pX, pY + 400, height, breadth);
		Rectangle pillar3 = new Rectangle(pX, pY + 200, height, breadth);

		pillar1.setFill(pattern);
		pillar2.setFill(pattern);
		pillar3.setFill(pattern);
		cat.setFill(pattern1);

		MotionBlur mb = new MotionBlur();
		mb.setRadius(2.0f);
		mb.setAngle(90.0f);		
		cat.setEffect(mb);
		pillar1.setEffect(mb);
		pillar2.setEffect(mb);
		pillar3.setEffect(mb);

		TranslateTransition TT = new TranslateTransition();
		TT.setDuration(Duration.millis(2500));
		TT.setNode(pillar1);
		TT.setFromX(1600);
		TT.setToX(-100);
		TT.setCycleCount(200);
		TT.play();


		TranslateTransition TT2 = new TranslateTransition();
		TT2.setDuration(Duration.millis(2100));
		TT2.setNode(pillar2);
		TT2.setFromX(2000);
		TT2.setToX(-100);
		TT2.setCycleCount(1000);
		TT2.play();


		TranslateTransition TT3 = new TranslateTransition();
		TT3.setDuration(Duration.millis(1700));
		TT3.setNode(pillar3);
		TT3.setFromX(1800);
		TT3.setToX(-100);
		TT3.setCycleCount(1000);
		TT3.play();


		EventHandler<MouseEvent> collisionEvent = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				cat.setCenterX(me.getX());
				cat.setCenterY(me.getY());
				if (Integer.parseInt(l.getText()) % 20 == 0 && Integer.parseInt(l.getText()) != 0) {
					bonusSound.play();
				}
				if (Math.abs(cat.getBoundsInParent().getCenterX() - pillar1.getBoundsInParent().getCenterX()) < 10) {
					l.setText(Integer.toString(Integer.parseInt(l.getText()) + 1));
				}
				if (Math.abs(cat.getBoundsInParent().getCenterX() - pillar2.getBoundsInParent().getCenterX()) < 10) {
					l.setText(Integer.toString(Integer.parseInt(l.getText()) + 1));
				}
				if (Math.abs(cat.getBoundsInParent().getCenterX() - pillar3.getBoundsInParent().getCenterX()) < 10) {
					l.setText(Integer.toString(Integer.parseInt(l.getText()) + 1));
				}
				if (cat.getBoundsInParent().intersects(pillar1.getBoundsInParent()) ||
						cat.getBoundsInParent().intersects(pillar2.getBoundsInParent()) ||
						cat.getBoundsInParent().intersects(pillar3.getBoundsInParent())) {
					// System.out.println("Collision");
					heliSound.stop();
					gameSound.stop();
					overSound.play();
					TT.stop();
					TT2.stop();
					TT3.stop();
					VBox child = new VBox(20);

					child.getChildren().addAll();

					Stage stage2 = new Stage();

					Scene scene2 = new Scene(child, 682, 384, Color.rgb(30, 30, 30, 1));
					child.setBackground(background1);
					stage2.initModality(Modality.WINDOW_MODAL);
					stage2.setX(stage.getX() + 250);
					stage2.setY(stage.getY() + 200);
					stage2.initOwner(stage);
					stage2.setTitle("Game Over");
					stage2.setScene(scene2);
					stage2.show();
					stage2.setOnCloseRequest(ev -> {
						l.setText(Integer.toString(0));
						heliSound.play();
						gameSound.play();
						overSound.stop();
						TT.play();
						TT2.play();
						TT3.play();
						cat.setCenterX(150);
						cat.setCenterY(200);
					});
				}
			}
		};

		cat.addEventHandler(MouseEvent.MOUSE_MOVED, collisionEvent);
		cat.addEventHandler(MouseEvent.MOUSE_PRESSED, collisionEvent);
		cat.addEventHandler(MouseEvent.MOUSE_DRAGGED, collisionEvent);
		cat.addEventHandler(MouseEvent.MOUSE_RELEASED, collisionEvent);
		screen.addEventHandler(MouseEvent.MOUSE_MOVED, collisionEvent);
		screen.addEventHandler(MouseEvent.MOUSE_PRESSED, collisionEvent);
		screen.addEventHandler(MouseEvent.MOUSE_DRAGGED, collisionEvent);
		screen.addEventHandler(MouseEvent.MOUSE_RELEASED, collisionEvent);
		// cat.setOnMouseMoved(e -> {
		// 	cat.setCenterX(e.getX());
		// 	cat.setCenterY(e.getY());
		// });
		// screen.setOnMouseMoved(new EventHandler<MouseEvent>() {
		// 	public void handle(MouseEvent me) {

		// 		cat.setCenterX(me.getX());
		// 		cat.setCenterY(me.getY());
		// 		if (Math.abs(cat.getBoundsInParent().getCenterX() - pillar1.getBoundsInParent().getCenterX()) < 50) {
		// 			l.setText(Integer.toString(Integer.parseInt(l.getText())+1));
		// 		}
		// 		if (Math.abs(cat.getBoundsInParent().getCenterX() - pillar2.getBoundsInParent().getCenterX()) < 50) {
		// 			l.setText(Integer.toString(Integer.parseInt(l.getText())+1));
		// 		}
		// 		if (Math.abs(cat.getBoundsInParent().getCenterX() - pillar3.getBoundsInParent().getCenterX()) < 50) {
		// 			l.setText(Integer.toString(Integer.parseInt(l.getText())+1));
		// 		}
		// 		if (cat.getBoundsInParent().intersects(pillar1.getBoundsInParent()) ||
		// 				cat.getBoundsInParent().intersects(pillar2.getBoundsInParent()) ||
		// 				cat.getBoundsInParent().intersects(pillar3.getBoundsInParent())
		// 			) {
		// 			System.out.println("Collision");
		// 			TT.stop();
		// 			TT2.stop();
		// 			TT3.stop();
		// 			VBox child = new VBox(20);
		// 			l.setText(Integer.toString(0));
		
		// 			child.getChildren().addAll();
		
		// 			Stage stage2 = new Stage();
		
		// 			Scene scene2 = new Scene(child, 682, 384, Color.rgb(30, 30, 30, 1));
		// 			child.setBackground(background1);
		// 			stage2.initModality(Modality.WINDOW_MODAL);
		// 			stage2.setX(stage.getX() + 100);
		// 			stage2.setY(stage.getY() + 100);
		// 			stage2.initOwner(stage);
		// 			stage2.setTitle("Game Over");
		// 			stage2.setScene(scene2);
		// 			stage2.show();
		// 			stage2.setOnCloseRequest(ev -> {
		
		// 				TT.play();
		// 				TT2.play();
		// 				TT3.play();
		// 				cat.setCenterX(150);
		// 				cat.setCenterY(200);
		// 			});
		// 		}
		// 	}
		// });

		root.getChildren().addAll(screen, l, cat, pillar1, pillar2, pillar3);

		Scene scene = new Scene(root, 1200, 800, Color.rgb(100, 100, 150, 0.5));

		root.setBackground(background);
		stage.setTitle("CatCopter");
		stage.setScene(scene);
		stage.show();
	}
}