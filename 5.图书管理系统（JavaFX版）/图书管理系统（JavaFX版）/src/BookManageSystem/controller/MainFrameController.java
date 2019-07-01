package BookManageSystem.controller;

import BookManageSystem.MainApp;
import BookManageSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainFrameController {

    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private MenuItem bookManageMenuItem;

    @FXML
    public ImageView mainBookManageImageView;

    @FXML
    private AnchorPane mainFrameAnchorPane;

    @FXML
    private MenuItem bookAddMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem bookTypeManageMenuItem;

    @FXML
    private MenuItem aboutSoftMenuItem;

    @FXML
    private MenuItem bookTypeAddMenuItem;

    public void initialize() {
        /*--打包JAR包路径--*/
        String imagePath=simpleTools.getJARPath();
        simpleTools.setMenuItemImage(new MenuItem[]{bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem,
                bookManageMenuItem, exitMenuItem, aboutSoftMenuItem}, new String[]{imagePath + "/images/add.png", imagePath+"/images/edit.png",
                 imagePath+"/images/add.png",
                imagePath+"/images/edit.png",
                imagePath+"/images/exit.png",
                imagePath+ "/images/about.png"});
        mainBookManageImageView.setImage(new Image("file:"+imagePath+"/images/bookmanagesystem.png"));
//        simpleTools.setMenuItemImage(new MenuItem[]{bookTypeAddMenuItem, bookTypeManageMenuItem, bookAddMenuItem,
//                bookManageMenuItem, exitMenuItem, aboutSoftMenuItem}, new String[]{"src/BookManageSystem/images/add.png", "src/BookManageSystem/images/edit" +
//                ".png", "src/BookManageSystem/images/add.png", "src/BookManageSystem/images/edit.png", "src/BookManageSystem/images/exit.png", "src/BookManageSystem/images/about.png"});
//        mainBookManageImageView.setImage(new Image("file:src/BookManageSystem/images/bookmanagesystem.png"));
    }

    public void do_bookManageMenuItem_event(ActionEvent event) {
        AnchorPane pane = new MainApp().initBookManageFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    public void do_exitMenuItem_vent(ActionEvent event) {
        System.exit(0);
    }

    public void do_aboutSoftMenuItem_event(ActionEvent event) {
        new MainApp().initAboutSoftFrame();
    }

    public void do_bookTypeAddMenuItem_event(ActionEvent event) {
        AnchorPane pane = new MainApp().initBookTypeAddFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    public void do_bookTypeManageMenuItem_event(ActionEvent event) {
        AnchorPane pane = new MainApp().initBookTypeManageFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }

    public void do_bookAddMenuItem_event(ActionEvent event) {
        AnchorPane pane = new MainApp().initBookAddFrame();
        mainFrameAnchorPane.getChildren().clear();
        mainFrameAnchorPane.getChildren().add(pane);
    }
}
