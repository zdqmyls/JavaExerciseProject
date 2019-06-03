package AccountSystem.view;

import AccountSystem.MainApp;
import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MainPageController {
    private SimpleTools simpleTools=new SimpleTools();
    private RecordsDao recordsDao=new RecordsDao();
    private MainApp mainApp=new MainApp();
    private PublicTools publicTools=new PublicTools();

    @FXML // fx:id="userInfoMenuItem"
    private MenuItem userInfoMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="backupMenuItem"
    private MenuItem backupMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="recoverMenuItem"
    private MenuItem recoverMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="classificationColumn"
    private TableColumn<TableData, String> classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="totalOutputTextField"
    private TextField totalOutputTextField; // Value injected by FXMLLoader

    @FXML // fx:id="totalInputLabel"
    private Label totalInputLabel; // Value injected by FXMLLoader

    @FXML // fx:id="alterMenuItem"
    private MenuItem alterMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="totalOutputLabel"
    private Label totalOutputLabel; // Value injected by FXMLLoader

    @FXML // fx:id="totalInputTextField"
    private TextField totalInputTextField; // Value injected by FXMLLoader

    @FXML // fx:id="lineChartMenuItem"
    private MenuItem lineChartMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="checkMenuItem"
    private MenuItem checkMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="rightSplitPane"
    private AnchorPane rightSplitPane; // Value injected by FXMLLoader

    @FXML // fx:id="userImage"
    private ImageView userImage; // Value injected by FXMLLoader

    @FXML // fx:id="exitMenuItem"
    private MenuItem exitMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="dayMenuItem"
    private MenuItem dayMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="addClassificationMenuItem"
    private MenuItem addClassificationMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="leftSplitPane"
    private AnchorPane leftSplitPane; // Value injected by FXMLLoader

    @FXML // fx:id="barChartMenuItem"
    private MenuItem barChartMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="userNameLabel"
    private Label userNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="classificationCheckMenuItem"
    private MenuItem classificationCheckMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="pieChartMenuItem"
    private MenuItem pieChartMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="themeMenuItem"
    private MenuItem themeMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="helpMenuItem"
    private MenuItem helpMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="moneyColumn"
    private TableColumn<TableData, String> moneyColumn; // Value injected by FXMLLoader

    @FXML // fx:id="weekMenuItem"
    private MenuItem weekMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="alterContextMenu"
    private MenuItem alterContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="memoCheckMenuItem"
    private MenuItem memoCheckMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="seasonMenuItem"
    private MenuItem seasonMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="abutSoftMenuItem"
    private MenuItem abutSoftMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="importMenuItem"
    private MenuItem importMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="tableView"
    private TableView<TableData> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="balanceLabel"
    private Label balanceLabel; // Value injected by FXMLLoader

    @FXML // fx:id="typeColumn"
    private TableColumn<TableData, String> typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memoColumn"
    private TableColumn<TableData, String> memoColumn; // Value injected by FXMLLoader

    @FXML // fx:id="addContextMenu"
    private MenuItem addContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="monthMenuItem"
    private MenuItem monthMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="deleteContextMenu"
    private MenuItem deleteContextMenu; // Value injected by FXMLLoader

    @FXML // fx:id="balanceTextField"
    private TextField balanceTextField; // Value injected by FXMLLoader

    @FXML // fx:id="addMenuItem"
    private MenuItem addMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="yearMenuItem"
    private MenuItem yearMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="exportMenuItem"
    private MenuItem exportMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="dateCheckMenuItem"
    private MenuItem dateCheckMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumn"
    private TableColumn<TableData, String> dateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteMenuItem"
    private MenuItem deleteMenuItem; // Value injected by FXMLLoader

    @FXML // fx:id="idColumn"
    private TableColumn<TableData, String> idColumn; // Value injected by FXMLLoader

    @FXML // “导入”菜单项的事件监听器
    public void importMenuItemEvent(ActionEvent actionEvent) {
        do_importMenuItem_Event();
    }

    @FXML // “导出”菜单项的事件监听器
    public void exportMenuItemEvent(ActionEvent actionEvent) {
        do_exportMenuItem_Event();
    }

    @FXML // “备份”菜单项的事件监听器
    public void backupMenuItemEvent(ActionEvent actionEvent) {
        do_backupMenuItem_Event();
    }

    @FXML // “恢复”菜单项的事件监听器
    public void recoverMenuItemEvent(ActionEvent actionEvent) {
        do_recoverMenuItem_Event();
    }

    @FXML // “退出”菜单项的事件监听器
    public void exitMenuItemEvent(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML // ”添加“菜单项的事件监听器
    public void addMenuItemEvent(ActionEvent actionEvent) {
        do_addMenuItem_Event();
    }

    @FXML // ”删除“菜单项的事件监听器
    public void deleteMenuItemEvent(ActionEvent actionEvent) {
        do_deleteMenuItem_Event();
    }

    @FXML // ”修改“菜单项的事件监听器
    public void alterMenuItemEvent(ActionEvent actionEvent) {
        do_alterMenuItem_Event();
    }

    @FXML // “删除”右键菜单的事件监听器
    public void deleteContextMenuEvent(ActionEvent event) {
        do_deleteContextMenu_Event();
    }

    @FXML // “添加”右键菜单的事件监听器
    public void addContextMenuEvent(ActionEvent event) {
        do_addContextMenu_Event();
    }

    @FXML // “修改”右键菜单的事件监听器
    public void alterContextMenuEvent(ActionEvent event) {
        do_alterContextMenu_Event();
    }

    @FXML // ”查询“菜单项的事件监听器
    public void checkMenuItemEvent(ActionEvent actionEvent) {
        do_checkMenuItem_Event();
    }

    @FXML // ”条形图“菜单项的事件监听器
    public void barChartMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initBarChart();
    }

    @FXML // “折线图”菜单项的事件监听器
    public void lineChartMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initLineChart();
    }

    @FXML // ”饼图“菜单项的事件监听器
    public void pieChartMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initPieChart();
    }

    @FXML
    public void dayMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void weekMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void monthMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void seasonMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void yearMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void addClassificationMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void userInfoMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void themeMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void abutSoftMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void helpMenuItemEvent(ActionEvent actionEvent) {
    }

    @FXML // ”按日期查询“菜单项的事件监听器
    public void dateCheckMenuItemEvent(ActionEvent event) {
        do_dateCheckMenuItem_Event();
    }

    @FXML // ”按分类查询“菜单项的事件监听器
    public void classificationCheckMenuItemEvent(ActionEvent event) {
        do_classificationCheckMenuItem_Event();
    }

    @FXML // ”按备注查询“菜单项的事件监听器
    public void memoCheckMenuItemEvent(ActionEvent event) {
        do_memoCheckMenuItem_Event();
    }


    @FXML // 初始化界面
    public void initialize(){
        initFrame();// 初始化用户记录
        addDataToTableView();// 初始化表格数据
    }

    /**
     * 操作结果：“导入”按钮的事件方法
     */
    public void do_importMenuItem_Event(){
        String importPath = null;
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置默认文件过滤器
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("excel(*.xls)", "xls", "xlsx"));
        //打开文件选择框，并得到选中的文件
        File result = fileChooser.showOpenDialog(null);
        importPath = result.getAbsolutePath();
        try {
            //读取excel表内容（不包括表头）
            String[][] content = simpleTools.readExcelContentArray(new FileInputStream(importPath));
            Records records = new Records();
            Session session = new Session();
            boolean isSuccess = false;
            for (int i = 0; i < content.length; i++) {
                records.setRecordType(content[i][1]);
                records.setRecordMoney(Float.parseFloat(content[i][2]));
                records.setRecordClassification(content[i][3]);
                records.setRecordMemo(content[i][4]);
                records.setRecordDate(simpleTools.dateFormat(simpleTools.msToDate(Double.parseDouble(content[i][5])), "yyyy-MM-dd"));
                //添加数据到数据库
                isSuccess = new RecordsDao().addAccountRecords(session, records);
            }
            if (isSuccess) {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "导入excel数据成功");
                /*操作开始
                    【在添加数据成功后，打开查询面板显示添加后的数据】
                操作结束*/
            } else {
                simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "导入excel数据失败");
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 操作结果：“导出”按钮的事件方法
     */
    public void do_exportMenuItem_Event(){
        String exportPath = null;
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //打开保存文件选择框
        fileChooser.setInitialFileName("Excel");
        File result = fileChooser.showSaveDialog(null);
        exportPath = result.getAbsolutePath();
        //excel表格表头
        String[] title = {"序号", "类型", "金额", "分类", "备注", "日期"};
        //excel表格数据
        String sql = "select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records";
        String[][] values = simpleTools.objectToString(recordsDao.checkAccount(sql));
        //导出路径
        String exportExcelFilePath = simpleTools.exportExcel(title, values, exportPath);
        if (new File(exportExcelFilePath).exists()) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "导出excel成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "导出excel失败！");
        }
    }

    /**
     * 操作结果：“备份”按钮的事件方法
     */
    public void do_backupMenuItem_Event(){
        String savePath = null;
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置打开文件选择框默认输入的文件名
        fileChooser.setInitialFileName("Database_Backup_" + simpleTools.dateFormat(new Date(), "yyyy-MM-dd") + ".sql");
        //打开文件选择框
        File result = fileChooser.showSaveDialog(null);
        savePath = result.getAbsolutePath();
        boolean b = false;
        try {
            b = simpleTools.backup("root", "admin", savePath, "db_accountSystem");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if (b == true) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "备份数据库成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "备份数据库失败！");
        }
    }

    /**
     * 操作结果：“恢复”按钮的事件方法
     */
    public void do_recoverMenuItem_Event(){
        String recoverPath = null;
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置默认文件过滤器
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("sql(*.sql)", "sql"));
        //打开文件选择框
        File result = fileChooser.showOpenDialog(null);
        // 恢复文件的路径
        recoverPath = result.getAbsolutePath();
        boolean b = false;
        try {
            b = simpleTools.recover("root", "admin", "db_accountSystem", recoverPath);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (b == true) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "数据库恢复成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "数据库恢复失败！");
        }
    }

    /**
     * 操作结果：”删除“右键菜单的事件方法
     */
    public void do_deleteContextMenu_Event(){
        publicTools.public_do_deleteContextMenu_Event(tableView);
    }

    /**
     * 操作结果：”添加“右键菜单的事件方法
     */
    public void do_addContextMenu_Event(){
        publicTools.public_do_addContextMenu_Event(tableView);
    }

    /**
     * 操作结果：”修改“右键菜单的事件方法
     */
    public void do_alterContextMenu_Event(){
        publicTools.public_do_alterContextMenu_Event(tableView);
    }

    /**
     * 操作结果：”添加“菜单项的事件方法
     */
    public void do_addMenuItem_Event(){
        mainApp.initAddFrame();
    }

    /**
     * 操作结果：”删除“菜单项的事件方法
     */
    public void do_deleteMenuItem_Event(){
        mainApp.initDeleteFrame();
    }

    /**
     * 操作结果：”修改“菜单项的事件方法
     */
    public void do_alterMenuItem_Event(){
        mainApp.initAlterFrame();
    }

    /**
     * 操作结果：”查询“菜单项的事件方法
     */
    public void do_checkMenuItem_Event(){
        mainApp.initTableView();
    }

    /**
     * 操作结果：”按日期查询“菜单项的事件方法
     */
    public void do_dateCheckMenuItem_Event(){
        mainApp.initDateCheckTableView();
    }

    /**
     * 操作结果：”按分类查询“菜单项的事件方法
     */
    public void do_classificationCheckMenuItem_Event(){
        mainApp.initClassificationTableView();
    }

    /**
     * 操作结果：”按备注查询“菜单项的事件方法
     */
    public void do_memoCheckMenuItem_Event(){
        mainApp.initMemoTableView();
    }

    /**
     * 操作结果：初始化用户名、总支出、总收入及余额
     */
    public void initFrame() {
        // 获取结果值
        RecordsDao recordsDao = new RecordsDao();
        float totalOutput = recordsDao.getAccountTotalOutput("支出");
        float totalInput = recordsDao.getAccountTotalOutput("收入");
        float balance = totalInput - totalOutput;

        // 初始化值用户名，总收入，总支出及余额
        totalOutputTextField.setText(String.valueOf(totalOutput));
        totalInputTextField.setText(String.valueOf(totalInput));
        balanceTextField.setText(String.valueOf(balance));
        userNameLabel.setText(new Session().getUsers().getUserName());
    }

    /**
     * 操作结果：初始化数据表视图
     */
    public void addDataToTableView() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        memoColumn.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        List li = recordsDao.getAllTableDataByUser(new Session());
        ObservableList<TableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < li.size(); i++) {
            Records r = (Records) li.get(i);
            TableData td = new TableData(String.valueOf(r.getRecordId()), r.getRecordType(), String.valueOf(r.getRecordMoney()), r.getRecordClassification(), r.getRecordMemo(), r.getRecordDate());
            data.add(td);
//            System.out.println("id="+r.getRecordId()+"\t\ttype="+r.getRecordType()+"\t\tmoney"+r.getRecordMoney()+"\t\tclassification="+r.getRecordClassification()+"\t\tmemo="+r.getRecordMemo()+"\t\tdate="+r.getRecordDate());
        }

        tableView.setItems(data);
        // 向表格中添加列
//        table.getColumns().addAll(idColumn, typeColumn, moneyColumn, classificationColumn, memoColumn, dateColumn);
    }


}

