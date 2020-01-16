package controller;

import java.io.IOException;
import library.Order;
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
public class OrderController implements Initializable {
    
    @FXML
    private TextField order_idField;
    
    @FXML
    private TextField animal_idField;

    @FXML
    private TextField operation_idField;
    
    @FXML
    private TextField order_dateField;
    
    @FXML
    private Button insertOrderButton;

    @FXML
    private Button updateOrderButton;

    @FXML
    private Button deleteOrderButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Order> TableViewOrder;
    
    @FXML
    private TableColumn<Order, Integer> order_idColumn;
    
    @FXML
    private TableColumn<Order, Integer> animal_idColumn;

    @FXML
    private TableColumn<Order, Integer> operation_idColumn;
    
    @FXML
    private TableColumn<Order, String> order_dateColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            
            Scene sceneMenu = new Scene(fxmlLoader.load());
            Stage stageMenu = new Stage();
            stageMenu.initModality(Modality.NONE);
            stageMenu.setTitle("Order table");
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
    private void insertOrderButton() {
    	String query = "insert into orders values("+order_idField.getText()+","+animal_idField.getText()+","+operation_idField.getText()+",'"+order_dateField.getText()+"')";
    	executeQuery(query);
    	showOrder();
    }
    
    @FXML 
    private void updateOrderButton() {
    String query = "UPDATE orders SET animal_id="+animal_idField.getText()+",operation_id="+operation_idField.getText()+",order_date='"+order_dateField.getText()+"' WHERE order_id="+order_idField.getText()+"";
    executeQuery(query);
	showOrder();
    }
    
    @FXML
    private void deleteOrderButton() {
    	String query = "DELETE FROM orders WHERE order_id="+order_idField.getText()+"";
    	executeQuery(query);
    	showOrder();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionOrder();
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
    	showOrder();
    }
    
    public Connection getConnectionOrder() {
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
      
    public ObservableList<Order> getOrderList(){
    	ObservableList<Order> orderList = FXCollections.observableArrayList();
    	Connection connection = getConnectionOrder();
    	String query = "SELECT * FROM orders ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Order order;
			while(rs.next()) {
				order = new Order(rs.getInt("order_id"),rs.getInt("animal_id"),rs.getInt("operation_id"),rs.getString("order_date"));
				orderList.add(order);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return orderList;
    }
    
    public void showOrder() {
    	ObservableList<Order> list_order = getOrderList();
    	
    	order_idColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("order_id"));
    	animal_idColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("animal_id"));
    	operation_idColumn.setCellValueFactory(new PropertyValueFactory<Order,Integer>("operation_id"));
        order_dateColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("order_date"));
        
    	TableViewOrder.setItems(list_order);
        
        
    }
}
