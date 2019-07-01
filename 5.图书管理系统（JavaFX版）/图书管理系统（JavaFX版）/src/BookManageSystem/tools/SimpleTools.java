package BookManageSystem.tools;

import BookManageSystem.beans.BookBean;
import BookManageSystem.beans.BookBeanTableData;
import BookManageSystem.beans.BookTypeBean;
import BookManageSystem.beans.BookTypeBeanTableData;
import BookManageSystem.dao.BookDao;
import BookManageSystem.dao.BookTypeDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

public class SimpleTools {

    /**
     * 操作结果：JavaFX设置按钮、标签等组件的图标
     * @param labeleds 需要设置图标的按钮
     * @param imagePaths 图标的路径
     */
    public void setLabeledImage(Labeled[] labeleds,String[] imagePaths){
        for(int i=0;i<labeleds.length;i++){
            labeleds[i].setGraphic(new ImageView(new Image("file:"+imagePaths[i])));
        }
    }

    /**
     * 操作结果：清空文本框组件的内容
     * @param inputControls 文本框或文本域组等
     */
    public void clearTextField(TextInputControl...inputControls){
        for(int i=0;i<inputControls.length;i++){
            inputControls[i].setText("");
        }
    }

    /**
     * 操作结果：取消所有单选按钮选择
     * @param toggleButtons 单选按钮组
     */
    public void clearSelectedRadioButton(ToggleButton...toggleButtons){
        for(int i=0;i<toggleButtons.length;i++){
            toggleButtons[i].setSelected(false);
        }
    }

    /**
     * 操作结果：取消所有下拉列表框选择
     * @param comboBoxes 下拉列表框组
     */
    public void clearSelectedComboBox(ComboBox...comboBoxes){
        for(int i=0;i<comboBoxes.length;i++){
            comboBoxes[i].getSelectionModel().select(-1);// 设置选择的索引为-1，就不会选择任何选择选项了。
        }
    }

    /**
     * 操作结果：JavaFX设置菜单项组件的图标
     * @param menuItems 菜单项
     * @param imagePaths 图标的路径
     */
    public void setMenuItemImage(MenuItem[] menuItems,String[] imagePaths){
        for(int i=0;i<menuItems.length;i++){
            menuItems[i].setGraphic(new ImageView(new Image("file:"+imagePaths[i])));
        }
    }

