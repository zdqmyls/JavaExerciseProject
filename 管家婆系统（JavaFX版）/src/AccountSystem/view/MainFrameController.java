/**
 * Sample Skeleton for 'mainFrame.fxml' Controller Class
 */

package AccountSystem.view;

import AccountSystem.MainApp;

import AccountSystem.bean.Records;
import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.dao.RecordsDao;
import AccountSystem.tools.SimpleTools;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.util.Date;
import java.util.List;

public class MainFrameController {

    @FXML
    private Button backupButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button checkButton;

    @FXML
    private TextField totalOutputTextField;

    @FXML
    private Label totalInputLabel;

    @FXML
    private Label totalOutputLabel;

    @FXML
    private TextField totalInputTextField;

    @FXML
    private Tab fileTabPane;

    @FXML
    private Tab editTabPane;

    @FXML
    private AnchorPane leftSplitPane;

    @FXML
    private Label userNameLabel;

    @FXML
    private Button conditionButton;

    @FXML
    private Button alterButton;

    @FXML
    private MenuItem deleteContextMenu1;

    @FXML
    private Button themeButton;

    @FXML
    private TabPane TabbedPane;

    @FXML
    private Button addButton;

    @FXML
    private MenuItem addContextMenu;

    @FXML
    private Button userButton;

    @FXML
    private MenuItem deleteContextMenu;

    @FXML
    private Tab checkTabPane;

    @FXML
    private Tab optionTabPane;

    @FXML
    private Button barChartButton;

    @FXML
    private Button inputClassificationButton;

    @FXML
    private MenuItem addContextMenu1;

    @FXML
    private Button recoverButton;

    @FXML
    private AnchorPane rightSplitPane;

    @FXML
    private ImageView userImage;

    @FXML
    private Button importButton;

    @FXML
    private Button pieChartButton;
    @FXML
    private MenuItem alterContextMenu;

    @FXML
    private Label balanceLabel;

    @FXML
    private Button exportButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField balanceTextField;

    @FXML
    private Button lineChartButton;

    @FXML
    private Button outputClassificationButton;

    @FXML
    private Button alterPasswordButton;

    @FXML
    private MenuItem alterContextMenu1;


    @FXML
    private Button initButton;

    @FXML
    private Button showButton;

    @FXML
    private SimpleTools simpleTools = new SimpleTools();

    @FXML
    private RecordsDao recordsDao = new RecordsDao();

    @FXML
    private MainApp mainApp = new MainApp();

    @FXML
    private Stage mainFrameStage;

    @FXML // fx:id="leftSplitContentPane"
    private Pane leftSplitContentPane; // Value injected by FXMLLoader

    @FXML
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void setMainFrameStage(Stage mainFrameStage) {
        this.mainFrameStage = mainFrameStage;
    }

    @FXML
    public void editTabPaneSelectedEvent(Event event) {
    }

    @FXML
    // “添加”按钮的事件监听器
    public void addButtonEvent(ActionEvent actionEvent) {
        mainApp.initAddFrame();
    }

    @FXML
    // ”删除“按钮的事件监听器
    public void deleteButtonEvent(ActionEvent actionEvent) {
        mainApp.initDeleteFrame();
    }

    @FXML
    // ”修改“按钮的事件监听器
    public void alterButtonEvent(ActionEvent actionEvent) {
        mainApp.initAlterFrame();
    }

    @FXML
    public void checkTabPaneSelectedEvent(Event event) {
    }

    @FXML
    public void barChartButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void lineChartButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void pieChartButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    // ”查询“按钮的事件监听器
    public void checkButtonEvent(ActionEvent actionEvent) {
        addDataToTableView();
    }

    @FXML
    public void optionTabPaneSelectedEvent(Event event) {
    }

    @FXML
    public void conditionButtonEvent(ActionEvent actionEvent) {
        mainApp.initConditionCheckFrame();
    }

    @FXML
    public void outputClassificationButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void inputClassificationButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void alterPasswordButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void userButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void themeButtonEvent(ActionEvent actionEvent) {
    }

    @FXML
    public void fileTabPaneSelectedEvent(Event event) {
    }

