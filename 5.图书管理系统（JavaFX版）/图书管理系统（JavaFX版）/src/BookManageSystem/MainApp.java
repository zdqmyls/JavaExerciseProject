package BookManageSystem;

import BookManageSystem.controller.LogupFrameController;
import BookManageSystem.controller.SoftInformationFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("图书馆管理系统 ");
        initLogupFrame();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // 登录界面
    public void initLogupFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/logupFrame.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("登录");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            LogupFrameController controller = loader.getController();
            controller.setStage(primaryStage);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 主界面
    public void initMainFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/mainFrame.fxml"));
            AnchorPane root = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("图书管理系统主界面");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);

            Scene scene = new Scene(root);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 图书类别添加界面
    public AnchorPane initBookTypeAddFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/bookTypeAddFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 图书类别管理界面
    public AnchorPane initBookTypeManageFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/bookTypeManageFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 图书添加界面
    public AnchorPane initBookAddFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/bookAddFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 图书管理界面
    public AnchorPane initBookManageFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/bookManageFrame.fxml"));
            AnchorPane root = loader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 关于软件界面
    public Scene initAboutSoftFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/softInformationFrame.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("关于软件");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            SoftInformationFrameController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);

            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
