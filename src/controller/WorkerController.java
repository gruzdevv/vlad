package controller;

import java.io.IOException;
import library.Worker;
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
public class WorkerController implements Initializable {
    
    @FXML
    private TextField worker_idField;
    
    @FXML
    private TextField fioField;

    @FXML
    private TextField postField;
    
    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;
    
    @FXML
    private Button insertWorkerButton;

    @FXML
    private Button updateWorkerButton;

    @FXML
    private Button deleteWorkerButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Worker> TableViewWorker;
    
    @FXML
    private TableColumn<Worker, Integer> worker_idColumn;
    
    @FXML
    private TableColumn<Worker, String> fioColumn;

    @FXML
    private TableColumn<Worker, String> postColumn;
    
    @FXML
    private TableColumn<Worker, String> addressColumn;
    
    @FXML
    private TableColumn<Worker, String> phoneColumn;
    
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
    private void insertWorkerButton() {
    	String query = "insert into worker values("+worker_idField.getText()+",'"+fioField.getText()+"','"+postField.getText()+"','"+addressField.getText()+"','"+phoneField.getText()+"')";
    	executeQuery(query);
    	showWorker();
    }
    
    @FXML 
    private void updateWorkerButton() {
    String query = "UPDATE worker SET fio='"+fioField.getText()+"',post='"+postField.getText()+"',address='"+addressField.getText()+"',phone='"+phoneField.getText()+"' WHERE worker_id="+worker_idField.getText()+"";
    executeQuery(query);
	showWorker();
    }
    
    @FXML
    private void deleteWorkerButton() {
    	String query = "DELETE FROM worker WHERE worker_id="+worker_idField.getText()+"";
    	executeQuery(query);
    	showWorker();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionWorker();
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
    	showWorker();
    }
    
    public Connection getConnectionWorker() {
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
      
    public ObservableList<Worker> getWorkerList(){
    	ObservableList<Worker> workerList = FXCollections.observableArrayList();
    	Connection connection = getConnectionWorker();
    	String query = "SELECT * FROM worker ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Worker worker;
			while(rs.next()) {
				worker = new Worker(rs.getInt("worker_id"),rs.getString("fio"),rs.getString("post"),rs.getString("address"),rs.getString("phone"));
				workerList.add(worker);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return workerList;
    }
    
    public void showWorker() {
    	ObservableList<Worker> list_worker = getWorkerList();
    	
    	worker_idColumn.setCellValueFactory(new PropertyValueFactory<Worker,Integer>("worker_id"));
    	fioColumn.setCellValueFactory(new PropertyValueFactory<Worker,String>("fio"));
    	postColumn.setCellValueFactory(new PropertyValueFactory<Worker,String>("post"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Worker,String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Worker,String>("phone"));
        
    	TableViewWorker.setItems(list_worker);
        
        
    }
}
