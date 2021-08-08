package grades;

/**
 * 
 * @author Meraj Khan
 * ITC313 - Programming in Java 2
 * Task 1
 */

import java.sql.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

	// Button lists and titles
	private Button btSearch = new Button("Search");
	private Button btAdd = new Button("Add");
	private Button btUpdate = new Button("Update");
	private Button btClear = new Button("Clear");
	private Button btTable = new Button("TableView");

	// Textfields and titles
	private TextField tfStudentId = new TextField();
	private TextField tfStudentName = new TextField();
	private TextField tfQuizMark = new TextField();
	private TextField tfAsg1Mark = new TextField();
	private TextField tfAsg2Mark = new TextField();
	private TextField tfAsg3Mark = new TextField();
	private TextField tfExamMark = new TextField();

	// Variable to set Label at the Stage window
	private Label lblStatus = new Label();

	// TABLE VIEW AND DATA
	private ObservableList<ObservableList> data;
	private TableView tableview;

	// The Statement for processing queries
	private Statement stmt;
	
	
	 /* The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line
	 */	
	public static void main(String[] args) {
		launch(args);
	}

	// Override the start method in the Application class
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Tableview to show data from database table
		tableview = new TableView();

		// Text fields are placed on the grid pane
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5);
		pane.setVgap(5);

		pane.add(new Label("Student Id"), 0, 0);
		pane.add(tfStudentId, 1, 0);
		pane.add(new Label("Student Name"), 0, 1);
		pane.add(tfStudentName, 1, 1);
		pane.add(new Label("Quiz"), 0, 2);
		pane.add(tfQuizMark, 1, 2);
		pane.add(new Label("Assignment 1"), 0, 3);
		pane.add(tfAsg1Mark, 1, 3);
		pane.add(new Label("Assignment 2"), 0, 4);
		pane.add(tfAsg2Mark, 1, 4);
		pane.add(new Label("Assignment 3"), 0, 5);
		pane.add(tfAsg3Mark, 1, 5);
		pane.add(new Label("Exam"), 0, 6);
		pane.add(tfExamMark, 1, 6);

		// Hbox holds buttons
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(btSearch, btAdd, btUpdate, btClear, btTable);

		pane.add(hBox, 1, 7);
		GridPane.setHalignment(hBox, HPos.RIGHT);

		// Border pane holds table
		BorderPane layout2 = new BorderPane();
		layout2.setCenter(tableview);
		layout2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

		// Buttons, textfeilds, Label and tableview are places on border pane
		BorderPane pane1 = new BorderPane();
		pane1.setCenter(pane);
		pane1.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane1.setTop(lblStatus);
		pane1.setBottom(layout2);

		Scene scene = new Scene(pane1, 525, 750); // Defines the scene size
		primaryStage.setTitle("Grade Processing"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		initializeDB(); // initializing the database

		btSearch.setOnAction(e -> search()); // button to activate search method.
		btAdd.setOnAction(e -> add()); // button to activate add method.
		btUpdate.setOnAction(e -> update()); // button to activate update method.
		btClear.setOnAction(e -> clear()); // button to activate clear method.
		btTable.setOnAction(e -> buildData()); // button to activate buildData method.

	}

	// Method to initialize connection to the Database
	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // loading the driver
			System.out.println("Driver loaded");

			// Connect to the local InterBase database
			Connection conn = DriverManager.getConnection

			("jdbc:mysql://localhost/gradeprocessing", "root", "");
			/*Passing the values to establish database connection. NOTE: database name is "gradeprocessing"*/

			lblStatus.setText("Database connected"); //

			// Create a statement
			stmt = conn.createStatement();

		} catch (Exception ex) {
			lblStatus.setText("Connection failed: " + ex);
		}
	}

	// Insert a new record
	private void add() {
		// if statement to apply data validation
		if ((tfStudentId.getText().length() == 8) && (checkifInt(tfStudentId.getText()) == true)) {
			// Nested if statement to validate student id
			if ((tfStudentId.getText() != null && !tfStudentId.getText().isEmpty()) &&
					(tfStudentName.getText() != null && !tfStudentName.getText().isEmpty()
							&& (checkString(tfStudentName.getText()) == true))
					&& (tfQuizMark.getText() != null && !tfQuizMark.getText().isEmpty()
							&& (checkDouble(tfQuizMark.getText())))
					&& (tfAsg1Mark.getText() != null && !tfAsg1Mark.getText().isEmpty()
							&& (checkDouble(tfAsg1Mark.getText())))
					&& (tfAsg2Mark.getText() != null && !tfAsg2Mark.getText().isEmpty()
							&& (checkDouble(tfAsg2Mark.getText())))
					&& (tfAsg3Mark.getText() != null && !tfAsg3Mark.getText().isEmpty()
							&& (checkDouble(tfAsg3Mark.getText())))
					&& (tfExamMark.getText() != null && !tfExamMark.getText().isEmpty()
							&& (checkDouble(tfExamMark.getText())))) {
				// variables to covert text fields values to Integer and Double
				int id = Integer.parseInt(tfStudentId.getText());
				String name = tfStudentName.getText();
				double quiz = Double.parseDouble(tfQuizMark.getText());
				double a1 = Double.parseDouble(tfAsg1Mark.getText());
				double a2 = Double.parseDouble(tfAsg2Mark.getText());
				double a3 = Double.parseDouble(tfAsg3Mark.getText());
				double exam = Double.parseDouble(tfExamMark.getText());
				// Nested if statement to apply more data validation
				if ((quiz >= 0) && (quiz <= 100) && (a1 >= 0) && (a1 <= 100) && (a2 >= 0) && (a2 <= 100) && (a3 >= 0)
						&& (a3 <= 100) && (exam >= 0) && (exam <= 100)) {
					// methods are called to from CalculateGrades class to calculate Result and Grade
					CalculateGrades calc = new CalculateGrades(quiz, a1, a2, a3, exam);
					double result = calc.getResult(quiz, a1, a2, a3, exam);
					String grade = calc.getGrade(result);
					
					// Build a SQL createTable statement
					String createTable = "CREATE TABLE IF NOT EXISTS java2 (StudentId INT(8) ZEROFILL NOT NULL,"
							+ "StudentName VARCHAR(255) NOT NULL, Quiz DECIMAL(5,2) NULL, "
							+ "A1 DECIMAL(5,2) NULL, A2 DECIMAL(5,2) NULL, A3 DECIMAL(5,2) NULL, "
							+ "Exam DECIMAL(5,2) NULL, Result DECIMAL(5,2) NULL, Grade VARCHAR(2) NULL, PRIMARY KEY (StudentId))";

					InsertApp app = new InsertApp();

					try {
						// Execute query by calling method from class
						stmt.executeUpdate(createTable);// creating table if does not exists
						app.insert(id, name, quiz, a1, a2, a3, exam, result, grade); // passing values to the method from InsertApp Class

						System.out.println(result); // printing to console for User convenience
						System.out.println(grade); // printing to console for User convenience

					} catch (SQLException ex) {
						lblStatus.setText("Insertion failed: " + ex); //Exception handles insertions failures
					}

					lblStatus.setText("record inserted"); //confirmation label for successful record insertion.

				} else {
					// Alert stops user from entering invalid marks.
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Invalid Entry ");
					alert.setContentText("Individual Markings should limit from 0 to 100");

					alert.showAndWait();
				}

			} else {
				// Alert stops user from entering invalid student name or leaving text field blank.
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input fields empty/Invaid Student Name");
				alert.setContentText("Please fill all input fields" + "/Student Name Must Not Contain Numbers"
						+ "/Marks should contain double value only");

				alert.showAndWait();

			}
		} else {
			// Alert stops user from entering invalid student Id.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Student ID ");
			alert.setContentText("Student Id should be of 8 digit and numbers only ");
			alert.showAndWait();
		}

	}

	// View record by ID
	private void search() {
		// Build a SQL SELECT statement
		String query = "SELECT * FROM java2 WHERE StudentId = " + "'" + tfStudentId.getText().trim() + "'";

		try {
			// Execute query
			ResultSet rs = stmt.executeQuery(query);
			loadToTextField(rs);

		} catch (SQLException ex) {
			lblStatus.setText("Select failed: " + ex);
		}
	}

	// Load the record into text fields
	private void loadToTextField(ResultSet rs) throws SQLException {

		// If loop to read data from mySql database table and passes to Search method
		if (rs.next()) {
			tfStudentName.setText(rs.getString(2));
			tfQuizMark.setText(rs.getString(3));
			tfAsg1Mark.setText(rs.getString(4));
			tfAsg2Mark.setText(rs.getString(5));
			tfAsg3Mark.setText(rs.getString(6));
			tfExamMark.setText(rs.getString(7));

			lblStatus.setText("Record found");
		} else
			lblStatus.setText("Record not found");
	}

	// Update a record
	private void update() {
		// if statement to apply data validation
		if ((tfStudentId.getText().length() == 8) && (checkifInt(tfStudentId.getText()) == true)) {
			// Nested if statement to apply more data validation
			if ((tfStudentId.getText() != null && !tfStudentId.getText().isEmpty())
					&& (tfStudentName.getText() != null && !tfStudentName.getText().isEmpty()
							&& (checkString(tfStudentName.getText()) == true))
					&& (tfQuizMark.getText() != null && !tfQuizMark.getText().isEmpty()
							&& (checkDouble(tfQuizMark.getText())))
					&& (tfAsg1Mark.getText() != null && !tfAsg1Mark.getText().isEmpty()
							&& (checkDouble(tfAsg1Mark.getText())))
					&& (tfAsg2Mark.getText() != null && !tfAsg2Mark.getText().isEmpty()
							&& (checkDouble(tfAsg2Mark.getText())))
					&& (tfAsg3Mark.getText() != null && !tfAsg3Mark.getText().isEmpty()
							&& (checkDouble(tfAsg3Mark.getText())))
					&& (tfExamMark.getText() != null && !tfExamMark.getText().isEmpty()
							&& (checkDouble(tfExamMark.getText())))) {
				// variables to covert text fields values to Integer and Double
				int id = Integer.parseInt(tfStudentId.getText());
				String name = tfStudentName.getText();
				double quiz = Double.parseDouble(tfQuizMark.getText());
				double a1 = Double.parseDouble(tfAsg1Mark.getText());
				double a2 = Double.parseDouble(tfAsg2Mark.getText());
				double a3 = Double.parseDouble(tfAsg3Mark.getText());
				double exam = Double.parseDouble(tfExamMark.getText());
				
				// Nested if statement to apply more data validation
				if ((quiz >= 0) && (quiz <= 100) && (a1 >= 0) && (a1 <= 100) && (a2 >= 0) && (a2 <= 100) && (a3 >= 0)
						&& (a3 <= 100) && (exam >= 0) && (exam <= 100)) {
					// methods are called to from CalculateGrades class to calculate Result and Grade
					CalculateGrades calc = new CalculateGrades(quiz, a1, a2, a3, exam);
					double result = calc.getResult(quiz, a1, a2, a3, exam);
					String grade = calc.getGrade(result);
					try {
						// Execute query by calling method from class
						UpdateApp app1 = new UpdateApp();
						app1.update(name, quiz, a1, a2, a3, exam, result, grade, id);
						lblStatus.setText("Record successfully updated");

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					// Alert stops user from entering invalid marks.
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Invalid Entry ");
					alert.setContentText("Individual Markings should limit from 0 to 100");

					alert.showAndWait();
				}

			} else {
				// Alert stops user from leaving text field empty
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Input fields empty");
				alert.setContentText("Please fill all input fields, Name should not contain numeric values"
						+ "Marks should contain double values only");
				alert.showAndWait();

			}
		} else {
			// Alert stops user from entering invalid student Id.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Input fields empty/Invaid Student Name");
			alert.setContentText("Please fill all input fields" + "/Student Name Must Not Contain Numbers"
					+ "/Marks should contain double value only");
		}

	}

	//Clear text fields
	private void clear() {
		tfStudentId.setText(null);
		tfStudentName.setText(null);
		tfQuizMark.setText(null);
		tfAsg1Mark.setText(null);
		tfAsg2Mark.setText(null);
		tfAsg3Mark.setText(null);
		tfExamMark.setText(null);
		tableview.setItems(null);
	}

	//Builds tableview data
	public void buildData() {
		tableview.getColumns().clear(); //clears console memory before building new data from table
		
		Connection c;
		data = FXCollections.observableArrayList();
		try {
			c = DBConnect.connect();
			
			// SQL FOR SELECTING ALL OF Student
			String SQL = "SELECT * from java2";
			String createTable = "CREATE TABLE IF NOT EXISTS java2 (StudentId INT NOT NULL,"
					+ "StudentName VARCHAR(255) NOT NULL, Quiz DECIMAL(2) NULL, "
					+ "A1 DECIMAL(2) NULL, A2 DECIMAL(2) NULL, A3 DECIMAL(2) NULL, "
					+ "Exam DECIMAL(2) NULL, Result DECIMAL(2) NULL, Grade VARCHAR(2) NULL, PRIMARY KEY (StudentId))";

			// ResultSet
			c.createStatement().executeUpdate(createTable);
			ResultSet rs = c.createStatement().executeQuery(SQL);

			// TABLE COLUMN ADDED DYNAMICALLY
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			// Data added to ObservableList
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);
			}

			//ADDED TO TableView
			tableview.setItems(data);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
	}

	//method for data validation
	private boolean checkifInt(String text) {//this method checks if the user input value is a number
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	//method for data validation
	private boolean checkString(String text) {//this method checks if the user input value is a string
		try {
			Double.parseDouble(text);
			return false;
		} catch (NumberFormatException ex) {
			return true;
		}
	}
	
	//method for data validation
	private boolean checkDouble(String text) {//this method checks if the user input value is a double
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
