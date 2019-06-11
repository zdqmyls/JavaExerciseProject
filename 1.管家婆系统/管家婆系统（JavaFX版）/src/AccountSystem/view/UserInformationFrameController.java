package AccountSystem.view;

import AccountSystem.bean.Session;
import AccountSystem.bean.Users;
import AccountSystem.dao.UsersDao;
import AccountSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class UserInformationFrameController {

    private UsersDao usersDao = new UsersDao();
    private SimpleTools simpleTools = new SimpleTools();

    @FXML // fx:id="idTextField"
    private TextField idTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="alterPasswordVBox"
    private VBox alterPasswordVBox; // Value injected by FXMLLoader

    @FXML // fx:id="userImage"
    private ImageView userImage; // Value injected by FXMLLoader

    @FXML // fx:id="nameTextField"
    private TextField nameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="newPasswordTextField"
    private PasswordField newPasswordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="confirmNewPasswordTextField"
    private PasswordField confirmNewPasswordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="alterPasswordCheckBox"
    private CheckBox alterPasswordCheckBox; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        Session session = new Session();
        Users users = session.getUsers();
        String sql = "select * from tb_users where uId=" + users.getUserId();
        List list = (List) usersDao.getUsersDataBySql(sql);
        Users u = null;
        for (int i = 0; i < list.size(); i++) {
            u = (Users) list.get(i);
        }
        userImage.setImage(new Image("file:/" + u.getUserImagePath()));
        nameTextField.setText(u.getUserName());
        idTextField.setText(String.valueOf(u.getUserId()));
        passwordTextField.setText(u.getUserPassword());
    }

    @FXML // 点击图片更改图片
    public void alterImageViewEvent(MouseEvent mouseEvent) {
        String importPath = null;
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置默认文件过滤器
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("excel(*.xls)", "xls", "xlsx"));
        //打开文件选择框，并得到选中的文件
        File result = fileChooser.showOpenDialog(null);
        importPath = result.getAbsolutePath();
        String s = importPath.replaceAll("\\\\", "\\\\\\\\");// 数据库保存路径需要进行转义，否则斜杠会消失
        userImage.setImage(new Image("file:/" + importPath));
        String sql = "update tb_users set uImagePath='" + s + "' where uId=" + new Session().getUsers().getUserId();
        usersDao.alterUsersDataItem(sql);
    }

    // ”修改“按钮的事件监听器
    public void alterButtonEvent(ActionEvent event) {
        if (newPasswordTextField.getText().length() == 0) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "新密码不能为空！");
        }
        if (confirmNewPasswordTextField.getText().length() == 0) {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "确认密码不能为空！");
        }
        if (simpleTools.MD5(newPasswordTextField.getText()).equals(simpleTools.MD5(confirmNewPasswordTextField.getText()))) {
            String password = simpleTools.MD5(confirmNewPasswordTextField.getText());
            String sql = "update tb_users set uPassword='" + password + "' where uId=" + new Session().getUsers().getUserId();
            boolean b = usersDao.alterUsersDataItem(sql);
            if (b) {
                boolean isSuccess = simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "密码修改成功！");
                if (isSuccess) {
                    alterPasswordVBox.setVisible(false);
                    alterPasswordCheckBox.setSelected(false);
                }
            } else {
                simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "密码修改失败！");
            }
        } else {
            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "新密码和确认密码必须相同！");
        }

    }

    // ”取消“按钮的监听器
    public void cancelButtonEvent(ActionEvent event) {
        alterPasswordVBox.setVisible(false);
        alterPasswordCheckBox.setSelected(false);
    }

    // ”修改密码“复选框监听器
    public void alterPasswordCheckBoxEvent(ActionEvent event) {
        if (alterPasswordCheckBox.isSelected()) {
            alterPasswordVBox.setVisible(true);
        } else {
            alterPasswordVBox.setVisible(false);
        }
    }
}
