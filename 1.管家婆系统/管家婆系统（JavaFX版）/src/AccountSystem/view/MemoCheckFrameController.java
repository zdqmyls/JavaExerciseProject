package AccountSystem.view;

import AccountSystem.bean.Session;
import AccountSystem.bean.TableData;
import AccountSystem.tools.PublicTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MemoCheckFrameController {
    private PublicTools publicTools = new PublicTools();

    @FXML // fx:id="memo_classificationColumn"
    private TableColumn<TableData, String> memo_classificationColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_moneyColumn"
    private TableColumn<TableData, String> memo_moneyColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_dateColumn"
    private TableColumn<TableData, String> memo_dateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_memoTextField"
    private TextField memo_memoTextField; // Value injected by FXMLLoader

    @FXML // fx:id="memo_tableView"
    private TableView<TableData> memo_tableView; // Value injected by FXMLLoader

    @FXML // fx:id="memo_idColumn"
    private TableColumn<TableData, String> memo_idColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_typeColumn"
    private TableColumn<TableData, String> memo_typeColumn; // Value injected by FXMLLoader

    @FXML // fx:id="memo_memoColumn"
    private TableColumn<TableData, String> memo_memoColumn; // Value injected by FXMLLoader

    @FXML // ”查询“按钮的事件监听方法
    public void memo_checkButtonEvent(ActionEvent event) {
        String memo = memo_memoTextField.getText();
        String sql = "select rId,rType,rMoney,rClassification,rMemo,rDate from tb_records where rMemo like '%" + memo + "%' and uId=" + new Session().getUsers().getUserId() + ";";
        publicTools.public_initTableViewData(memo_tableView
                , publicTools.public_getTableViewData(sql)
                , memo_idColumn
                , memo_typeColumn
                , memo_moneyColumn
                , memo_classificationColumn
                , memo_memoColumn
                , memo_dateColumn);
    }

    // ”删除“右键菜单的事件方法
    public void deleteContextMenuEvent(ActionEvent event) {
        publicTools.public_do_deleteContextMenu_Event(memo_tableView);
    }

    // ”添加“右键菜单的事件方法
    public void addContextMenuEvent(ActionEvent event) {
        publicTools.public_do_addContextMenu_Event(memo_tableView);
    }

    // ”修改“右键菜单的事件方法
    public void alterContextMenuEvent(ActionEvent event) {
        publicTools.public_do_alterContextMenu_Event(memo_tableView);
    }

}
