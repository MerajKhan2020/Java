import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.HPos;
import javafx.geometry.Insets;


/**
 * 
 * @author Meraj Khan
 * ITC313 - Programming in Java 2
 * Task 2
 */

public class TrafficLight extends Application implements Runnable {

	//Button lists and titles
	private Button btStart = new Button("Start");
	private Button btStop = new Button("Stop");
	
	// Textfields and titles
	private TextField tfGreen = new TextField("3"); //Text Field with default value 3
	private TextField tfYellow = new TextField("3"); //Text Field with default value 3
	private TextField tfRed = new TextField("3"); //Text Field with default value 3

	boolean runThread = true; //boolean value for looping traffic light indefinite times

	// Drwaing GridPane
	private double paneWidth = 300;
	private double paneHeight = 300;
	
	//main method
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		Pane pane2 = new StackPane();

		// Drawing a Rectangle
		Rectangle rectangle = new Rectangle();

		// Setting the properties of the rectangle
		rectangle.setX(150.0f);
		rectangle.setY(75.0f);
		rectangle.setWidth(300.0f);
		rectangle.setHeight(150.0f);

		// Setting the height and width of the arc
		rectangle.setArcWidth(30.0);
		rectangle.setArcHeight(20.0);

		// Drawing a Grid Pane for Traffic light circles
		GridPane pane1 = new GridPane();
		pane1.setAlignment(Pos.CENTER);
		pane1.setPadding(new Insets(5, 5, 5, 5));
		pane1.setHgap(5);
		pane1.setVgap(5);

		// Setting the properties of the Circle
		Circle circle = new Circle(paneWidth / 2, 60, 40);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.GREY);
		pane1.add(circle, 1, 1);

		// Setting the properties of the Circle1
		Circle circle1 = new Circle(paneWidth / 2, 60, 40);
		circle1.setStroke(Color.BLACK);
		circle1.setFill(Color.GREY);
		pane1.add(circle1, 2, 1);

		// Setting the properties of the Circle2
		Circle circle2 = new Circle(paneWidth / 2, 60, 40);
		circle2.setStroke(Color.BLACK);
		circle2.setFill(Color.GREY);
		pane1.add(circle2, 3, 1);

		//Traffic light with rectangle that holds 3 circles
		pane2.getChildren().add(rectangle);
		pane2.getChildren().add(pane1);

		//Hbox to hold buttons
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(btStart, btStop);

		//Grid Pane to set the text fields
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5);
		pane.setVgap(5);
		pane.add(new Label("Green"), 0, 0);
		pane.add(tfGreen, 1, 0);
		pane.add(new Label("Yellow"), 0, 1);
		pane.add(tfYellow, 1, 1);
		pane.add(new Label("Red"), 0, 2);
		pane.add(tfRed, 1, 2);
		pane.add(hBox, 2, 3);
		GridPane.setHalignment(hBox, HPos.RIGHT);
		
		// VBox holds both traffic light pane and text field pans
		VBox vBox = new VBox(10);
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(25.5, 12.5, 13.5, 14.5));
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(pane2, pane);

		//Set the parameter of the Scene box and places VBox on it.
		Scene scene = new Scene(vBox, 350, 300);
		primaryStage.setTitle("Traffic Light Simulation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		//creating start and stop button for the program
		btStart.setOnAction(e -> {
			runThread = true;
			Thread tfLight = new Thread() {//creating a new thread for timing traffic lights
				@Override
				public void run() {
					String tfGreenString = tfGreen.getText();
					int tfGreenInt = (Integer.parseInt(tfGreenString) * 1000 - 1000); //gets value to setup timer for green light
					String tfYellowString = tfYellow.getText();
					int tfYellowInt = (Integer.parseInt(tfYellowString) * 1000 - 1000); //gets value to setup timer for green light
					String tfRedString = tfRed.getText();
					int tfRedInt = (Integer.parseInt(tfRedString) * 1000 - 1000); //gets value to setup timer for green light

					while (runThread != false) {
						circle.setFill(Color.GREEN);
						System.out.print("Now Light Color Green. "); // prints basic console message about change light color
						for (int i = (tfGreenInt / 1000); i >= 0; i--) {
							try {
								Thread.sleep(1000); //pauses program for 1 sec 
								if (runThread == false){
	                                break;
	                            }
								
							}catch (InterruptedException e) {
							}
							System.out.println("Color Green Will Change in :" + i);//prints the remaining time after pausing for a sec
						}
						circle.setFill(Color.GRAY);
						if (runThread==false){
                            break;}
						circle1.setFill(Color.YELLOW);
						System.out.print("Now Light Color Yellow. "); // prints basic console message about change light color
						for (int i = (tfYellowInt / 1000); i >= 0; i--) {
							try {
								Thread.sleep(1000); //pauses program for 1 sec 
								if (runThread==false){
                                    break;
                            }
							} catch (InterruptedException e) {
							}
							System.out.println("Color Yellow Will Change in :" + i);//prints the remaining time after pausing for a sec
						}
						if (runThread==false){
                            break;
                    }
						circle1.setFill(Color.GRAY);
						circle2.setFill(Color.RED);
						System.out.print("Now Light Color Red. "); // prints basic console message about change light color
						for (int i = (tfRedInt / 1000); i >= 0; i--) {
							try {
								Thread.sleep(1000); //pauses program for 1 sec 
								if (runThread==false){
	                                break;
	                            }
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
							System.out.println("Color Red Will Change in :" + i);//prints the remaining time after pausing for a sec
						}
						circle2.setFill(Color.GRAY);
						if (runThread==false){
                            break;
                }
					}
				}
			};
			tfLight.start();
		});

		btStop.setOnAction(e -> {
			runThread = false;
			circle.setFill(Color.GRAY); //sets the color back to default
			circle1.setFill(Color.GRAY); //sets the color back to default
			circle2.setFill(Color.GRAY); //sets the color back to default
		});
	}

	@Override
	public void run() {
		while (true) {
		}
	}
	
}
