package BookManageSystem.controller;

import BookManageSystem.beans.BookBeanTableData;
import BookManageSystem.beans.BookTypeBean;
import BookManageSystem.beans.BookTypeBeanTableData;
import BookManageSystem.dao.BookDao;
import BookManageSystem.dao.BookTypeDao;
import BookManageSystem.tools.SimpleTools;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class BookManageFrameController {

    private SimpleTools simpleTools = new SimpleTools();
    private BookDao bookDao = new BookDao();

    @FXML
    private TextField idTextField;

    @FXML
    private Button alterButton;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField bookAuthorTextField2;

    @FXML
    private ComboBox bookTypeComboBox2;

    @FXML
    private TableColumn<BookBeanTableData, String> idTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> authorSexTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookPriceTableColumn;

    @FXML
    private ComboBox<?> bookTypeComboBox;

    @FXML
    private Button checkButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button resetButton2;

    @FXML
    private TableColumn<BookBeanTableData, String> bookAuthorTableColumn;

    @FXML
    private TableView<BookBeanTableData> bookManageTableView;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private TextField bookAuthorTextField;

    @FXML
    private TableColumn<BookBeanTableData, String> bookNameTableColumn;

    @FXML
    private TableColumn<BookBeanTableData, String> bookDescriptionTableColumn;

    @FXML
    private TextField bookNameTextField2;

    @FXML
    private TextField priceTextField;

    @FXML
    private Button delteButton;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TableColumn<BookBeanTableData, String> bookTypeTableColumn;

    // 初始化
    public void initialize() {
//        simpleTools.setLabeledImage(new Labeled[]{alterButton, delteButton, resetButton2}, new String[]{"src/BookManageSystem/images/edit" +
//                ".png",
//                "src/BookManageSystem/images/delete.png", "src/BookManageSystem/images/reset.png"});
        /*--打包JAR包路径--*/
        String imagePath=simpleTools.getJARPath();
        simpleTools.setLabeledImage(new Labeled[]{alterButton,delteButton,resetButton2},new String[]{imagePath+
                "/images/edit.png",imagePath+"/images/delete.png",imagePath+"/images/reset.png"});
        idTextField.setDisable(true);
        String sql = "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where" +
                " " +
                "tb_book.btId=tb_booktype.btId;";
        simpleTools.setBookTableViewData(bookManageTableView
                , simpleTools.getBookTableViewData(sql)
                , idTableColumn
                , bookNameTableColumn
                , bookAuthorTableColumn
                , authorSexTableColumn
                , bookPriceTableColumn
                , bookDescriptionTableColumn
                , bookTypeTableColumn
        );

        String getBookTypeSQL = "select * from tb_booktype";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(getBookTypeSQL);
        String[] typeNames = new String[bookTypeList.size()];
        for (int i = 0; i < bookTypeList.size(); i++) {
            BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(i);
            typeNames[i] = bookTypeBean.getBookTypeName();
        }
        simpleTools.addComboBoxItems(bookTypeComboBox, typeNames);
        simpleTools.addComboBoxItems(bookTypeComboBox2, typeNames);

        bookManageTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    // 【查询】按钮的事件监听器
    public void do_checkButton_event(ActionEvent event) {
        String sql = "select bId,bBookName,bAuthor,bSex,bPrice,bBookDescription,btName from tb_book,tb_booktype where" +
                " tb_book.btId=tb_booktype.btId ";
        if (!simpleTools.isEmpty(bookNameTextField.getText())) {
            sql += " and bBookName like '%" + bookNameTextField.getText() + "%'";
        }
        if (!simpleTools.isEmpty(bookAuthorTextField.getText())) {
            sql += " and bAuthor like '%" + bookAuthorTextField.getText() + "%'";
        }
        String booktype = (String) bookTypeComboBox.getSelectionModel().selectedItemProperty().getValue();
        if (!simpleTools.isEmpty(booktype)) {
            sql += " and btName='" + booktype + "';";
        }
        simpleTools.setBookTableViewData(bookManageTableView
                , simpleTools.getBookTableViewData(sql)
                , idTableColumn
                , bookNameTableColumn
                , bookAuthorTableColumn
                , authorSexTableColumn
                , bookPriceTableColumn
                , bookDescriptionTableColumn
                , bookTypeTableColumn
        );
    }

    // 【修改】按钮的事件监听器
    public void do_alterButton_event(ActionEvent event) {
        String id = idTextField.getText();
        String bookName = bookNameTextField2.getText();
        String authorSex = "";
        if (maleRadioButton.isSelected()) {
            authorSex = maleRadioButton.getText();
        } else if (femaleRadioButton.isSelected()) {
            authorSex = femaleRadioButton.getText();
        }
        String price = priceTextField.getText();
        String bookAuthor = bookAuthorTextField2.getText();
        String bookType = (String) bookTypeComboBox2.getSelectionModel().selectedItemProperty().getValue();
        String description = bookDescriptionTextArea.getText();
        String bookTypeSQL = "select * from tb_booktype where btName='" + bookType + "';";
        List bookTypeList = new BookTypeDao().getRecordsDataBySql(bookTypeSQL);
        BookTypeBean bookTypeBean = (BookTypeBean) bookTypeList.get(0);
        int bookTypeId = bookTypeBean.getBookTypeId();
        String alterSQL =
                "update tb_book set bBookName='" + bookName + "',bAuthor='" + bookAuthor + "',bSex='" + authorSex +
                        "',bPrice=" + price + ",bBookDescription='" + description + "',btId=" + bookTypeId + " where " +
                        "bId=" + id + ";";
        boolean isOK = bookDao.dataChange(alterSQL);
        if (isOK) {
            initialize();
            do_resetButton2_event(null);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "修改失败！");
        }
    }

    // 【删除】按钮的事件监听器
    public void do_delteButton_event(ActionEvent event) {
        String id = idTextField.getText();
        String deleteSQL = "delete from tb_book where bId=" + id + ";";
        boolean isOK = bookDao.dataChange(deleteSQL);
        if (isOK) {
            initialize();
            do_resetButton2_event(null);
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "删除成功！");
        } else {
            simpleTools.informationDialog(Alert.AlertType.ERROR, "提示", "错误", "删除失败！");
        }
    }

    // 【重置】按钮的事件监听器
    public void do_resetButton2_event(ActionEvent event) {
        simpleTools.clearTextField(idTextField, bookNameTextField2, priceTextField, bookAuthorTextField2,
                bookDescriptionTextArea);
        simpleTools.clearSelectedRadioButton(femaleRadioButton, maleRadioButton);
        simpleTools.clearSelectedComboBox(bookTypeComboBox2);
    }

    // 【重置】按钮的事件监听器
    public void do_resetButton_event(ActionEvent event) {
        simpleTools.clearTextField(bookNameTextField, bookAuthorTextField);
        simpleTools.clearSelectedComboBox(bookTypeComboBox);
        initialize();
    }

    // 选中某行后行内容显示在下面文本框中
    public void showBookDetails(BookBeanTableData bookBeanTableData) {
        if (bookManageTableView.getSelectionModel().getSelectedIndex() < 0) {
            return;
        } else {
            idTextField.setText(bookBeanTableData.getBookId());
            bookNameTextField2.setText(bookBeanTableData.getBookName());
            if (bookBeanTableData.getBookAuthorSex().equals("男")) {
                maleRadioButton.setSelected(true);
            } else if (bookBeanTableData.getBookAuthorSex().equals("女")) {
                femaleRadioButton.setSelected(true);
            }
            priceTextField.setText(bookBeanTableData.getBookPrice());
            bookAuthorTextField2.setText(bookBeanTableData.getBookAuthor());
            // 设置分类
            String str = bookBeanTableData.getBookType();

            int index = 0;
            List inputList = FXCollections.observableArrayList(bookTypeComboBox.getItems());
            for (int i = 0; i < inputList.size(); i++) {
                if (str.equals(inputList.get(i))) {
                    index = i;
                }
            }
            bookTypeComboBox2.getSelectionModel().select(index);//设置分类
            bookDescriptionTextArea.setText(bookBeanTableData.getBookDescription());
        }
    }
}
