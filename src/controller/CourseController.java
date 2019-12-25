package controller;

import java.io.IOException;
import library.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author khadiullin
 */

public class CourseController implements Initializable {
    
    @FXML
    private TextField course_idField;
    
    @FXML
    private TextField course_nameField;

    @FXML
    private TextField department_idField;
    
    @FXML
    private Button insertCourseButton;

    @FXML
    private Button updateCourseButton;

    @FXML
    private Button deleteCourseButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Course> TableViewCourse;
    
    @FXML
    private TableColumn<Course, Integer> course_idColumn;
    
    @FXML
    private TableColumn<Course, String> course_nameColumn;

    @FXML
    private TableColumn<Course, Integer> department_idColumn;
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        if (event.getSource()== BackButton){
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        }  
    }
    
    @FXML
    private void insertCourseButton() {
    	String query = "insert into course values("+course_idField.getText()+",'"+course_nameField.getText()+"',"+department_idField.getText()+")";
    	executeQuery(query);
    	showCourse();
    }
    
    @FXML 
    private void updateCourseButton() {
    String query = "UPDATE course SET course_name='"+course_nameField.getText()+"',department_id="+department_idField.getText()+" WHERE course_id="+course_idField.getText()+"";
    executeQuery(query);
	showCourse();
    }
    
    @FXML
    private void deleteCourseButton() {
    	String query = "DELETE FROM course WHERE course_id="+course_idField.getText()+"";
    	executeQuery(query);
    	showCourse();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionCourse();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	showCourse();
    }
    
    public Connection getConnectionCourse() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru:3306/srv162551_db","srv162551_root","2010dima");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
      
    public ObservableList<Course> getCourseList(){
    	ObservableList<Course> courseList = FXCollections.observableArrayList();
    	Connection connection = getConnectionCourse();
    	String query = "SELECT * FROM course ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Course course;
			while(rs.next()) {
				course = new Course(rs.getInt("course_id"),rs.getString("course_name"),rs.getInt("department_id"));
				courseList.add(course);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return courseList;
    }
    
    public void showCourse() {
    	ObservableList<Course> list_course = getCourseList();
    	
    	course_idColumn.setCellValueFactory(new PropertyValueFactory<Course,Integer>("course_id"));
    	course_nameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("course_name"));
    	department_idColumn.setCellValueFactory(new PropertyValueFactory<Course,Integer>("department_id"));

    	TableViewCourse.setItems(list_course);
        
        
    }
}
