package controller;

import java.io.IOException;
import library.City;
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
public class CityController implements Initializable {
    
    @FXML
    private TextField city_idField;
    
    @FXML
    private TextField city_nameField;

    @FXML
    private TextField distanceField;
    
    @FXML
    private Button insertCityButton;

    @FXML
    private Button updateCityButton;

    @FXML
    private Button deleteCityButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<City> TableViewCity;
    
    @FXML
    private TableColumn<City, Integer> city_idColumn;
    
    @FXML
    private TableColumn<City, String> city_nameColumn;

    @FXML
    private TableColumn<City, Integer> distanceColumn;
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        if (event.getSource()== BackButton){
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        }  
    }
    
    @FXML
    private void insertCityButton() {
    	String query = "insert into city values("+city_idField.getText()+",'"+city_nameField.getText()+"',"+distanceField.getText()+")";
    	executeQuery(query);
    	showCity();
    }
    
    @FXML 
    private void updateCityButton() {
    String query = "UPDATE city SET city_name='"+city_nameField.getText()+"',distance="+distanceField.getText()+" WHERE city_id="+city_idField.getText()+"";
    executeQuery(query);
	showCity();
    }
    
    @FXML
    private void deleteCityButton() {
    	String query = "DELETE FROM city WHERE city_id="+city_idField.getText()+"";
    	executeQuery(query);
    	showCity();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionCity();
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
    	showCity();
    }
    
    public Connection getConnectionCity() {
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
      
    public ObservableList<City> getCityList(){
    	ObservableList<City> cityList = FXCollections.observableArrayList();
    	Connection connection = getConnectionCity();
    	String query = "SELECT * FROM city ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			City city;
			while(rs.next()) {
				city = new City(rs.getInt("city_id"),rs.getString("city_name"),rs.getInt("distance"));
				cityList.add(city);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return cityList;
    }
    
    public void showCity() {
    	ObservableList<City> list_city = getCityList();
    	
    	city_idColumn.setCellValueFactory(new PropertyValueFactory<City,Integer>("city_id"));
    	city_nameColumn.setCellValueFactory(new PropertyValueFactory<City,String>("city_name"));
    	distanceColumn.setCellValueFactory(new PropertyValueFactory<City,Integer>("distance"));

    	TableViewCity.setItems(list_city);
        
        
    }
}
