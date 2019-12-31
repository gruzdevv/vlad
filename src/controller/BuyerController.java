package controller;

import java.io.IOException;
import library.Buyer;
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
public class BuyerController implements Initializable {
    
    @FXML
    private TextField buyer_idField;
    
    @FXML
    private TextField fioField;

    @FXML
    private TextField polField;
    
    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;
    
    @FXML
    private Button insertBuyerButton;

    @FXML
    private Button updateBuyerButton;

    @FXML
    private Button deleteBuyerButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Buyer> TableViewBuyer;
    
    @FXML
    private TableColumn<Buyer, Integer> buyer_idColumn;
    
    @FXML
    private TableColumn<Buyer, String> fioColumn;

    @FXML
    private TableColumn<Buyer, String> polColumn;
    
    @FXML
    private TableColumn<Buyer, String> addressColumn;
    
    @FXML
    private TableColumn<Buyer, String> phoneColumn;
    
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
    private void insertBuyerButton() {
    	String query = "insert into buyer values("+buyer_idField.getText()+",'"+fioField.getText()+"','"+polField.getText()+"','"+addressField.getText()+"','"+phoneField.getText()+"')";
    	executeQuery(query);
    	showBuyer();
    }
    
    @FXML 
    private void updateBuyerButton() {
    String query = "UPDATE buyer SET fio='"+fioField.getText()+"',pol='"+polField.getText()+"',address='"+addressField.getText()+"',phone='"+phoneField.getText()+"' WHERE buyer_id="+buyer_idField.getText()+"";
    executeQuery(query);
	showBuyer();
    }
    
    @FXML
    private void deleteBuyerButton() {
    	String query = "DELETE FROM buyer WHERE buyer_id="+buyer_idField.getText()+"";
    	executeQuery(query);
    	showBuyer();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionBuyer();
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
    	showBuyer();
    }
    
    public Connection getConnectionBuyer() {
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
      
    public ObservableList<Buyer> getBuyerList(){
    	ObservableList<Buyer> buyerList = FXCollections.observableArrayList();
    	Connection connection = getConnectionBuyer();
    	String query = "SELECT * FROM buyer ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Buyer buyer;
			while(rs.next()) {
				buyer = new Buyer(rs.getInt("buyer_id"),rs.getString("fio"),rs.getString("pol"),rs.getString("address"),rs.getString("phone"));
				buyerList.add(buyer);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return buyerList;
    }
    
    public void showBuyer() {
    	ObservableList<Buyer> list_buyer = getBuyerList();
    	
    	buyer_idColumn.setCellValueFactory(new PropertyValueFactory<Buyer,Integer>("buyer_id"));
    	fioColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("fio"));
    	polColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("pol"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("phone"));
        
    	TableViewBuyer.setItems(list_buyer);
        
        
    }
}
