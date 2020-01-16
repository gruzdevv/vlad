package controller;

import java.io.IOException;
import library.Host;
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
 * @author INANOVA D.
 */
public class HostController implements Initializable {
    
    @FXML
    private TextField host_idField;
    
    @FXML
    private TextField host_nameField;

    @FXML
    private TextField host_phoneField;
    
    @FXML
    private TextField host_emailField;
    
    @FXML
    private Button insertHostButton;

    @FXML
    private Button updateHostButton;

    @FXML
    private Button deleteHostButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Host> TableViewHost;
    
    @FXML
    private TableColumn<Host, Integer> host_idColumn;
    
    @FXML
    private TableColumn<Host, String> host_nameColumn;

    @FXML
    private TableColumn<Host, String> host_phoneColumn;
    
    @FXML
    private TableColumn<Host, String> host_emailColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            
            Scene sceneMenu = new Scene(fxmlLoader.load());
            Stage stageMenu = new Stage();
            stageMenu.initModality(Modality.NONE);
            stageMenu.setTitle("Host table");
            stageMenu.setScene(sceneMenu);
            stageMenu.show();
            System.out.println(" *Кнопка 'btnOK' нажата");
            System.out.println("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertHostButton() {
    	String query = "insert into host values("+host_idField.getText()+",'"+host_nameField.getText()+"','"+host_phoneField.getText()+"','"+host_emailField.getText()+"')";
    	executeQuery(query);
    	showHost();
    }
    
    @FXML 
    private void updateHostButton() {
    String query = "UPDATE host SET host_name='"+host_nameField.getText()+"',host_phone='"+host_phoneField.getText()+"',host_email='"+host_emailField.getText()+"' WHERE host_id="+host_idField.getText()+"";
    executeQuery(query);
	showHost();
    }
    
    @FXML
    private void deleteHostButton() {
    	String query = "DELETE FROM host WHERE host_id="+host_idField.getText()+"";
    	executeQuery(query);
    	showHost();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionHost();
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
    	showHost();
    }
    
    public Connection getConnectionHost() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://mysql-162920.srv.hoster.ru:3306/srv162920_ramm", "srv162920_dasha", "dasha1999");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
      
    public ObservableList<Host> getHostList(){
    	ObservableList<Host> hostList = FXCollections.observableArrayList();
    	Connection connection = getConnectionHost();
    	String query = "SELECT * FROM host ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Host host;
			while(rs.next()) {
				host = new Host(rs.getInt("host_id"),rs.getString("host_name"),rs.getString("host_phone"),rs.getString("host_email"));
				hostList.add(host);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return hostList;
    }
    
    public void showHost() {
    	ObservableList<Host> list_host = getHostList();
    	
    	host_idColumn.setCellValueFactory(new PropertyValueFactory<Host,Integer>("host_id"));
    	host_nameColumn.setCellValueFactory(new PropertyValueFactory<Host,String>("host_name"));
    	host_phoneColumn.setCellValueFactory(new PropertyValueFactory<Host,String>("host_phone"));
        host_emailColumn.setCellValueFactory(new PropertyValueFactory<Host,String>("host_email"));
        
    	TableViewHost.setItems(list_host);
        
        
    }
}
