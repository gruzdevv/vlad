package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author grafinina
 */
public class MenuController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private StackPane parentContainer;

    @FXML
    private Button nextPostavkaButton;

    @FXML
    private Button nextFlowerButton;

    @FXML
    private Button nextWorkerButton;

    @FXML
    private Button nextKvitokButton;

    @FXML
    private Button nextBuyerButton;

    @FXML
    private Button nextPostavshikiButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void loadSecond(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
        Scene scene = button.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == nextPostavshikiButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PostavshikMenu.fxml"));
                Scene sceneStudents = new Scene(fxmlLoader.load());
                Stage stageStudents = new Stage();
                stageStudents.initModality(Modality.NONE);
                //убрать панельку
                stageStudents.initStyle(StageStyle.UNDECORATED); 
                stageStudents.setTitle("Учёт поставщиков");
                stageStudents.setScene(sceneStudents);
                stageStudents.show();
            } catch (IOException ex) {
                Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (event.getSource() == nextWorkerButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/WorkerMenu.fxml"));
                Scene sceneCity = new Scene(fxmlLoader.load());
                Stage stageCity = new Stage();
                stageCity.initModality(Modality.NONE);
                //убрать панельку
                stageCity.initStyle(StageStyle.UNDECORATED); 
                stageCity.setTitle("Учёт всех сотрудников");
                stageCity.setScene(sceneCity);
                stageCity.show();
            } catch (IOException ex) {
                Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (event.getSource() == nextBuyerButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/BuyerMenu.fxml"));
                Scene sceneDepartment = new Scene(fxmlLoader.load());
                Stage stageDepartment = new Stage();
                stageDepartment.initModality(Modality.NONE);
                //убрать панельку
                stageDepartment.initStyle(StageStyle.UNDECORATED); 
                stageDepartment.setTitle("Учёт покупателей");
                stageDepartment.setScene(sceneDepartment);
                stageDepartment.show();
            } catch (IOException ex) {
                Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (event.getSource() == nextKvitokButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/KvitokMenu.fxml"));
                Scene sceneTeacher = new Scene(fxmlLoader.load());
                Stage stageTeacher = new Stage();
                stageTeacher.initModality(Modality.NONE);
                //убрать панельку
                stageTeacher.initStyle(StageStyle.UNDECORATED); 
                stageTeacher.setTitle("Чеки магазина");
                stageTeacher.setScene(sceneTeacher);
                stageTeacher.show();
            } catch (IOException ex) {
                Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (event.getSource() == nextFlowerButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FlowerMenu.fxml"));
                Scene sceneNotad = new Scene(fxmlLoader.load());
                Stage stageNotad = new Stage();
                stageNotad.initModality(Modality.NONE);
                //убрать панельку
                stageNotad.initStyle(StageStyle.UNDECORATED); 
                stageNotad.setTitle("Журанал цветов");
                stageNotad.setScene(sceneNotad);
                stageNotad.show();
            } catch (IOException ex) {
                Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (event.getSource() == nextPostavkaButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PostavkaMenu.fxml"));
                Scene sceneStcourse = new Scene(fxmlLoader.load());
                Stage stageStcourse = new Stage();
                stageStcourse.initModality(Modality.NONE);
                //убрать панельку
                stageStcourse.initStyle(StageStyle.UNDECORATED); 
                stageStcourse.setTitle("Учёт поставок");
                stageStcourse.setScene(sceneStcourse);
                stageStcourse.show();
            } catch (IOException ex) {
                Logger.getLogger(PostavshikController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
//
//        if (event.getSource() == nextGrButton) {
//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/view/GrMenu.fxml"));
//                Scene scene = button.getScene();
//                root.translateYProperty().set(scene.getHeight());
//                
//                parentContainer.getChildren().add(root);
//                
//                Timeline timeline = new Timeline();
//                KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
//                KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//                timeline.getKeyFrames().add(kf);
//                timeline.setOnFinished(t -> {
//                    parentContainer.getChildren().remove(anchorRoot);
//                });
//                timeline.play();
//            } catch (IOException ex) {
//                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
