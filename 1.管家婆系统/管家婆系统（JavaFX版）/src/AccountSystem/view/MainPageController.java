package AccountSystem.view;

import AccountSystem.MainApp;
import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.bean.Users;
import AccountSystem.dao.JDBCUtils;
import AccountSystem.dao.RecordsDao;
import AccountSystem.dao.UsersDao;
import AccountSystem.tools.DateTools;
import AccountSystem.tools.PublicTools;
import AccountSystem.tools.SimpleTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPageController {
    private SimpleTools simpleTools = new SimpleTools();
    private RecordsDao recordsDao = new RecordsDao();
    private MainApp mainApp = new MainApp();
    private PublicTools publicTools = new PublicTools();
    private UsersDao usersDao = new UsersDao();
    private DateTools dateTools = new DateTools();
    private JDBCUtils jdbcUtils = new JDBCUtils();

    @FXML
    public RadioMenuItem defaultRadioMenuItem;

    @FXML
    public RadioMenuItem blackRadioMenuItem;

    @FXML
    public RadioMenuItem whiteRadioMenuItem;

    @FXML // fx:id="classificationColumn"
    private TableColumn<TableData, String> classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="totalOutputTextField"
    private TextField totalOutputTextField; // Value injected by FXMLLoader

    @FXML // fx:id="totalInputTextField"
    private TextField totalInputTextField; // Value injected by FXMLLoader

    @FXML // fx:id="userImage"
    private ImageView userImage; // Value injected by FXMLLoader

    @FXML // fx:id="userNameLabel"
    private Label userNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="moneyColumn"
    private TableColumn<TableData, String> moneyColumn; // Value injected by FXMLLoader

    public TableView<TableData> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<TableData> tableView) {
        this.tableView = tableView;
    }

    @FXML // fx:id="tableView"
    private TableView<TableData> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="typeColumn"
    private TableColumn<TableData, String> typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memoColumn"
    private TableColumn<TableData, String> memoColumn; // Value injected by FXMLLoader

    @FXML // fx:id="balanceTextField"
    private TextField balanceTextField; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumn"
    private TableColumn<TableData, String> dateColumn; // Value injected by FXMLLoader

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
        initialize();
    }

    @FXML // ”删除“菜单项的事件监听器
    public void deleteMenuItemEvent(ActionEvent actionEvent) {
        do_deleteMenuItem_Event();
        initialize();
    }

    @FXML // ”修改“菜单项的事件监听器
    public void alterMenuItemEvent(ActionEvent actionEvent) {
        do_alterMenuItem_Event();
        initialize();
    }

    @FXML // “删除”右键菜单的事件监听器
    public void deleteContextMenuEvent(ActionEvent event) {
        do_deleteContextMenu_Event();
        initialize();
    }

    @FXML // “添加”右键菜单的事件监听器
    public void addContextMenuEvent(ActionEvent event) {
        do_addContextMenu_Event();
        initialize();
    }

    @FXML // “修改”右键菜单的事件监听器
    public void alterContextMenuEvent(ActionEvent event) {
        do_alterContextMenu_Event();
        initialize();
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

    @FXML // ”添加分类“菜单项的事件监听器
    public void addClassificationMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initAddClassificationFrame();
    }

    @FXML // “用户信息”菜单项的事件监听器
    public void userInfoMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initUserInformationFrame();
    }

    @FXML // “报告”菜单项的事件监听器
    public void reportMenuItemEvent(ActionEvent event) {
        mainApp.initReportFrame();
    }

    @FXML // “日志”菜单项的事件监听器
    public void diaryMenuItemEvent(ActionEvent event) {
        simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "该功能暂未实现！");
    }

    @FXML // “关于软件”菜单项的事件监听器
    public void abutSoftMenuItemEvent(ActionEvent actionEvent) {
        mainApp.initSoftInformationFrame();
    }

    @FXML // “帮助”菜单项的事件监听器
    public void helpMenuItemEvent(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/lck100/JavaExerciseProject/tree/master/1.%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F/%E7%AE%A1%E5%AE%B6%E5%A9%86%E7%B3%BB%E7%BB%9F%EF%BC%88JavaFX%E7%89%88%EF%BC%89"));
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
    public void initialize() {
        new RecordsDao().refreshPrimaryKey();// 刷新主键编号
        initFrame();// 初始化用户记录
        initAddDataToTableView();// 初始化表格数据
        initThemeRadioMenuItem();// 初始化主题菜单项单选框被选中
    }

    /**
     * 操作结果：“导入”按钮的事件方法
     */
    public void do_importMenuItem_Event() {
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
                records.setRecordDate(content[i][5]);
                //添加数据到数据库
                isSuccess = new RecordsDao().addAccountRecords(session, records);
            }
            if (isSuccess) {
                simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "导入excel数据成功");
                initialize();// 刷新数据
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
    public void do_exportMenuItem_Event() {
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
    public void do_backupMenuItem_Event() {
        String savePath = null;
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置打开文件选择框默认输入的文件名
        fileChooser.setInitialFileName("Database_Backup_" + dateTools.dateFormat(new Date(), "yyyy-MM-dd") + ".sql");
        //打开文件选择框
        File result = fileChooser.showSaveDialog(null);
        savePath = result.getAbsolutePath();
        boolean b = false;
        try {
            b = jdbcUtils.backup("root", "admin", savePath, "db_accountSystem");
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
    public void do_recoverMenuItem_Event() {
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
            b = jdbcUtils.recover("root", "admin", "db_accountSystem", recoverPath);
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
    public void do_deleteContextMenu_Event() {
        publicTools.public_do_deleteContextMenu_Event(tableView);
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”添加“右键菜单的事件方法
     */
    public void do_addContextMenu_Event() {
        publicTools.public_do_addContextMenu_Event(tableView);
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”修改“右键菜单的事件方法
     */
    public void do_alterContextMenu_Event() {
        publicTools.public_do_alterContextMenu_Event(tableView);
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”添加“菜单项的事件方法
     */
    public void do_addMenuItem_Event() {
        mainApp.initAddFrame();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”删除“菜单项的事件方法
     */
    public void do_deleteMenuItem_Event() {
        mainApp.initDeleteFrame();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”修改“菜单项的事件方法
     */
    public void do_alterMenuItem_Event() {
        mainApp.initAlterFrame();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”查询“菜单项的事件方法
     */
    public void do_checkMenuItem_Event() {
        mainApp.initTableView();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”按日期查询“菜单项的事件方法
     */
    public void do_dateCheckMenuItem_Event() {
        mainApp.initDateCheckTableView();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”按分类查询“菜单项的事件方法
     */
    public void do_classificationCheckMenuItem_Event() {
        mainApp.initClassificationTableView();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：”按备注查询“菜单项的事件方法
     */
    public void do_memoCheckMenuItem_Event() {
        mainApp.initMemoTableView();
        initialize();// 刷新数据
    }

    /**
     * 操作结果：初始化用户名、总支出、总收入及余额
     */
    public void initFrame() {
        // 初始化值用户名，总收入，总支出及余额
        Session session = new Session();
        Users users = session.getUsers();
        UsersDao usersDao = new UsersDao();
        // 获取结果值
        RecordsDao recordsDao = new RecordsDao();
        String outputSql = "select SUM(rMoney) from tb_records where rType='支出' and uId=" + users.getUserId() + ";";
        String inputSql = "select SUM(rMoney) from tb_records where rType='收入' and uId=" + users.getUserId() + ";";
        float totalOutput = recordsDao.getAccountTotal(outputSql);
        float totalInput = recordsDao.getAccountTotal(inputSql);
        float balance = totalInput - totalOutput;

        String sql = "select * from tb_users where uId=" + users.getUserId();
        List list = (List) usersDao.getUsersDataBySql(sql);
        Users u = null;
        for (int i = 0; i < list.size(); i++) {
            u = (Users) list.get(i);
        }
        userImage.setImage(new Image("file:" + u.getUserImagePath()));
        userImage.setSmooth(true);
        userImage.setFitWidth(100);
        userImage.setFitHeight(100);
        userImage.setCache(true);
        userImage.setPreserveRatio(true);
        totalOutputTextField.setText(String.valueOf(totalOutput));
        totalInputTextField.setText(String.valueOf(totalInput));
        balanceTextField.setText(String.valueOf(balance));
        userNameLabel.setText(new Session().getUsers().getUserName());
    }

    /**
     * 操作结果：初始化数据表视图
     */
    public void initAddDataToTableView() {
        String sql = "select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where uId=" + new Session().getUsers().getUserId() + ";";
        publicTools.public_initTableViewData(tableView
                , publicTools.public_getTableViewData(sql)
                , idColumn
                , typeColumn
                , moneyColumn
                , classificationColumn
                , memoColumn
                , dateColumn);
    }

    /**
     * 操作结果：初始主题菜单项单选框被选中情况
     */
    public void initThemeRadioMenuItem() {
        String theme = publicTools.readStyleData();
        if (theme.equals("view/BlackStyle.css")) {
            blackRadioMenuItem.setSelected(true);
        } else if (theme.equals("view/WhiteStyle.css")) {
            whiteRadioMenuItem.setSelected(true);
        } else {
            defaultRadioMenuItem.setSelected(true);
        }
    }

    // “默认”菜单项的事件监听器方法
    public void defaultRadioMenuItemEvent(ActionEvent event) {
        Map<String, String> map = new HashMap<>();
        map.put(defaultRadioMenuItem.getText(), "");
        simpleTools.dataWriteProperties("E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\properties\\properties.properties", map);
        simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "请重启软件！");
    }

    // “经典黑”菜单项的事件监听器方法
    public void blackRadioMenuItemEvent(ActionEvent event) {
        Map<String, String> map = new HashMap<>();
        map.put(blackRadioMenuItem.getText(), "view/BlackStyle.css");
        simpleTools.dataWriteProperties("E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\properties\\properties.properties", map);
        simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "请重启软件！");
    }

    // “优雅白”菜单项的事件监听器方法
    public void whiteRadioMenuItemEvent(ActionEvent event) {
        Map<String, String> map = new HashMap<>();
        map.put(whiteRadioMenuItem.getText(), "view/WhiteStyle.css");
        simpleTools.dataWriteProperties("E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\properties\\properties.properties", map);
        simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "请重启软件！");
    }

}

