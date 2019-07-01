package BookManageSystem.controller;

import BookManageSystem.MainApp;
import BookManageSystem.tools.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LogupFrameController {
    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button logupButton;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label systemLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Label passwordLabel;

    public void initialize() {
        /*--打包JAR包路径--*/
        String imagePath=simpleTools.getJARPath();
        String[] imagePaths = new String[]{imagePath+"/images/logo.png", imagePath+"/images/userName.png",
                imagePath+"/images/password.png",
                imagePath+"/images/login.png", imagePath+"/images/reset.png"};
        // 给组件添加图标
        Labeled[] labeleds = new Labeled[]{systemLabel, userNameLabel, passwordLabel, logupButton, resetButton};
//        String[] imagePaths = new String[]{"src/BookManageSystem/images/logo.png", "src/BookManageSystem/images/userName.png",
//                "src/BookManageSystem/images/password.png",
//                "src/BookManageSystem/images/login.png", "src/BookManageSystem/images/reset.png"};
        simpleTools.setLabeledImage(labeleds, imagePaths);
    }

    // “登录”按钮的事件监听器方法
    public void logupButtonEvent(ActionEvent event) {
        stage.close();
        boolean isOK = simpleTools.isLogIn(userNameTextField, passwordTextField, "张三", "123456");
        if (isOK) {
            new MainApp().initMainFrame();
        }
    }

    // “重置”按钮的事件监听器方法
    public void resetButtonEvent(ActionEvent event) {
        userNameTextField.setText("");
        passwordTextField.setText("");
    }
}
