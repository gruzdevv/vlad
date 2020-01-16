package controller;

import java.io.IOException;
import library.Nedvizh;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NedvizhController implements Initializable {

    @FXML
    private TextField nedvizh_idField;

    @FXML
    private TextField nedvizh_nameField;

    @FXML
    private TextField raion_idField;

    @FXML
    private TextField street_idField;

    @FXML
    private TextField typened_idField;

    @FXML
    private TextField nedvizh_priceField;
    
    @FXML
    private Button closeButton;

    @FXML
    private Button insertNedvizhButton;

    @FXML
    private Button updateNedvizhButton;

    @FXML
    private Button deleteNedvizhButton;

    @FXML
    private TableView<Nedvizh> TableViewNedvizh;

    @FXML
    private TableColumn<Nedvizh, Integer> nedvizh_idColumn;

    @FXML
    private TableColumn<Nedvizh, String> nedvizh_nameColumn;

    @FXML
    private TableColumn<Nedvizh, Integer> raion_idColumn;

    @FXML
    private TableColumn<Nedvizh, Integer> street_idColumn;

    @FXML
    private TableColumn<Nedvizh, Integer> typened_idColumn;

    @FXML
    private TableColumn<Nedvizh, String> nedvizh_priceColumn;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            stageTeacher.setTitle("Nedvizh table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            System.out.println(" *Кнопка 'btnOK' нажата");
            System.out.println("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(NedvizhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private Button BackButton;
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        if (event.getSource() == BackButton){
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
        }  
    }
    
    @FXML
    private void insertNedvizhButton() {
        String query = "insert into nedvizh values("+ nedvizh_idField.getText() + ",'" + nedvizh_nameField.getText() + "'," + raion_idField.getText() + "," + street_idField.getText() + "," + typened_idField.getText() + ",'" + nedvizh_priceField.getText() +"')";
        executeQuery(query);
        showNedvizh();
    }

    @FXML
    private void updateNedvizhButton() {
        String query = "UPDATE nedvizh SET nedvizh_name='" + nedvizh_nameField.getText() + "',raion_id=" + raion_idField.getText() + ",street_id=" + street_idField.getText() + ",typened_id=" + typened_idField.getText() + ",nedvizh_price='" + nedvizh_priceField.getText() + "' WHERE nedvizh_id=" + nedvizh_idField.getText() + "";
        executeQuery(query);
        showNedvizh();
    }

    @FXML
    private void deleteNedvizhButton() {
        String query = "DELETE FROM nedvizh WHERE nedvizh_id=" + nedvizh_idField.getText() + "";
        executeQuery(query);
        showNedvizh();
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
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
        showNedvizh();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vladdb", "root", "pi159357");
            //conn = DriverManager.getConnection("jdbc:mysql://mysql-162920.srv.hoster.ru:3306/srv162920_vladdb", "srv162920_vlad", "vlad2000");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Nedvizh> getNedvizhList() {
        ObservableList<Nedvizh> nedvizhList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM nedvizh ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Nedvizh nedvizh;
            while (rs.next()) {
                nedvizh = new Nedvizh(rs.getInt("nedvizh_id"), rs.getString("nedvizh_name"), rs.getInt("raion_id"), rs.getInt("street_id"), rs.getInt("typened_id"), rs.getString("nedvizh_price"));
                nedvizhList.add(nedvizh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nedvizhList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showNedvizh() {
        ObservableList<Nedvizh> list_nedvizh = getNedvizhList();

        nedvizh_idColumn.setCellValueFactory(new PropertyValueFactory<Nedvizh, Integer>("nedvizh_id"));
        nedvizh_nameColumn.setCellValueFactory(new PropertyValueFactory<Nedvizh, String>("nedvizh_name"));
        raion_idColumn.setCellValueFactory(new PropertyValueFactory<Nedvizh, Integer>("raion_id"));
        street_idColumn.setCellValueFactory(new PropertyValueFactory<Nedvizh, Integer>("street_id"));
        typened_idColumn.setCellValueFactory(new PropertyValueFactory<Nedvizh, Integer>("typened_id"));
        nedvizh_priceColumn.setCellValueFactory(new PropertyValueFactory<Nedvizh, String>("nedvizh_price"));
        
        TableViewNedvizh.setItems(list_nedvizh);

    }

}
