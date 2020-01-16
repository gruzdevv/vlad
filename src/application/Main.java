package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stageMain) throws Exception {
        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                "/view/Menu.fxml"));
        Scene sceneMain = new Scene(parent);
        stageMain.setScene(sceneMain);
        stageMain.setTitle("Ветеринарная клиника");
        stageMain.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
