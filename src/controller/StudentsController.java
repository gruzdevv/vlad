package controller;

import java.io.IOException;
import library.Students;
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

public class StudentsController implements Initializable {

    @FXML
    private TextField student_idField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField middlenameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField city_idField;

    @FXML
    private TextField mother_nameField;

    @FXML
    private TextField mother_phoneField;

    @FXML
    private TextField father_nameField;

    @FXML
    private TextField father_phoneField;

    @FXML
    private Button closeButton;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Students> TableViewStudents;

    @FXML
    private TableColumn<Students, Integer> student_idColumn;

    @FXML
    private TableColumn<Students, String> lastnameColumn;

    @FXML
    private TableColumn<Students, String> firstnameColumn;

    @FXML
    private TableColumn<Students, String> middlenameColumn;

    @FXML
    private TableColumn<Students, String> phoneColumn;

    @FXML
    private TableColumn<Students, String> city_idColumn;

    @FXML
    private TableColumn<Students, String> mother_nameColumn;

    @FXML
    private TableColumn<Students, String> mother_phoneColumn;

    @FXML
    private TableColumn<Students, String> father_nameColumn;

    @FXML
    private TableColumn<Students, String> father_phoneColumn;

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
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
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
        String query = "insert into students (lastname, firstname, middlename, phone, city_id, mother_name, mother_phone, father_name, father_phone) values('" + lastnameField.getText() + "','" + firstnameField.getText() + "','" + middlenameField.getText() + "','" + phoneField.getText() + "'," + city_idField.getText() + ",'" + mother_nameField.getText() + "','" + mother_phoneField.getText() + "','" + father_nameField.getText() + "','" + father_phoneField.getText() + "')";
        executeQuery(query);
        showStudents();
    }

    @FXML
    private void updateButton() {
        String query = "UPDATE students SET lastname='" + lastnameField.getText() + "',firstname='" + firstnameField.getText() + "',middlename='" + middlenameField.getText() + "',phone='" + phoneField.getText() + "',city_id=" + city_idField.getText() + ",mother_name='" + mother_nameField.getText() + "',mother_phone='" + mother_phoneField.getText() + "',father_name='" + father_nameField.getText() + "',father_phone='" + father_phoneField.getText() + "' WHERE student_id=" + student_idField.getText() + "";
        executeQuery(query);
        showStudents();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM students WHERE student_id=" + student_idField.getText() + "";
        executeQuery(query);
        showStudents();
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
        showStudents();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://mysql-162551.srv.hoster.ru:3306/srv162551_db", "srv162551_root", "2010dima");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Students> getStudentsList() {
        ObservableList<Students> studentsList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT students.student_id, students.lastname, students.firstname, students.middlename, students.phone, city.city_name, students.mother_name, students.mother_phone, students.father_name, students.father_phone FROM students JOIN city ON students.city_id=city.city_id ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Students students;
            while (rs.next()) {
                students = new Students(rs.getInt("student_id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("middlename"), rs.getString("phone"), rs.getString("city_name"), rs.getString("mother_name"), rs.getString("mother_phone"), rs.getString("father_name"), rs.getString("father_phone"));
                studentsList.add(students);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showStudents() {
        ObservableList<Students> list_students = getStudentsList();

        student_idColumn.setCellValueFactory(new PropertyValueFactory<Students, Integer>("student_id"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("lastname"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("firstname"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("middlename"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("phone"));
        city_idColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("city_name"));
        mother_nameColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("mother_name"));
        mother_phoneColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("mother_phone"));
        father_nameColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("father_name"));
        father_phoneColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("father_phone"));

        TableViewStudents.setItems(list_students);

    }

}
