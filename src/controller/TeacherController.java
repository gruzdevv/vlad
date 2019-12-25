package controller;

import java.io.IOException;
import library.Teacher;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherController implements Initializable {

    @FXML
    private TextField teacher_idField;
    
    @FXML
    private TextField teacher_nameField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField teacher_phoneField;

    @FXML
    private TextField teacher_emailField;

    @FXML
    private TextField department_idField;
    
    @FXML
    private Button MenuCityButton;
    
    @FXML
    private Button insertTeacherButton;

    @FXML
    private Button updateTeacherButton;

    @FXML
    private Button deleteTeacherButton;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private TableView<Teacher> TableViewTeacher;
    
    @FXML
    private TableColumn<Teacher, Integer> teacher_idColumn;
    
    @FXML
    private TableColumn<Teacher, String> teacher_nameColumn;

    @FXML
    private TableColumn<Teacher, String> subjectColumn;

    @FXML
    private TableColumn<Teacher, String> teacher_phoneColumn;
    
    @FXML
    private TableColumn<Teacher, String> teacher_emailColumn;
     
    @FXML
    private TableColumn<Teacher, Integer> department_idColumn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource()== MenuCityButton){
             try {
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
                 
                 Scene scene1 = new Scene(fxmlLoader.load());
                 Stage stage1 = new Stage();
                 stage1.initModality(Modality.NONE);
                 //убрать панельку
                 //stage1.initStyle(StageStyle.UNDECORATED); 
                 stage1.setTitle("ABC");
                 stage1.setScene(scene1);
                 stage1.show();
             } catch (IOException ex) {
                 Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
             }

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
    private void insertTeacherButton() {
    	String query = "insert into teacher values("+teacher_idField.getText()+",'"+teacher_nameField.getText()+"','"+subjectField.getText()+"','"+teacher_phoneField.getText()+"','"+teacher_emailField.getText()+"',"+department_idField.getText()+")";
    	executeQuery(query);
    	showTeacher();
    }
    

    @FXML 
    private void updateTeacherButton() {
    String query = "UPDATE teacher SET teacher_name='"+teacher_nameField.getText()+"',subject='"+subjectField.getText()+"',teacher_phone='"+teacher_phoneField.getText()+"',teacher_email='"+teacher_emailField.getText()+"',department_id='"+department_idField.getText()+"' WHERE teacher_id="+teacher_idField.getText()+"";
    executeQuery(query);
	showTeacher();
    }
    
    @FXML
    private void deleteTeacherButton() {
    	String query = "DELETE FROM teacher WHERE teacher_id="+teacher_idField.getText()+"";
    	executeQuery(query);
    	showTeacher();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnection();
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
    	showTeacher();
    }
    
    public Connection getConnection() {
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
    
    public ObservableList<Teacher> getTeacherList(){
    	ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM teacher ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Teacher teacher;
			while(rs.next()) {
				teacher = new Teacher(rs.getInt("teacher_id"),rs.getString("teacher_name"),rs.getString("subject"),rs.getString("teacher_phone"),rs.getString("teacher_email"),rs.getInt("department_id"));
				teacherList.add(teacher);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return teacherList;
    }
    
    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showTeacher() {
    	ObservableList<Teacher> list_teacher = getTeacherList();
    	
    	teacher_idColumn.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("teacher_id"));
    	teacher_nameColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("teacher_name"));
    	subjectColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("subject"));
    	teacher_phoneColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("teacher_phone"));
        teacher_emailColumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("teacher_email"));
    	department_idColumn.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("department_id"));
        
    	TableViewTeacher.setItems(list_teacher);
        
        
    }

}
