package controller;

import java.io.IOException;
import library.Department;
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
public class DepartmentController implements Initializable {
    
    @FXML
    private TextField department_idField;
    
    @FXML
    private TextField department_nameField;

    @FXML
    private TextField department_zam_idField;
    
    @FXML
    private Button insertDepartmentButton;

    @FXML
    private Button updateDepartmentButton;

    @FXML
    private Button deleteDepartmentButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Department> TableViewDepartment;
    
    @FXML
    private TableColumn<Department, Integer> department_idColumn;
    
    @FXML
    private TableColumn<Department, String> department_nameColumn;

    @FXML
    private TableColumn<Department, Integer> department_zam_idColumn;
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        if (event.getSource()== BackButton){
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        }  
    }
    
    @FXML
    private void insertDepartmentButton() {
    	String query = "insert into department values("+department_idField.getText()+",'"+department_nameField.getText()+"',"+department_zam_idField.getText()+")";
    	executeQuery(query);
    	showDepartment();
    }
    
    @FXML 
    private void updateDepartmentButton() {
    String query = "UPDATE department SET department_name='"+department_nameField.getText()+"',department_zam_id="+department_zam_idField.getText()+" WHERE department_id="+department_idField.getText()+"";
    executeQuery(query);
	showDepartment();
    }
    
    @FXML
    private void deleteDepartmentButton() {
    	String query = "DELETE FROM department WHERE department_id="+department_idField.getText()+"";
    	executeQuery(query);
    	showDepartment();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionDepartment();
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
    	showDepartment();
    }
    
    public Connection getConnectionDepartment() {
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
      
    public ObservableList<Department> getDepartmentList(){
    	ObservableList<Department> departmentList = FXCollections.observableArrayList();
    	Connection connection = getConnectionDepartment();
    	String query = "SELECT * FROM department ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Department department;
			while(rs.next()) {
				department = new Department(rs.getInt("department_id"),rs.getString("department_name"),rs.getInt("department_zam_id"));
				departmentList.add(department);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return departmentList;
    }
    
    public void showDepartment() {
    	ObservableList<Department> list_department = getDepartmentList();
    	
    	department_idColumn.setCellValueFactory(new PropertyValueFactory<Department,Integer>("department_id"));
    	department_nameColumn.setCellValueFactory(new PropertyValueFactory<Department,String>("department_name"));
    	department_zam_idColumn.setCellValueFactory(new PropertyValueFactory<Department,Integer>("department_zam_id"));

    	TableViewDepartment.setItems(list_department);
        
        
    }
}
