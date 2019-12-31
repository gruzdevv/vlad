package controller;

import java.io.IOException;
import library.Flower;
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
 * @author grafinina
 */
public class FlowerController implements Initializable {
    
    @FXML
    private TextField flower_idField;
    
    @FXML
    private TextField nameField;

    @FXML
    private TextField heightField;
    
    @FXML
    private TextField priceField;

    @FXML
    private TextField flower_countField;
    
    @FXML
    private Button insertFlowerButton;

    @FXML
    private Button updateFlowerButton;

    @FXML
    private Button deleteFlowerButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Flower> TableViewFlower;
    
    @FXML
    private TableColumn<Flower, Integer> flower_idColumn;
    
    @FXML
    private TableColumn<Flower, String> nameColumn;

    @FXML
    private TableColumn<Flower, String> heightColumn;
    
    @FXML
    private TableColumn<Flower, String> priceColumn;
    
    @FXML
    private TableColumn<Flower, String> flower_countColumn;
    
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
            Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertFlowerButton() {
    	String query = "insert into flower values("+flower_idField.getText()+",'"+nameField.getText()+"','"+heightField.getText()+"','"+priceField.getText()+"','"+flower_countField.getText()+"')";
    	executeQuery(query);
    	showFlower();
    }
    
    @FXML 
    private void updateFlowerButton() {
    String query = "UPDATE flower SET name='"+nameField.getText()+"',height='"+heightField.getText()+"',price='"+priceField.getText()+"',flower_count='"+flower_countField.getText()+"' WHERE flower_id="+flower_idField.getText()+"";
    executeQuery(query);
	showFlower();
    }
    
    @FXML
    private void deleteFlowerButton() {
    	String query = "DELETE FROM flower WHERE flower_id="+flower_idField.getText()+"";
    	executeQuery(query);
    	showFlower();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionFlower();
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
    	showFlower();
    }
    
    public Connection getConnectionFlower() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru:3306/srv162551_grafinina","srv162551_root","2010dima");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
      
    public ObservableList<Flower> getFlowerList(){
    	ObservableList<Flower> flowerList = FXCollections.observableArrayList();
    	Connection connection = getConnectionFlower();
    	String query = "SELECT * FROM flower ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Flower flower;
			while(rs.next()) {
				flower = new Flower(rs.getInt("flower_id"),rs.getString("name"),rs.getString("height"),rs.getString("price"),rs.getString("flower_count"));
				flowerList.add(flower);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return flowerList;
    }
    
    public void showFlower() {
    	ObservableList<Flower> list_flower = getFlowerList();
    	
    	flower_idColumn.setCellValueFactory(new PropertyValueFactory<Flower,Integer>("flower_id"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Flower,String>("name"));
    	heightColumn.setCellValueFactory(new PropertyValueFactory<Flower,String>("height"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flower,String>("price"));
        flower_countColumn.setCellValueFactory(new PropertyValueFactory<Flower,String>("flower_count"));
        
    	TableViewFlower.setItems(list_flower);
        
        
    }
}
