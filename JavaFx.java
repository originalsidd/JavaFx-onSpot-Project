import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
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
// import javafx.scene.PerspectiveCamera;

public class JavaFx extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {

		Line l1 = new Line(240, 100, 300, 160);
		Group root = new Group();
		root.getChildren().addAll(l1);

		Scene scene = new Scene(root, 600, 400, Color.rgb(100, 100, 150, 0.5));

		stage.setTitle("Application");
		stage.setScene(scene);
		stage.show();
	}
}