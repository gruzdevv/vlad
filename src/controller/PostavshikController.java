package controller;

import java.io.IOException;
import library.Postavshik;
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

public class PostavshikController implements Initializable {

    @FXML
    private TextField postavshik_idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField fioField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button closeButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Postavshik> TableViewPostavshik;

    @FXML
    private TableColumn<Postavshik, Integer> postavshik_idColumn;

    @FXML
    private TableColumn<Postavshik, String> nameColumn;

    @FXML
    private TableColumn<Postavshik, String> fioColumn;

    @FXML
    private TableColumn<Postavshik, String> addressColumn;

    @FXML
    private TableColumn<Postavshik, String> phoneColumn;

    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            stageTeacher.setTitle("Teacher table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            System.out.println(" *Кнопка 'btnOK' нажата");
            System.out.println("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertButton() {
        String query = "insert into postavshik values("+ postavshik_idField.getText() + ",'" + nameField.getText() + "','" + fioField.getText() + "','" + addressField.getText() + "','" + phoneField.getText() + "')";
        executeQuery(query);
        showPostavshik();
    }

    @FXML
    private void updateButton() {
        String query = "UPDATE postavshik SET name='" + nameField.getText() + "',fio='" + fioField.getText() + "',address='" + addressField.getText() + "',phone='" + phoneField.getText() + "' WHERE postavshik_id=" + postavshik_idField.getText() + "";
        executeQuery(query);
        showPostavshik();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM postavshik WHERE postavshik_id=" + postavshik_idField.getText() + "";
        executeQuery(query);
        showPostavshik();
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
        showPostavshik();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru:3306/srv162551_grafinina", "srv162551_root", "2010dima");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Postavshik> getPostavshikList() {
        ObservableList<Postavshik> postavshikList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM postavshik ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Postavshik postavshik;
            while (rs.next()) {
                postavshik = new Postavshik(rs.getInt("postavshik_id"), rs.getString("name"), rs.getString("fio"), rs.getString("address"), rs.getString("phone"));
                postavshikList.add(postavshik);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postavshikList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showPostavshik() {
        ObservableList<Postavshik> list_postavshik = getPostavshikList();

        postavshik_idColumn.setCellValueFactory(new PropertyValueFactory<Postavshik, Integer>("postavshik_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Postavshik, String>("name"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<Postavshik, String>("fio"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Postavshik, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Postavshik, String>("phone"));

        TableViewPostavshik.setItems(list_postavshik);

    }

}
