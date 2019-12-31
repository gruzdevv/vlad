package controller;

import java.io.IOException;
import library.Postavka;
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
public class PostavkaController implements Initializable {
    
    @FXML
    private TextField postavka_idField;
    
    @FXML
    private TextField postavshik_idField;

    @FXML
    private TextField flower_idField;
    
    @FXML
    private TextField data_postavkaField;
    
    @FXML
    private TextField postavka_countField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private Button insertPostavkaButton;

    @FXML
    private Button updatePostavkaButton;

    @FXML
    private Button deletePostavkaButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Postavka> TableViewPostavka;
    
    @FXML
    private TableColumn<Postavka, Integer> postavka_idColumn;
    
    @FXML
    private TableColumn<Postavka, Integer> postavshik_idColumn;

    @FXML
    private TableColumn<Postavka, Integer> flower_idColumn;
    
    @FXML
    private TableColumn<Postavka, String> data_postavkaColumn;
    
    @FXML
    private TableColumn<Postavka, Integer> postavka_countColumn;
    
    @FXML
    private TableColumn<Postavka, Integer> priceColumn;
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            stageTeacher.setTitle("Table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            //System.out.println(" *Кнопка 'btnOK' нажата");
            //System.out.println("");
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertPostavkaButton() {
    	String query = "insert into postavka values("+postavka_idField.getText()+","+postavshik_idField.getText()+","+flower_idField.getText()+",'"+data_postavkaField.getText()+"',"+postavka_countField.getText()+","+priceField.getText()+")";
    	executeQuery(query);
    	showPostavka();
    }
    
    @FXML 
    private void updatePostavkaButton() {
    String query = "UPDATE postavka SET postavshik_id="+postavshik_idField.getText()+",flower_id="+flower_idField.getText()+",data_postavka='"+data_postavkaField.getText()+"',postavka_count="+postavka_countField.getText()+",price="+priceField.getText()+" WHERE postavka_id="+postavka_idField.getText()+"";
    executeQuery(query);
	showPostavka();
    }
    
    @FXML
    private void deletePostavkaButton() {
    	String query = "DELETE FROM postavka WHERE postavshik_id="+postavka_idField.getText()+"";
    	executeQuery(query);
    	showPostavka();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionPostavka();
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
    	showPostavka();
    }
    
    public Connection getConnectionPostavka() {
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
      
    public ObservableList<Postavka> getPostavkaList(){
    	ObservableList<Postavka> postavkaList = FXCollections.observableArrayList();
    	Connection connection = getConnectionPostavka();
    	String query = "SELECT * FROM postavka ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Postavka postavka;
			while(rs.next()) {
				postavka = new Postavka(rs.getInt("postavka_id"),rs.getInt("postavshik_id"),rs.getInt("flower_id"),rs.getString("data_postavka"),rs.getInt("postavka_count"),rs.getInt("price"));
				postavkaList.add(postavka);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return postavkaList;
    }
    
    public void showPostavka() {
    	ObservableList<Postavka> list_postavka = getPostavkaList();
    	
    	postavka_idColumn.setCellValueFactory(new PropertyValueFactory<Postavka,Integer>("postavka_id"));
    	postavshik_idColumn.setCellValueFactory(new PropertyValueFactory<Postavka,Integer>("postavshik_id"));
    	flower_idColumn.setCellValueFactory(new PropertyValueFactory<Postavka,Integer>("flower_id"));
        data_postavkaColumn.setCellValueFactory(new PropertyValueFactory<Postavka,String>("data_postavka"));
        postavka_countColumn.setCellValueFactory(new PropertyValueFactory<Postavka,Integer>("postavka_count"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Postavka,Integer>("price"));
        
    	TableViewPostavka.setItems(list_postavka);
        
        
    }
}
