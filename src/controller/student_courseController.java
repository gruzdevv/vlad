package controller;

import java.io.IOException;
import library.Stcourse;
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
public class student_courseController implements Initializable {
    
    @FXML
    private TextField student_course_idField;
    
    @FXML
    private TextField student_idField;

    @FXML
    private TextField course_idField;
    
    @FXML
    private TextField gr_idField;
    
    @FXML
    private Button insertStcourseButton;

    @FXML
    private Button updateStcourseButton;

    @FXML
    private Button deleteStcourseButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Stcourse> TableViewStcourse;
    
    @FXML
    private TableColumn<Stcourse, Integer> student_course_idColumn;
    
    @FXML
    private TableColumn<Stcourse, Integer> student_idColumn;

    @FXML
    private TableColumn<Stcourse, Integer> course_idColumn;
    
    @FXML
    private TableColumn<Stcourse, Integer> gr_idColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            stageTeacher.setTitle("Teacher table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            //System.out.println(" *Кнопка 'btnOK' нажата");
            //System.out.println("");
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        if (event.getSource()== closeButton){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }  
    }
    
    @FXML
    private void insertStcourseButton() {
    	String query = "insert into student_course values("+student_course_idField.getText()+","+student_idField.getText()+","+course_idField.getText()+","+gr_idField.getText()+")";
    	executeQuery(query);
    	showStcourse();
    }
    
    @FXML 
    private void updateStcourseButton() {
    String query = "UPDATE student_course SET student_id="+student_idField.getText()+",course_id="+course_idField.getText()+",gr_id="+gr_idField.getText()+" WHERE student_course_id="+student_course_idField.getText()+"";
    executeQuery(query);
	showStcourse();
    }
    
    @FXML
    private void deleteStcourseButton() {
    	String query = "DELETE FROM student_course WHERE student_course_id="+student_course_idField.getText()+"";
    	executeQuery(query);
    	showStcourse();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionStcourse();
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
    	showStcourse();
    }
    
    public Connection getConnectionStcourse() {
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
      
    public ObservableList<Stcourse> getStcourseList(){
    	ObservableList<Stcourse> stcourseList = FXCollections.observableArrayList();
    	Connection connection = getConnectionStcourse();
    	String query = "SELECT * FROM student_course ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Stcourse stcourse;
			while(rs.next()) {
				stcourse = new Stcourse(rs.getInt("student_course_id"),rs.getInt("student_id"),rs.getInt("course_id"),rs.getInt("gr_id"));
				stcourseList.add(stcourse);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return stcourseList;
    }
    
    public void showStcourse() {
    	ObservableList<Stcourse> list_stcourse = getStcourseList();
    	
    	student_course_idColumn.setCellValueFactory(new PropertyValueFactory<Stcourse,Integer>("student_course_id"));
    	student_idColumn.setCellValueFactory(new PropertyValueFactory<Stcourse,Integer>("student_id"));
    	course_idColumn.setCellValueFactory(new PropertyValueFactory<Stcourse,Integer>("course_id"));
        gr_idColumn.setCellValueFactory(new PropertyValueFactory<Stcourse,Integer>("gr_id"));
        
    	TableViewStcourse.setItems(list_stcourse);
        
        
    }
}
