package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    private TextField wordNumberTextField;

    @FXML
    private CheckBox meaningCheckBox;

    @FXML
    private TextField importTextArea;

    @FXML
    private CheckBox soundsCheckBox;

    @FXML
    private CheckBox wordCheckBox;

    @FXML
        // 【导入】按钮的事件监听器
    void do_importButton_event(ActionEvent event) {
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //设置默认文件过滤器
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("文本文档", "txt"));
        //打开文件选择框，并得到选中的文件
        File result = fileChooser.showOpenDialog(null);
        String importPath = result.getAbsolutePath();
        importTextArea.setText(importPath);
    }

    @FXML
        // 【转换】按钮的事件监听器
    void do_changeButton_event(ActionEvent event) {
        if (importTextArea.getText().length()<=0) {
            new Tools().informationDialog(Alert.AlertType.WARNING,"提示","警告","请选择txt文件！");
        } else {
            String exportFilePath =
                    new File(importTextArea.getText()).getParentFile().getAbsolutePath() + "\\" + (new File(importTextArea.getText())).getName().substring(0, (new File(importTextArea.getText())).getName().lastIndexOf(".")) + ".xls";
            int wordNumber = Integer.parseInt(wordNumberTextField.getText());
            boolean wordIsSelected = wordCheckBox.isSelected();
            boolean soundsIsSelected = soundsCheckBox.isSelected();
            boolean meaningIsSelected = meaningCheckBox.isSelected();
            if (wordIsSelected == true && soundsIsSelected == true && meaningIsSelected == true) {
                String[][] values = new Tools().getTxt(importTextArea.getText(), wordNumber);
                String[] title = {"单词", "音标", "释义"};
                new Tools().exportExcel(title, values, exportFilePath);
            } else if (wordIsSelected == true && soundsIsSelected == true) {
                String[][] values = new Tools().getSounds(importTextArea.getText(), wordNumber);
                String[] title = {"单词", "音标"};
                new Tools().exportExcel(title, values, exportFilePath);
            } else if (wordIsSelected == true && meaningIsSelected == true) {
                String[][] values = new Tools().getMeaning(importTextArea.getText(), wordNumber);
                String[] title = {"单词", "释义"};
                new Tools().exportExcel(title, values, exportFilePath);
            }
        }
    }

    @FXML
        // 【退出】按钮的事件监听器
    void do_exitButton_event(ActionEvent event) {
        System.exit(0);
    }

}