    @FXML
    // “导入”按钮的事件监听器
    public void importButtonEvent(ActionEvent actionEvent) {
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

    @FXML
    // “导出”按钮的事件监听器
    public void exportButtonEvent(ActionEvent actionEvent) {
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

    @FXML
    // “备份”按钮的事件监听器
    public void backupButtonEvent(ActionEvent actionEvent) {
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

    @FXML
    // “恢复”按钮的事件监听器
    public void recoverButtonEvent(ActionEvent actionEvent) {
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

    @FXML
    // “退出”按钮的事件监听器
    public void exitButtonEvent(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    // 初始化用户名、总支出、总收入及余额
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

    @FXML
    private TableView<TableData> table;
    @FXML
    private TableColumn<TableData, String> idColumn;
    @FXML
    private TableColumn<TableData, String> typeColumn;
    @FXML
    private TableColumn<TableData, String> moneyColumn;
    @FXML
    private TableColumn<TableData, String> classificationColumn;
    @FXML
    private TableColumn<TableData, String> memoColumn;
    @FXML
    private TableColumn<TableData, String> dateColumn;

    @FXML
    // 初始化数据
    private void initialize() {

        // 初始化用户的记录
        initFrame();

        // 向数据表视图中添加数据
        addDataToTableView2();

        // 设置单元格可编辑
        setTableCellEdited();

    }

    /**
     * 操作结果：添加数据库数据到数据表视图
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

        table.setItems(data);
        // 向表格中添加列
//        table.getColumns().addAll(idColumn, typeColumn, moneyColumn, classificationColumn, memoColumn, dateColumn);
    }

    @FXML
    private TableView<TableData> table2;
    @FXML
    private TableColumn<TableData, String> idColumn2;
    @FXML
    private TableColumn<TableData, String> typeColumn2;
    @FXML
    private TableColumn<TableData, String> moneyColumn2;
    @FXML
    private TableColumn<TableData, String> classificationColumn2;
    @FXML
    private TableColumn<TableData, String> memoColumn2;
    @FXML
    private TableColumn<TableData, String> dateColumn2;

    /**
     * 操作结果：添加数据库数据到数据表视图
     */
    public void addDataToTableView2() {
        idColumn2.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeColumn2.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        moneyColumn2.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        classificationColumn2.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        memoColumn2.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        dateColumn2.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        List li = recordsDao.getAllTableDataByUser(new Session());
        ObservableList<TableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < li.size(); i++) {
            Records r = (Records) li.get(i);
            TableData td = new TableData(String.valueOf(r.getRecordId()), r.getRecordType(), String.valueOf(r.getRecordMoney()), r.getRecordClassification(), r.getRecordMemo(), r.getRecordDate());
            data.add(td);
//            System.out.println("id="+r.getRecordId()+"\t\ttype="+r.getRecordType()+"\t\tmoney"+r.getRecordMoney()+"\t\tclassification="+r.getRecordClassification()+"\t\tmemo="+r.getRecordMemo()+"\t\tdate="+r.getRecordDate());
        }

        table2.setItems(data);
        // 向表格中添加列
//        table.getColumns().addAll(idColumn, typeColumn, moneyColumn, classificationColumn, memoColumn, dateColumn);
    }

    /**
     * 操作结果：设置tableView的单元格可编辑
     */
    public void setTableCellEdited(){
        //表格设置为可编辑
        table.setEditable(true);
        //给需要编辑的列设置属性
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        moneyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        classificationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        memoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    // 弹出菜单”删除“的事件监听器
    public void deleteContextMenuEvent(ActionEvent actionEvent) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        TableData td=table.getSelectionModel().getSelectedItem();
        Records records = new Records();
        if (selectedIndex >= 0) {
            table.getItems().remove(selectedIndex);
            records.setRecordId(Integer.parseInt(td.getId()));
            boolean b = recordsDao.deleteAccountItem(records);
        }
    }

    // 弹出菜单”更改“的事件监听器
    public void alterContextMenuEvent(ActionEvent actionEvent) {
        TableData td=table.getSelectionModel().getSelectedItem();
        Records records = new Records();
        records.setRecordId(Integer.parseInt(td.getId()));
        records.setRecordType(td.getType());
        records.setRecordMoney(Float.parseFloat(td.getMoney()));
        records.setRecordClassification(td.getClassification());
        records.setRecordMemo(td.getMemo());
        records.setRecordDate(td.getDate());
//        System.out.println(td.getId()+"\t\ttype="+td.getType()+"\t\tmoney="+td.getMoney()+"\t\tclassification="+td.getClassification()+"\t\tmemo="+td.getMemo()+"\t\tdate="+td.getDate());
//        TablePosition<TableData, ?> cell=table.getEditingCell();
//        TableData s= cell.getTableView().getSelectionModel().getSelectedItem();
        mainApp.initEditFrame();
        // 初始化编辑界面的值
        new EditAccountFrameController().setRecords(records);

    }

    // 弹出菜单”增加“的事件监听器
    public void addContextMenuEvent(ActionEvent actionEvent) {
        mainApp.initAddFrame();
    }

}
