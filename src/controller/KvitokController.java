package controller;

import java.io.IOException;
import library.Kvitok;
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
public class KvitokController implements Initializable {
    
    @FXML
    private TextField kvitok_idField;
    
    @FXML
    private TextField kvitok_dataField;

    @FXML
    private TextField kvitok_timeField;
    
    @FXML
    private TextField summaField;
    
    @FXML
    private TextField worker_idField;
    
    @FXML
    private TextField buyer_idField;
    
    @FXML
    private Button insertKvitokButton;

    @FXML
    private Button updateKvitokButton;

    @FXML
    private Button deleteKvitokButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Kvitok> TableViewKvitok;
    
    @FXML
    private TableColumn<Kvitok, Integer> kvitok_idColumn;
    
    @FXML
    private TableColumn<Kvitok, String> kvitok_dataColumn;

    @FXML
    private TableColumn<Kvitok, String> kvitok_timeColumn;
    
    @FXML
    private TableColumn<Kvitok, Integer> summaColumn;
    
    @FXML
    private TableColumn<Kvitok, Integer> worker_idColumn;
    
    @FXML
    private TableColumn<Kvitok, Integer> buyer_idColumn;
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
    private void insertKvitokButton() {
    	String query = "insert into kvitok values("+kvitok_idField.getText()+",'"+kvitok_dataField.getText()+"','"+kvitok_timeField.getText()+"',"+summaField.getText()+","+worker_idField.getText()+","+buyer_idField.getText()+")";
    	executeQuery(query);
    	showKvitok();
    }
    
    @FXML 
    private void updateKvitokButton() {
    String query = "UPDATE kvitok SET kvitok_data='"+kvitok_dataField.getText()+"',kvitok_time='"+kvitok_timeField.getText()+"',summa="+summaField.getText()+",worker_id="+worker_idField.getText()+",buyer_id="+buyer_idField.getText()+" WHERE kvitok_id="+kvitok_idField.getText()+"";
    executeQuery(query);
	showKvitok();
    }
    
    @FXML
    private void deleteKvitokButton() {
    	String query = "DELETE FROM kvitok WHERE kvitok_id="+kvitok_idField.getText()+"";
    	executeQuery(query);
    	showKvitok();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionKvitok();
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
    	showKvitok();
    }
    
    public Connection getConnectionKvitok() {
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
      
    public ObservableList<Kvitok> getKvitokList(){
    	ObservableList<Kvitok> kvitokList = FXCollections.observableArrayList();
    	Connection connection = getConnectionKvitok();
    	String query = "SELECT * FROM kvitok ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Kvitok kvitok;
			while(rs.next()) {
				kvitok = new Kvitok(rs.getInt("kvitok_id"),rs.getString("kvitok_data"),rs.getString("kvitok_time"),rs.getInt("summa"),rs.getInt("worker_id"),rs.getInt("buyer_id"));
				kvitokList.add(kvitok);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return kvitokList;
    }
    
    public void showKvitok() {
    	ObservableList<Kvitok> list_kvitok = getKvitokList();
    	
    	kvitok_idColumn.setCellValueFactory(new PropertyValueFactory<Kvitok,Integer>("kvitok_id"));
    	kvitok_dataColumn.setCellValueFactory(new PropertyValueFactory<Kvitok,String>("kvitok_data"));
    	kvitok_timeColumn.setCellValueFactory(new PropertyValueFactory<Kvitok,String>("kvitok_time"));
        summaColumn.setCellValueFactory(new PropertyValueFactory<Kvitok,Integer>("summa"));
        worker_idColumn.setCellValueFactory(new PropertyValueFactory<Kvitok,Integer>("worker_id"));
        buyer_idColumn.setCellValueFactory(new PropertyValueFactory<Kvitok,Integer>("buyer_id"));
        
    	TableViewKvitok.setItems(list_kvitok);
        
        
    }
}
