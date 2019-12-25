package controller;

import java.io.IOException;
import library.Notad;
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
public class NotadController implements Initializable {
    
    @FXML
    private TextField notad_idField;
    
    @FXML
    private TextField dateField;

    @FXML
    private TextField student_idField;
    
    @FXML
    private TextField countField;
    
    @FXML
    private Button insertNotadButton;

    @FXML
    private Button updateNotadButton;

    @FXML
    private Button deleteNotadButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Notad> TableViewNotad;
    
    @FXML
    private TableColumn<Notad, Integer> notad_idColumn;
    
    @FXML
    private TableColumn<Notad, String> dateColumn;

    @FXML
    private TableColumn<Notad, Integer> student_idColumn;
    
    @FXML
    private TableColumn<Notad, Integer> countColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            
            Scene sceneMenu = new Scene(fxmlLoader.load());
            Stage stageMenu = new Stage();
            stageMenu.initModality(Modality.NONE);
            stageMenu.setTitle("Menu table");
            stageMenu.setScene(sceneMenu);
            stageMenu.show();
            System.out.println(" *Кнопка 'btnOK' нажата");
            System.out.println("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        if (event.getSource()== BackButton){
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        }  
    }
    
    @FXML
    private void insertNotadButton() {
    	String query = "insert into notattendance values("+notad_idField.getText()+",'"+dateField.getText()+"',"+student_idField.getText()+","+countField.getText()+")";
    	executeQuery(query);
    	showNotad();
    }
    
    @FXML 
    private void updateNotadButton() {
    String query = "UPDATE notattendance SET date='"+dateField.getText()+"',student_id="+student_idField.getText()+",count="+countField.getText()+" WHERE notad_id="+notad_idField.getText()+"";
    executeQuery(query);
	showNotad();
    }
    
    @FXML
    private void deleteNotadButton() {
    	String query = "DELETE FROM notattendance WHERE notad_id="+notad_idField.getText()+"";
    	executeQuery(query);
    	showNotad();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionNotad();
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
    	showNotad();
    }
    
    public Connection getConnectionNotad() {
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
      
    public ObservableList<Notad> getNotadList(){
    	ObservableList<Notad> notadList = FXCollections.observableArrayList();
    	Connection connection = getConnectionNotad();
    	String query = "SELECT * FROM notattendance ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Notad notad;
			while(rs.next()) {
				notad = new Notad(rs.getInt("notad_id"),rs.getString("date"),rs.getInt("student_id"),rs.getInt("count"));
				notadList.add(notad);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return notadList;
    }
    
    public void showNotad() {
    	ObservableList<Notad> list_notad = getNotadList();
    	
    	notad_idColumn.setCellValueFactory(new PropertyValueFactory<Notad,Integer>("notad_id"));
    	dateColumn.setCellValueFactory(new PropertyValueFactory<Notad,String>("date"));
    	student_idColumn.setCellValueFactory(new PropertyValueFactory<Notad,Integer>("student_id"));
        countColumn.setCellValueFactory(new PropertyValueFactory<Notad,Integer>("count"));
        
    	TableViewNotad.setItems(list_notad);
        
        
    }
}
