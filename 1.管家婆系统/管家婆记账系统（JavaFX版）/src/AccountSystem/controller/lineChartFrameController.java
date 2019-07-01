package AccountSystem.controller;

import AccountSystem.tools.ChartTools;
import AccountSystem.tools.PublicTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;

import java.util.Calendar;

public class lineChartFrameController {
    private PublicTools publicTools = new PublicTools();
    private ChartTools chartTools=new ChartTools();

    @FXML // fx:id="categoryAxis"
    private CategoryAxis categoryAxis; // Value injected by FXMLLoader

    @FXML // fx:id="lineChart_comboBox"
    private ComboBox<?> lineChart_comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="lineChart"
    private LineChart<?, ?> lineChart; // Value injected by FXMLLoader

    @FXML // fx:id="numberAxis"
    private NumberAxis numberAxis; // Value injected by FXMLLoader

    @FXML // 初始化界面
    public void initialize() {
        publicTools.public_addComboBoxItems(lineChart_comboBox, new Object[]{"最近3天", "最近7天", "最近30天", "最近1年（12月）",
                "最近1年（4季度）"});
    }

    // 折线图界面下拉列表框事件方法
    public void lineChart_comboBoxEvent(ActionEvent event) {
        //只处理选中的状态
        String selectedCoboboxItem = (String) lineChart_comboBox.getSelectionModel().selectedItemProperty().getValue();
        if (selectedCoboboxItem.equals("最近3天")) {
            chartTools.public_setDayLineChartData(3, lineChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近7天")) {
            chartTools.public_setDayLineChartData(7, lineChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近30天")) {
            chartTools.public_setDayLineChartData(30, lineChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近1年（12月）")) {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONDAY) + 1;
            chartTools.public_setMonthLineChartData(month, lineChart, categoryAxis, numberAxis);
        } else if (selectedCoboboxItem.equals("最近1年（4季度）")) {
            chartTools.public_setSeasonLineChartData(lineChart, categoryAxis, numberAxis);
        }
    }

}
