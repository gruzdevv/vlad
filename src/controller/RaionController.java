package controller;

import java.io.IOException;
import library.Raion;
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
 * @author 
 */
public class RaionController implements Initializable {
    
    @FXML
    private TextField raion_idField;
    
    @FXML
    private TextField raion_nameField;
    
    @FXML
    private Button insertRaionButton;

    @FXML
    private Button updateRaionButton;

    @FXML
    private Button deleteRaionButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Raion> TableViewRaion;
    
    @FXML
    private TableColumn<Raion, Integer> raion_idColumn;
    
    @FXML
    private TableColumn<Raion, String> raion_nameColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            
            Scene sceneMenu = new Scene(fxmlLoader.load());
            Stage stageMenu = new Stage();
            stageMenu.initModality(Modality.NONE);
            stageMenu.setTitle("Raion table");
            stageMenu.setScene(sceneMenu);
            stageMenu.show();
            System.out.println(" *Кнопка 'btnOK' нажата");
            System.out.println("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(NedvizhController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertRaionButton() {
    	String query = "insert into raion values("+raion_idField.getText()+",'"+raion_nameField.getText()+"')";
    	executeQuery(query);
    	showRaion();
    }
    
    @FXML 
    private void updateRaionButton() {
    String query = "UPDATE raion SET raion_name='"+raion_nameField.getText()+"' WHERE raion_id="+raion_idField.getText()+"";
    executeQuery(query);
	showRaion();
    }
    
    @FXML
    private void deleteRaionButton() {
    	String query = "DELETE FROM raion WHERE raion_id="+raion_idField.getText()+"";
    	executeQuery(query);
    	showRaion();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionRaion();
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
    	showRaion();
    }
    
    public Connection getConnectionRaion() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://mysql-162920.srv.hoster.ru:3306/srv162920_vladdb", "srv162920_vlad", "vlad2000");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
      
    public ObservableList<Raion> getRaionList(){
    	ObservableList<Raion> raionList = FXCollections.observableArrayList();
    	Connection connection = getConnectionRaion();
    	String query = "SELECT * FROM raion ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Raion raion;
			while(rs.next()) {
				raion = new Raion(rs.getInt("raion_id"),rs.getString("raion_name"));
				raionList.add(raion);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return raionList;
    }
    
    public void showRaion() {
    	ObservableList<Raion> list_raion = getRaionList();
    	
    	raion_idColumn.setCellValueFactory(new PropertyValueFactory<Raion,Integer>("raion_id"));
    	raion_nameColumn.setCellValueFactory(new PropertyValueFactory<Raion,String>("raion_name"));
        
    	TableViewRaion.setItems(list_raion);
        
        
    }
}
