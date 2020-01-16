package controller;

import java.io.IOException;
import library.Operation;
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
 * @author IVANOVA D.
 */
public class OperationController implements Initializable {
    
    @FXML
    private TextField operation_idField;
    
    @FXML
    private TextField operation_typeField;

    @FXML
    private TextField operation_priceField;
    
    @FXML
    private Button insertOperationButton;

    @FXML
    private Button updateOperationButton;

    @FXML
    private Button deleteOperationButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Operation> TableViewOperation;
    
    @FXML
    private TableColumn<Operation, Integer> operation_idColumn;
    
    @FXML
    private TableColumn<Operation, String> operation_typeColumn;

    @FXML
    private TableColumn<Operation, String> operation_priceColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            
            Scene sceneMenu = new Scene(fxmlLoader.load());
            Stage stageMenu = new Stage();
            stageMenu.initModality(Modality.NONE);
            stageMenu.setTitle("Operation table");
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
    private void insertOperationButton() {
    	String query = "insert into operation values("+operation_idField.getText()+",'"+operation_typeField.getText()+"','"+operation_priceField.getText()+"')";
    	executeQuery(query);
    	showOperation();
    }
    
    @FXML 
    private void updateOperationButton() {
    String query = "UPDATE operation SET operation_type='"+operation_typeField.getText()+"',operation_price='"+operation_priceField.getText()+"' WHERE operation_id="+operation_idField.getText()+"";
    executeQuery(query);
	showOperation();
    }
    
    @FXML
    private void deleteOperationButton() {
    	String query = "DELETE FROM operation WHERE operation_id="+operation_idField.getText()+"";
    	executeQuery(query);
    	showOperation();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionOperation();
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
    	showOperation();
    }
    
    public Connection getConnectionOperation() {
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
      
    public ObservableList<Operation> getOperationList(){
    	ObservableList<Operation> operationList = FXCollections.observableArrayList();
    	Connection connection = getConnectionOperation();
    	String query = "SELECT * FROM operation ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Operation operation;
			while(rs.next()) {
				operation = new Operation(rs.getInt("operation_id"),rs.getString("operation_type"),rs.getString("operation_price"));
				operationList.add(operation);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return operationList;
    }
    
    public void showOperation() {
    	ObservableList<Operation> list_operation = getOperationList();
    	
    	operation_idColumn.setCellValueFactory(new PropertyValueFactory<Operation,Integer>("operation_id"));
    	operation_typeColumn.setCellValueFactory(new PropertyValueFactory<Operation,String>("operation_type"));
    	operation_priceColumn.setCellValueFactory(new PropertyValueFactory<Operation,String>("operation_price"));
        
    	TableViewOperation.setItems(list_operation);
        
        
    }
}
