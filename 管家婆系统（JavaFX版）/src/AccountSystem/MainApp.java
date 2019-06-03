package AccountSystem;

import AccountSystem.view.MainFrameController;
import AccountSystem.view.MainPageController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import AccountSystem.view.LogupFrameController;
import java.io.IOException;

public class MainApp extends Application {

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Stage primaryStage;
    @FXML
    private HBox rootLayout;
    @FXML
    private AnchorPane mainFrame;

    @Override
    public void start(Stage primaryStage) throws Exception{
       this.primaryStage=primaryStage;
       this.primaryStage.setTitle("管家婆系统");

        initLogupFrame();

    }

    /**
     * 操作结果：登录界面
     */
    public void initLogupFrame(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("view/logupFrame.fxml"));
            rootLayout=(HBox)loader.load();

            Scene scene=new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            LogupFrameController controller=loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：主操作界面
     */
    public void initMainFrame(){
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/mainPage.fxml"));
            AnchorPane page=(AnchorPane)loader.load();
//            Parent page=FXMLLoader.load(getClass().getResource("view/mainFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("管家婆系统");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：主操作界面
     */
//    public void initMainFrame(){
//        try{
////            FXMLLoader loader=new FXMLLoader();
////            loader.setLocation(MainApp.class.getResource("view/mainFrame.fxml"));
////            AnchorPane page=(AnchorPane)loader.load();
//            Parent page=FXMLLoader.load(getClass().getResource("view/mainFrame.fxml"));
//
//            Stage mainFrameStage=new Stage();
//            mainFrameStage.setTitle("管家婆系统");
//            mainFrameStage.setResizable(true);
//            mainFrameStage.setAlwaysOnTop(false);
//            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
//            mainFrameStage.initOwner(primaryStage);
//            Scene scene=new Scene(page);
//            mainFrameStage.setScene(scene);
//
////            MainFrameController controller=loader.getController();
////            controller.setMainFrameStage(mainFrameStage);
////            controller.setMainApp(this);
//
//            mainFrameStage.show();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    /**
     * 操作结果：添加账目界面
     */
    public void initAddFrame(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/addAccountFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("添加账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：删除账目界面
     */
    public void initDeleteFrame(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/deleteAccountFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("删除账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：更改账目界面
     */
    public void initAlterFrame(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/alterAccountFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("更改账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：编辑界面
     */
    public void initEditFrame(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/editAccountFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("编辑账目");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：条件查询界面
     */
    public void initConditionCheckFrame(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/conditionCheckAccountFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("条件查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：查看数据表格界面
     */
    public void initTableView(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/tableView.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("查询结果");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：”按日期查询“结果界面
     */
    public void initDateCheckTableView(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/dateCheckFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("按日期查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：”按分类查询“结果界面
     */
    public void initClassificationTableView(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/classificationCheckFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("按分类查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：”按备注查询“结果界面
     */
    public void initMemoTableView(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/memoCheckFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("按备注查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：“条形图”查询结果界面
     */
    public void initBarChart(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/barChartFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("条形图");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：“折线图”查询结果界面
     */
    public void initLineChart(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/lineChartFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("折线图");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：”饼图“查询结果界面
     */
    public void initPieChart(){
        try{
            Parent page=FXMLLoader.load(getClass().getResource("view/pieChartFrame.fxml"));

            Stage mainFrameStage=new Stage();
            mainFrameStage.setTitle("饼图");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            mainFrameStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            mainFrameStage.setScene(scene);

            mainFrameStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args){
        launch(args);
    }

}
