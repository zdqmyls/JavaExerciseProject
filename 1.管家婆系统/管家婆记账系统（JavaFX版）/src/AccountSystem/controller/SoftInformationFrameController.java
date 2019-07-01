package AccountSystem.controller;

import AccountSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SoftInformationFrameController {
    private Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private ImageView imageView;

    @FXML // 初始化界面
    public void initialize() {
        // 初始化链接组件的超链接
        hyperlink.setText("相关GitHub链接");
        /*--打包JAR包路径--*/
        String imagePath=new SimpleTools().getJARPath();
        imageView.setImage(new Image("file:"+imagePath+"/images/panda.png"));
//        imageView.setImage(new Image("src/AccountSystem/images/panda.png"));
    }

    // “关闭”按钮的事件监听器
    public void closeButtonEvent(ActionEvent event) {
        dialogStage.close();
    }

    // 超链接的事件监听器
    public void hyperlinkEvent(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/lck100/JavaExerciseProject/tree/master/1.%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F/%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F%EF%BC%88JavaFX%E7%89%88%EF%BC%89"));
    }
}
