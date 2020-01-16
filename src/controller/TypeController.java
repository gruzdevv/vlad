package controller;

import java.io.IOException;
import library.TypeA;
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
public class TypeController implements Initializable {
    
    @FXML
    private TextField type_idField;
    
    @FXML
    private TextField type_nameField;
    
    @FXML
    private Button insertTypeButton;

    @FXML
    private Button updateTypeButton;

    @FXML
    private Button deleteTypeButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private TableView<TypeA> TableViewType;
    
    @FXML
    private TableColumn<TypeA, Integer> type_idColumn;
    
    @FXML
    private TableColumn<TypeA, Integer> type_nameColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            stageTeacher.setTitle("Type table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            //System.out.println(" *Кнопка 'btnOK' нажата");
            //System.out.println("");
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertTypeButton() {
    	String query = "insert into type values("+type_idField.getText()+",'"+type_nameField.getText()+"')";
    	executeQuery(query);
    	showType();
    }
    
    @FXML 
    private void updateTypeButton() {
    String query = "UPDATE type SET type_id="+type_idField.getText()+",type_name='"+type_nameField.getText()+"'";
    executeQuery(query);
	showType();
    }
    
    @FXML
    private void deleteTypeButton() {
    	String query = "DELETE FROM type WHERE type_id="+type_idField.getText()+"";
    	executeQuery(query);
    	showType();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnectionType();
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
    	showType();
    }
    
    public Connection getConnectionType() {
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
      
    public ObservableList<TypeA> getTypeList(){
    	ObservableList<TypeA> typeList = FXCollections.observableArrayList();
    	Connection connection = getConnectionType();
    	String query = "SELECT * FROM type ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			TypeA type;
			while(rs.next()) {
				type = new TypeA(rs.getInt("type_id"),rs.getString("type_name"));
				typeList.add(type);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return typeList;
    }
    
    public void showType() {
    	ObservableList<TypeA> list_type = getTypeList();
    	
    	type_idColumn.setCellValueFactory(new PropertyValueFactory<TypeA,Integer>("type_id"));
    	type_nameColumn.setCellValueFactory(new PropertyValueFactory<TypeA,Integer>("type_name"));
        
    	TableViewType.setItems(list_type);
        
        
    }
}
