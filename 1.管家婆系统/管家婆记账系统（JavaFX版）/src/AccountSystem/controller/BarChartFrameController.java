package AccountSystem.controller;

import AccountSystem.tools.ChartTools;
import AccountSystem.tools.PublicTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;

import java.util.Calendar;

public class BarChartFrameController {
    private ChartTools chartTools=new ChartTools();
    private PublicTools publicTools = new PublicTools();

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private ComboBox<?> barChart_comboBox;

    @FXML
    private NumberAxis numberAxis;

    @FXML // 初始化界面
    public void initialize() {
        publicTools.public_addComboBoxItems(barChart_comboBox, new String[]{"今天", "昨天", "最近3天", "最近7天", "最近30天", "最近1"
                + "年（12月）", "最近1年（4季度）"});
    }

    public void barChart_comboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) barChart_comboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("今天")) {
            chartTools.public_setBarChartData(1, barChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("昨天")) {
            chartTools.public_setBarChartData(2, barChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近3天")) {
            chartTools.public_setBarChartData(3, barChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近7天")) {
            chartTools.public_setBarChartData(7, barChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近30天")) {
            chartTools.public_setBarChartData(30, barChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近1年（12月）")) {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONDAY) + 1;
            chartTools.public_setMonthBarChartData(month, barChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近1年（4季度）")) {
            chartTools.public_setSeasonBarChartData(barChart, categoryAxis, numberAxis);
        }
    }
}

