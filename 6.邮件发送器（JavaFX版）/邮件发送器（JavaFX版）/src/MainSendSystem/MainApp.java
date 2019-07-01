package MainSendSystem;

import MainSendSystem.controller.SendAccountOptionFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("邮件发送器");
        initMainFrame();
    }

    // 加载主界面
    public void initMainFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MainFrame.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 加载发送账户设置界面
    public void initSendAccountOptionFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/SendAccountOptionFrame.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("设置");
            stage.setResizable(true);
            stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            SendAccountOptionFrameController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
