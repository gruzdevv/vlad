package controller;

import java.io.IOException;
import library.Gr;
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
public class GrController implements Initializable {
    
    @FXML
    private TextField gr_idField;
    
    @FXML
    private TextField gr_nameField;

    @FXML
    private TextField curator_idField;
    
    @FXML
    private Button insertGrButton;

    @FXML
    private Button updateGrButton;

    @FXML
    private Button deleteGrButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<Gr> TableViewGr;
    
    @FXML
    private TableColumn<Gr, Integer> gr_idColumn;
    
    @FXML
    private TableColumn<Gr, String> gr_nameColumn;

    @FXML
    private TableColumn<Gr, Integer> curator_idColumn;
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            //stageTeacher.setTitle("Mene table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertGrButton() {
    	String query = "insert into gr values("+gr_idField.getText()+",'"+gr_nameField.getText()+"',"+curator_idField.getText()+")";
    	executeQuery(query);
    	showGr();
    }
    
    @FXML 
    private void updateGrButton() {
    String query = "UPDATE gr SET gr_name='"+gr_nameField.getText()+"',curator_id="+curator_idField.getText()+" WHERE gr_id="+gr_idField.getText()+"";
    executeQuery(query);
	showGr();
    }
    
    @FXML
    private void deleteGrButton() {
    	String query = "DELETE FROM gr WHERE gr_id="+gr_idField.getText()+"";
    	executeQuery(query);
    	showGr();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionGr();
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
    	showGr();
    }
    
    public Connection getConnectionGr() {
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
      
    public ObservableList<Gr> getGrList(){
    	ObservableList<Gr> grList = FXCollections.observableArrayList();
    	Connection connection = getConnectionGr();
    	String query = "SELECT * FROM gr ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Gr gr;
			while(rs.next()) {
				gr = new Gr(rs.getInt("gr_id"),rs.getString("gr_name"),rs.getInt("curator_id"));
				grList.add(gr);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return grList;
    }
    
    public void showGr() {
    	ObservableList<Gr> list_gr = getGrList();
    	
    	gr_idColumn.setCellValueFactory(new PropertyValueFactory<Gr,Integer>("gr_id"));
    	gr_nameColumn.setCellValueFactory(new PropertyValueFactory<Gr,String>("gr_name"));
    	curator_idColumn.setCellValueFactory(new PropertyValueFactory<Gr,Integer>("curator_id"));

    	TableViewGr.setItems(list_gr);
        
        
    }
}
