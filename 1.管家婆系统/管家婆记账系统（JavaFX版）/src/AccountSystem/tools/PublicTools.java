package AccountSystem.tools;

import AccountSystem.MainApp;
import AccountSystem.bean.Classification;
import AccountSystem.bean.Records;
import AccountSystem.bean.TableData;
import AccountSystem.bean.Users;
import AccountSystem.dao.ClassificationDao;
import AccountSystem.dao.RecordsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class PublicTools {

    /**
     * 操作结果：”删除“右键菜单的事件方法
     */
    public void public_do_deleteContextMenu_Event(TableView<TableData> tableView) {
        RecordsDao recordsDao = new RecordsDao();
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();// 获取所选择行的索引，从0开始
        TableData td = tableView.getSelectionModel().getSelectedItem();
        Records records = new Records();
        if (selectedIndex >= 0) {
            tableView.getItems().remove(selectedIndex);
            records.setRecordId(Integer.parseInt(td.getId()));
            boolean b = recordsDao.deleteAccountItem(records);
        }
    }

    /**
     * 操作结果：”添加“右键菜单的事件方法
     */
    public void public_do_addContextMenu_Event(TableView<TableData> tableView) {
        new MainApp().initAddFrame();
    }

    /**
     * 操作结果：”修改“右键菜单的事件方法
     */
    public void public_do_alterContextMenu_Event(TableView<TableData> tableView) {
        TableData td = tableView.getSelectionModel().getSelectedItem();
        boolean isContextMenu = true;
        new MainApp().initAlterFrame(td, isContextMenu);
    }

    /**
     * 操作结果：读取配置文件信息
     *
     * @return
     */
    public String readStyleData() {
        String s = null;
        /*--打包JAR包路径--*/
        String propPath=new SimpleTools().getJARPath();
        List list = new SimpleTools().readPropertiesFile(propPath+"/properties/properties.properties");
//        List list = new SimpleTools().readPropertiesFile("src/AccountSystem/properties/properties.properties");
        for (int i = 0; i < list.size(); i++) {
            s = (String) list.get(i);
        }
        return s;
    }

    /**
     * 操作结果：向下拉列表框中添加列表项
     *
     * @param comboBox 下拉列表框
     * @param items    列表项
     */
    public void public_addComboBoxItems(ComboBox comboBox, Object[] items) {
        comboBox.getItems().clear();// 清除下列列表框中的所有选项
        ObservableList options = FXCollections.observableArrayList(items);
        comboBox.setItems(options);// 添加下拉列表项
    }

    /**
     * 操作结果：得到收入分类从数据库中读取数据
     *
     * @return String[] 收入分类
     */
    public String[] public_getInputClassification() {
        String inputSql = "select * from tb_classification where cType='收入';";
        List inputList = new ClassificationDao().getTableData(inputSql);
        List<String> list = new ArrayList();
        for (int i = 0; i < inputList.size(); i++) {
            Classification classification = (Classification) inputList.get(i);
            list.add(classification.getcName());
        }
        String[] strings = new String[list.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = list.get(i);
        }
        return strings;
    }

    /**
     * 操作结果：得到支出分类从数据库中读取数据
     *
     * @return String[] 支出分类
     */
    public String[] public_getOutputClassification() {

        String outputSql = "select * from tb_classification where cType='支出';";
        List outputList = new ClassificationDao().getTableData(outputSql);
        List<String> list = new ArrayList();
        for (int i = 0; i < outputList.size(); i++) {
            Classification classification = (Classification) outputList.get(i);
            list.add(classification.getcName());
        }
        String[] strings = new String[list.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = list.get(i);
        }
        return strings;
    }

    /**
     * 操作结果：根据用户计算余额
     *
     * @param users 用户对象
     * @return float 余额
     */
    public float public_getBalanceByUsers(Users users) {
        RecordsDao recordsDao = new RecordsDao();
        String outputSql = "select SUM(rMoney) from tb_records where rType='支出' and uId=" + users.getUserId() + ";";
        String inputSql = "select SUM(rMoney) from tb_records where rType='收入' and uId=" + users.getUserId() + ";";
        float totalOutput = recordsDao.getAccountTotal(outputSql);
        float totalInput = recordsDao.getAccountTotal(inputSql);
        float balance = totalInput - totalOutput;
        return balance;
    }

    /**
     * 操作结果：通过SQL语句从数据库得到表格视图的数据
     *
     * @param sql SQL语句
     * @return ObservableList
     */
    public ObservableList<TableData> public_getTableViewData(String sql) {
        RecordsDao recordsDao = new RecordsDao();
        List list = recordsDao.getRecordsDataBySql(sql);
        ObservableList<TableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Records r = (Records) list.get(i);
            TableData td = new TableData(String.valueOf(r.getRecordId()), r.getRecordType(),
                    String.valueOf(r.getRecordMoney()), r.getRecordClassification(), r.getRecordMemo(),
                    r.getRecordDate());
            data.add(td);
        }
        return data;
    }

    /**
     * 操作结果：设置表格视图的数据
     *
     * @param tableView            表格视图
     * @param data                 数据
     * @param idColumn             编号列
     * @param typeColumn           类型列
     * @param moneyColumn          金额列
     * @param classificationColumn 分类列
     * @param memoColumn           备注列
     * @param dateColumn           日期列
     */
    public void public_initTableViewData(TableView tableView,
                                         ObservableList<TableData> data,
                                         TableColumn<TableData, String> idColumn,
                                         TableColumn<TableData, String> typeColumn,
                                         TableColumn<TableData, String> moneyColumn,
                                         TableColumn<TableData, String> classificationColumn,
                                         TableColumn<TableData, String> memoColumn,
                                         TableColumn<TableData, String> dateColumn) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        memoColumn.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        tableView.setItems(data);
    }
}
