/**
 * Sample Skeleton for 'logupFrame.fxml' Controller Class
 */

package AccountSystem.controller;

import AccountSystem.MainApp;
import AccountSystem.bean.Session;
import AccountSystem.bean.Users;
import AccountSystem.dao.UsersDao;
import AccountSystem.tools.SimpleTools;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogupFrameController {
    private Stage logupStage;

    public Stage getLogupStage() {
        return logupStage;
    }

    public void setLogupStage(Stage logupStage) {
        this.logupStage = logupStage;
    }

    @FXML
    private SimpleTools simpleTools = new SimpleTools();

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="nameTextField"
    private TextField nameTextField; // Value injected by FXMLLoader

    @FXML
        // “注册”按钮事件监听器
    void loginButtonEvent() {
        if (nameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请按照文本框内容提示正确填写内容！");
        } else {
            UsersDao usersDao = new UsersDao();
            boolean isLoginSuccess = usersDao.loginUser(nameTextField.getText(), simpleTools.MD5(passwordTextField.getText()), "E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\images\\panda.png");
            if (isLoginSuccess == true) {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "恭喜您，注册成功，欢迎使用本系统！");
            } else {
                simpleTools.informationDialog(Alert.AlertType.ERROR, "错误", "错误", "抱歉，您注册失败了，请重新尝试！");
            }
        }
    }

    @FXML
        // “登录”按钮事件监听器
    void logupButtonEvent() {
        if (nameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请按照文本框内容提示正确填写内容！");
        } else {
            UsersDao usersDao = new UsersDao();
            Session session = new Session();
            Users logupUser = usersDao.logupUser(nameTextField.getText(), simpleTools.MD5(passwordTextField.getText()));
            /*--打包JAR包路径*/
            String imagePath=simpleTools.getJARPath();
            logupUser.setUserImagePath(imagePath+"\\images\\panda.png");
//            logupUser.setUserImagePath("images\\panda.png");
            if (logupUser != null) {
                /*设置通信对象*/
                session.setUsers(logupUser);// 建立登录成功通信
                boolean b = simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "恭喜" + session.getUsers().getUserName() + "，登录成功，欢迎使用本系统！");
                if (b) {
                    /*打开主窗口*/
                    new MainApp().initMainFrame();
                }
            } else {
                simpleTools.informationDialog(Alert.AlertType.ERROR, "错误", "错误", "抱歉，您登录失败了，请重新尝试！");
            }
        }
    }

}