    /**
     * 操作结果：JavaFX判断是否为空
     * @param str 文本
     * @return boolean 如果不为空返回true，否则返回false
     */
    public boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 操作结果：自定义一个JavaFX的对话框
     *
     * @param alterType 对话框类型
     * @param title     对话框标题
     * @param header    对话框头信息
     * @param message   对话框显示内容
     * @return boolean 如果点击了”确定“那么就返回true，否则返回false
     */
    public boolean informationDialog(Alert.AlertType alterType, String title, String header, String message) {
        // 按钮部分可以使用预设的也可以像这样自己 new 一个
        Alert alert = new Alert(alterType, message, new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE), new ButtonType("确定", ButtonBar.ButtonData.YES));
        // 设置对话框的标题
        alert.setTitle(title);
        alert.setHeaderText(header);
        // showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> buttonType = alert.showAndWait();
        // 根据点击结果返回
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            return true;// 如果点击了“确定”就返回true
        } else {
            return false;
        }
    }

    /**
     * 操作结果：JavaFX判断是否登录成功
     * @param userNameTextField 用户名文本框
     * @param passwordTextField 密码文本框
     * @param userName 正确用户名
     * @param password 正确密码
     * @return boolean 如果登录成功返回true，否则返回false
     */
    public boolean isLogIn(TextInputControl userNameTextField,TextInputControl passwordTextField,String userName,String password){
        SimpleTools simpleTools=new SimpleTools();
        if(simpleTools.isEmpty(userNameTextField.getText())){
            simpleTools.informationDialog(Alert.AlertType.WARNING,"提示","警告","用户名不能为空！");
            return false;
        }
        if(simpleTools.isEmpty(passwordTextField.getText())){
            simpleTools.informationDialog(Alert.AlertType.WARNING,"提示","警告","密码不能为空！");
            return false;
        }
        if(!userNameTextField.getText().equals(userName)){
            simpleTools.informationDialog(Alert.AlertType.WARNING,"提示","警告","用户名不正确！");
            return false;
        }
        if(!passwordTextField.getText().equals(password)){
            simpleTools.informationDialog(Alert.AlertType.WARNING,"提示","警告","密码不正确！");
            return false;
        }
        if(!userNameTextField.getText().equals(userName)&&!passwordTextField.getText().equals(password)){
            simpleTools.informationDialog(Alert.AlertType.ERROR,"提示","错误","用户名和密码均不正确！");
            return false;
        }
        if(userNameTextField.getText().equals(userName)&&passwordTextField.getText().equals(password)){
            boolean isOK=simpleTools.informationDialog(Alert.AlertType.INFORMATION,"提示","信息","登录成功！");
            return isOK;
        }
        return false;
    }

    /**
     * 操作结果：向下拉列表框中添加列表项
     *
     * @param comboBox 下拉列表框
     * @param items    列表项
     */
    public void addComboBoxItems(ComboBox comboBox, Object[] items) {
        comboBox.getItems().clear();// 清除下列列表框中的所有选项
        ObservableList options = FXCollections.observableArrayList(items);
        comboBox.setItems(options);// 添加下拉列表项
    }

    /**
     * 操作结果：获取JAR包所在的路径
     *
     * @return String JAR包所在的路径
     */
    public String getJARPath() {
        /**
         * 方法二：获取当前可执行jar包所在目录
         */
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        String filePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码，支持中文
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 获取jar包所在目录
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }

        File file = new File(filePath);
        filePath = file.getAbsolutePath();//得到windows下的正确路径
        return filePath;
    }

    public void setBookTypeTableViewData(TableView tableView, ObservableList data, TableColumn<BookTypeBeanTableData,String> idColumn, TableColumn<BookTypeBeanTableData,String> nameColumn, TableColumn<BookTypeBeanTableData,String> descriptionColumn){
        idColumn.setCellValueFactory(cellData->cellData.getValue().bookTypeIdProperty());
        nameColumn.setCellValueFactory(cellData->cellData.getValue().bookTypeNameProperty());
        descriptionColumn.setCellValueFactory(cellData->cellData.getValue().bookTypeDesciptionProperty());
        tableView.setItems(data);
    }

    public void setBookTableViewData(TableView tableView, ObservableList data, TableColumn<BookBeanTableData,String> idColumn,TableColumn<BookBeanTableData,String> nameColumn,TableColumn<BookBeanTableData,String> authorColumn,TableColumn<BookBeanTableData,String> sexColumn,TableColumn<BookBeanTableData,String> priceColumn,TableColumn<BookBeanTableData,String> descriptionColumn,TableColumn<BookBeanTableData,String> typeColumn){
        idColumn.setCellValueFactory(cellData->cellData.getValue().bookIdProperty());
        nameColumn.setCellValueFactory(cellData->cellData.getValue().bookNameProperty());
        authorColumn.setCellValueFactory(cellData->cellData.getValue().bookAuthorProperty());
        sexColumn.setCellValueFactory(cellData->cellData.getValue().bookAuthorSexProperty());
        priceColumn.setCellValueFactory(cellData->cellData.getValue().bookPriceProperty());
        descriptionColumn.setCellValueFactory(cellData->cellData.getValue().bookDescriptionProperty());
        typeColumn.setCellValueFactory(cellData->cellData.getValue().bookTypeProperty());
        tableView.setItems(data);
    }

    public ObservableList<BookTypeBeanTableData> getBookTypeTableViewData(String sql) {
        BookTypeDao bookTypeDao=new BookTypeDao();
        List list = bookTypeDao.getRecordsDataBySql(sql);
        ObservableList<BookTypeBeanTableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            BookTypeBean r = (BookTypeBean) list.get(i);
            BookTypeBeanTableData td = new BookTypeBeanTableData(String.valueOf(r.getBookTypeId()),r.getBookTypeName(),r.getBookTypeDescription());
            data.add(td);
        }
        return data;
    }

    public ObservableList<BookBeanTableData> getBookTableViewData(String sql){
        BookDao bookDao=new BookDao();
        List list = bookDao.getRecordsDataBySql(sql);
        ObservableList<BookBeanTableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            BookBean r = (BookBean) list.get(i);
            BookBeanTableData td = new BookBeanTableData(String.valueOf(r.getBookId()),r.getBookName(),r.getBookAuthor(),r.getBookAuthorSex(),String.valueOf(r.getBookPrice()),r.getBookDescription(),String.valueOf(r.getBookTypeId()));
            data.add(td);
        }
        return data;
    }
}
