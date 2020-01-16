package controller;

import java.io.IOException;
import library.Animal;
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
    private Button insertAnimalButton;

    @FXML
    private Button updateAnimalButton;

    @FXML
    private Button deleteAnimalButton;

    @FXML
    private TableView<Animal> TableViewAnimal;

    @FXML
    private TableColumn<Animal, Integer> animal_idColumn;

    @FXML
    private TableColumn<Animal, String> animal_nickColumn;

    @FXML
    private TableColumn<Animal, String> animal_nameColumn;

    @FXML
    private TableColumn<Animal, String> animal_birthdayColumn;

    @FXML
    private TableColumn<Animal, Integer> type_idColumn;

    @FXML
    private TableColumn<Animal, Integer> host_idColumn;
    
    @FXML
    public void closeButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Scene sceneTeacher = new Scene(fxmlLoader.load());
            Stage stageTeacher = new Stage();
            stageTeacher.initModality(Modality.NONE);
            stageTeacher.setTitle("Animal table");
            stageTeacher.setScene(sceneTeacher);
            stageTeacher.show();
            System.out.println(" *Кнопка 'btnOK' нажата");
            System.out.println("");
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(AnimalController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void insertAnimalButton() {
        String query = "insert into animal values("+ nedvizh_idField.getText() + ",'" + nedvizh_nameField.getText() + "','" + raion_idField.getText() + "','" + street_idField.getText() + "'," + typened_idField.getText() + "," + nedvizh_priceField.getText() +")";
        executeQuery(query);
        showAnimal();
    }

    @FXML
    private void updateAnimalButton() {
        String query = "UPDATE animal SET animal_nick='" + nedvizh_nameField.getText() + "',animal_name='" + raion_idField.getText() + "',animal_birthday='" + street_idField.getText() + "',type_id=" + typened_idField.getText() + ",host_id=" + nedvizh_priceField.getText() + " WHERE animal_id=" + nedvizh_idField.getText() + "";
        executeQuery(query);
        showAnimal();
    }

    @FXML
    private void deleteAnimalButton() {
        String query = "DELETE FROM animal WHERE animal_id=" + nedvizh_idField.getText() + "";
        executeQuery(query);
        showAnimal();
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
        showAnimal();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql-162920.srv.hoster.ru:3306/srv162920_ramm", "srv162920_dasha", "dasha1999");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Animal> getAnimalList() {
        ObservableList<Animal> animalList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM animal ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Animal animal;
            while (rs.next()) {
                animal = new Animal(rs.getInt("animal_id"), rs.getString("animal_nick"), rs.getString("animal_name"), rs.getString("animal_birthday"), rs.getInt("type_id"), rs.getInt("host_id"));
                animalList.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animalList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showAnimal() {
        ObservableList<Animal> list_animal = getAnimalList();

        animal_idColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("animal_id"));
        animal_nickColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("animal_nick"));
        animal_nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("animal_name"));
        animal_birthdayColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("animal_birthday"));
        type_idColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("type_id"));
        host_idColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("host_id"));
        
        TableViewAnimal.setItems(list_animal);

    }

}
