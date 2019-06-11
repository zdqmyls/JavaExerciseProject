package AccountSystem.view;

import AccountSystem.bean.Property;
import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.tools.PublicTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class classificationCheckFrameController {
    private PublicTools publicTools = new PublicTools();

    @FXML // fx:id="classification_tableView"
    private TableView<TableData> classification_tableView; // Value injected by FXMLLoader

    @FXML // fx:id="classification_idColumn"
    private TableColumn<TableData, String> classification_idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="classification_memoColumn2"
    private TableColumn<TableData, String> classification_memoColumn2; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumndateColumn1classification_dateColumn2"
    private TableColumn<TableData, String> classification_dateColumn2; // Value injected by FXMLLoader

    @FXML // fx:id="outputClassificationComboBox"
    private ComboBox<?> outputClassificationComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="classification_typeColumn2"
    private TableColumn<TableData, String> classification_typeColumn2; // Value injected by FXMLLoader

    @FXML // fx:id="classification_tableView2"
    private TableView<TableData> classification_tableView2; // Value injected by FXMLLoader

    @FXML // fx:id="classification_idColumn2"
    private TableColumn<TableData, String> classification_idColumn2; // Value injected by FXMLLoader

    @FXML // fx:id="classification_typeColumn"
    private TableColumn<TableData, String> classification_typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="classification_classificationColumn"
    private TableColumn<TableData, String> classification_classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="classification_memoColumn"
    private TableColumn<TableData, String> classification_memoColumn; // Value injected by FXMLLoader

    @FXML // fx:id="inputClassificationComboBox"
    private ComboBox<?> inputClassificationComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="classification_moneyColumn2"
    private TableColumn<TableData, String> classification_moneyColumn2; // Value injected by FXMLLoader

    @FXML // fx:id="classification_classificationColumn2"
    private TableColumn<TableData, String> classification_classificationColumn2; // Value injected by FXMLLoader

    @FXML // fx:id="classification_moneyColumn"
    private TableColumn<TableData, String> classification_moneyColumn; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumn1classification_dateColumn"
    private TableColumn<TableData, String> classification_dateColumn; // Value injected by FXMLLoader

    // ”删除“右键菜单的事件方法
    public void deleteContextMenuEvent(ActionEvent event) {
        publicTools.public_do_deleteContextMenu_Event(classification_tableView);
    }

    // ”添加“右键菜单的事件方法
    public void addContextMenuEvent(ActionEvent event) {
        publicTools.public_do_addContextMenu_Event(classification_tableView);
    }

    // ”修改“右键菜单的事件方法
    public void alterContextMenuEvent(ActionEvent event) {
        publicTools.public_do_alterContextMenu_Event(classification_tableView);
    }

    // ”支出“选项卡中的下拉菜单的事件方法
    public void outputClassificationComboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) outputClassificationComboBox.getSelectionModel().selectedItemProperty().getValue();
        String sql = "select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rClassification='" + selectedCoboboxItem + "' and uId=" + new Session().getUsers().getUserId() + ";";
        publicTools.public_initTableViewData(classification_tableView
                , publicTools.public_getTableViewData(sql)
                , classification_idColumn
                , classification_typeColumn
                , classification_moneyColumn
                , classification_classificationColumn
                , classification_memoColumn
                , classification_dateColumn);
    }

    // ”收入“选项卡中的下拉菜单的事件方法
    public void inputClassificationComboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) inputClassificationComboBox.getSelectionModel().selectedItemProperty().getValue();
        String sql = "select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rClassification='" + selectedCoboboxItem + "' and uId=" + new Session().getUsers().getUserId() + ";";
        publicTools.public_initTableViewData(classification_tableView2
                , publicTools.public_getTableViewData(sql)
                , classification_idColumn2
                , classification_typeColumn2
                , classification_moneyColumn2
                , classification_classificationColumn2
                , classification_memoColumn2
                , classification_dateColumn2);
    }

    @FXML // 初始化界面
    public void initialize() throws IOException {
        publicTools.public_addComboBoxItems(inputClassificationComboBox, new Property().getInputClassificationList().toArray());
        publicTools.public_addComboBoxItems(outputClassificationComboBox, new Property().getOutputClassificationList().toArray());
    }

}
