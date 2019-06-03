package AccountSystem.tools;

import AccountSystem.bean.Records;
import AccountSystem.bean.TableData;
import AccountSystem.dao.RecordsDao;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class PublicTools {
    private SimpleTools simpleTools=new SimpleTools();

    /**
     * 操作结果：”删除“右键菜单的事件方法
     */
    public void public_do_deleteContextMenu_Event(TableView<TableData> tableView){
        RecordsDao recordsDao=new RecordsDao();
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        TableData td=tableView.getSelectionModel().getSelectedItem();
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
    public void public_do_addContextMenu_Event(TableView<TableData> tableView){
        simpleTools.informationDialog(Alert.AlertType.INFORMATION,"提示","信息","该功能暂未实现！");
    }

    /**
     * 操作结果：”修改“右键菜单的事件方法
     */
    public void public_do_alterContextMenu_Event(TableView<TableData> tableView){
        simpleTools.informationDialog(Alert.AlertType.INFORMATION,"提示","信息","该功能暂未实现！");
    }

    /**
     * 操作结果：给表格视图填充数据
     * @param sql
     * @param idColumn
     * @param typeColumn
     * @param moneyColumn
     * @param classificationColumn
     * @param memoColumn
     * @param dateColumn
     * @return
     */
    public ObservableList<TableData> public_tableViewData(String sql, TableColumn<TableData,String> idColumn, TableColumn<TableData,String> typeColumn, TableColumn<TableData,String> moneyColumn, TableColumn<TableData,String> classificationColumn, TableColumn<TableData,String> memoColumn, TableColumn<TableData,String> dateColumn){
        RecordsDao recordsDao=new RecordsDao();
        List list=recordsDao.getRecordsDataBySql(sql);

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        moneyColumn.setCellValueFactory(cellData -> cellData.getValue().moneyProperty());
        classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());
        memoColumn.setCellValueFactory(cellData -> cellData.getValue().memoProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        ObservableList<TableData> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            Records r = (Records) list.get(i);
            TableData td = new TableData(String.valueOf(r.getRecordId()), r.getRecordType(), String.valueOf(r.getRecordMoney()), r.getRecordClassification(), r.getRecordMemo(), r.getRecordDate());
            data.add(td);
        }
        return data;
    }

    /**
     * 操作结果：设置条形图的数据根据给定的天数
     * @param day 给定的天数
     * @param barChart 条形图
     * @param categoryAxis X轴
     * @param numberAxis Y轴
     */
    public void public_setBarChartData(int day, BarChart<?, ?> barChart,CategoryAxis categoryAxis, NumberAxis numberAxis){
        RecordsDao recordsDao=new RecordsDao();
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        XYChart.Series inputSeries=new XYChart.Series();
        inputSeries.setName("收入");
        XYChart.Series outputSeries=new XYChart.Series();
        outputSeries.setName("支出");

        for(int i=0;i<day;i++){
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,-i);
            Date calendarTime=calendar.getTime();
            String date=simpleTools.dateFormat(calendarTime,"yyyy-MM-dd");
            String dateInputsql="select SUM(rMoney) from tb_records where rType='收入' and rDate= '"+date+"';";
            String dateOutputsql="select SUM(rMoney) from tb_records where rType='支出' and rDate= '"+date+"';";
            Object dateInput=recordsDao.getResultValue(dateInputsql).get(0);
            Object dateOutput=recordsDao.getResultValue(dateOutputsql).get(0);
            categoryAxis.getCategories().add(date);
            inputSeries.getData().add(new XYChart.Data<>(date,dateInput));
            outputSeries.getData().add(new XYChart.Data<>(date,dateOutput));
        }

        barChart.getData().clear();
        barChart.getData().addAll(inputSeries,outputSeries);
    }

    /**
     * 操作结果：设置饼图的数据根据给定的天数
     * @param day 给定的天数
     * @param pieChart 饼图
     */
    public void public_setPieChartData(int day,PieChart pieChart,String type){
        RecordsDao recordsDao=new RecordsDao();
        ObservableList observableList=FXCollections.observableArrayList();
        for(int i=0;i<day;i++){
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,-i);
            Date calendarTime=calendar.getTime();
            String date=simpleTools.dateFormat(calendarTime,"yyyy-MM-dd");
            String dateInputsql="select SUM(rMoney) from tb_records where rType='"+type+"' and rDate= '"+date+"';";
            Object dateInput=recordsDao.getResultValue(dateInputsql).get(0);
            observableList.add(new PieChart.Data(date,(Float)dateInput));
        }
        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.setData(observableList);
    }

    /**
     * 操作结果：设置折线图的数据根据给定的天数
     * @param day 给定的天数
     * @param lineChart 折线图
     */
    public void public_setLineChartData(int day, LineChart<?, ?> lineChart,CategoryAxis categoryAxis,NumberAxis numberAxis){
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        RecordsDao recordsDao=new RecordsDao();

        XYChart.Series inputSeries=new XYChart.Series();
        inputSeries.setName("收入");

        XYChart.Series outputSeries=new XYChart.Series();
        outputSeries.setName("支出");

        for(int i=0;i<day;i++){
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,-i);
            Date calendarTime=calendar.getTime();
            String date=simpleTools.dateFormat(calendarTime,"yyyy-MM-dd");
            String dateInputsql="select SUM(rMoney) from tb_records where rType='收入' and rDate= '"+date+"';";
            String dateOutputsql="select SUM(rMoney) from tb_records where rType='支出' and rDate= '"+date+"';";
            Object dateInput=recordsDao.getResultValue(dateInputsql).get(0);
            Object dateOutput=recordsDao.getResultValue(dateOutputsql).get(0);
            inputSeries.getData().add(new XYChart.Data<>(date,dateInput));
            outputSeries.getData().add(new XYChart.Data<>(date,dateOutput));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(inputSeries,outputSeries);
    }

    /**
     * 操作结果：设置条形图的数据根据给定的月数
     * @param Month 给定的月数
     * @param barChart 条形图
     * @param categoryAxis X轴
     * @param numberAxis Y轴
     */
    public void public_setMonthBarChartData(int Month, BarChart<?, ?> barChart,CategoryAxis categoryAxis, NumberAxis numberAxis){
        RecordsDao recordsDao=new RecordsDao();
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        XYChart.Series monthInputSeries=new XYChart.Series();
        monthInputSeries.setName("收入");
        XYChart.Series monthOutputSeries=new XYChart.Series();
        monthOutputSeries.setName("支出");

        for(int i=0;i<Month;i++){
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONDAY,-i);
            Date monthDate=calendar.getTime();
            String monthStringDate=simpleTools.dateFormat(monthDate,"yyyy-MM");
            String MonthInputsql="select SUM(rMoney) from tb_records where rType='收入' and MONTH(rDate)= MONTH('"+monthStringDate+"');";
            String MonthOutputsql="select SUM(rMoney) from tb_records where rType='支出' and MONTH(rDate)= MONTH('"+monthStringDate+"');";
            Object MonthInput=recordsDao.getResultValue(MonthInputsql).get(0);
            Object MonthOutput=recordsDao.getResultValue(MonthOutputsql).get(0);
            categoryAxis.getCategories().add(monthStringDate);
            monthInputSeries.getData().add(new XYChart.Data<>(monthStringDate,MonthInput));
            monthOutputSeries.getData().add(new XYChart.Data<>(monthStringDate,MonthOutput));
        }

        barChart.getData().clear();
        barChart.getData().addAll(monthInputSeries,monthOutputSeries);
    }

    /**
     * 操作结果：设置折线图的数据根据给定的月数
     * @param Month 给定的月数
     * @param lineChart 折线图
     */
    public void public_setMonthLineChartData(int Month,LineChart lineChart,CategoryAxis categoryAxis,NumberAxis numberAxis){
        categoryAxis.setLabel("日期");
        numberAxis.setLabel("金额");
        RecordsDao recordsDao=new RecordsDao();

        XYChart.Series monthInputSeries=new XYChart.Series();
        monthInputSeries.setName("收入");

        XYChart.Series monthOutputSeries=new XYChart.Series();
        monthOutputSeries.setName("支出");

        for(int i=0;i<Month;i++){
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONDAY,-i);
            Date monthDate=calendar.getTime();
            String monthStringDate=simpleTools.dateFormat(monthDate,"yyyy-MM");
            String MonthInputsql="select SUM(rMoney) from tb_records where rType='收入' and MONTH(rDate)= MONTH('"+monthStringDate+"');";
            String MonthOutputsql="select SUM(rMoney) from tb_records where rType='支出' and MONTH(rDate)= MONTH('"+monthStringDate+"');";
            Object MonthInput=recordsDao.getResultValue(MonthInputsql).get(0);
            Object MonthOutput=recordsDao.getResultValue(MonthOutputsql).get(0);
            monthInputSeries.getData().add(new XYChart.Data<>(monthStringDate,MonthInput));
            monthOutputSeries.getData().add(new XYChart.Data<>(monthStringDate,MonthOutput));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(monthInputSeries,monthOutputSeries);
    }

    /**
     * 操作结果：设置饼图的数据根据给定的月数
     * @param Month 给定的月数
     * @param pieChart 饼图
     * @param type 指定类型
     */
    public void public_setMonthPieChartData(int Month,PieChart pieChart,String type) {
        RecordsDao recordsDao=new RecordsDao();
        ObservableList observableList=FXCollections.observableArrayList();
        for(int i=0;i<Month;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONDAY, -i);
            Date monthDate = calendar.getTime();
            String monthStringDate = simpleTools.dateFormat(monthDate, "yyyy-MM");
            String monthsql = "select SUM(rMoney) from tb_records where rType='"+type+"' and MONTH(rDate)= MONTH('" + simpleTools.dateFormat(monthDate,"yyyy-MM-dd") + "');";
            Object monthResult = recordsDao.getResultValue(monthsql).get(0);
            observableList.add(new PieChart.Data(monthStringDate,(Float)monthResult));
        }

        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.setData(observableList);
    }

    /**
     * 操作结果：设置饼图的数据根据季度
     * @param pieChart 饼图
     * @param type 指定类型
     */
    public void public_setSeasonPieChartData(PieChart pieChart,String type){
        RecordsDao recordsDao=new RecordsDao();
        List<Date[]> list = simpleTools.yearQuarterList(new Date());
        Date[] date1 = list.get(0);
        Date[] date2 = list.get(1);
        Date[] date3 = list.get(2);
        Date[] date4 = list.get(3);

        String strdate1 = simpleTools.dateFormat(date1[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date1[1], "yyyy-MM-dd");
        String strdate2 = simpleTools.dateFormat(date2[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date2[1], "yyyy-MM-dd");
        String strdate3 = simpleTools.dateFormat(date3[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date3[1], "yyyy-MM-dd");
        String strdate4 = simpleTools.dateFormat(date4[0], "yyyy-MM-dd") + "-" + simpleTools.dateFormat(date4[1], "yyyy-MM-dd");

        String seasonOutputSql1 = "select SUM(rMoney) from tb_records where rType='"+type+"' and rDate between '" + simpleTools.dateFormat(date1[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date1[1], "yyyy-MM-dd") + "'";
        Object seasonOutput1 = recordsDao.getResultValue(seasonOutputSql1).get(0);
        String seasonOutputSql2 = "select SUM(rMoney) from tb_records where rType='"+type+"' and rDate between '" + simpleTools.dateFormat(date2[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date2[1], "yyyy-MM-dd") + "'";
        Object seasonOutput2 = recordsDao.getResultValue(seasonOutputSql2).get(0);
        String seasonOutputSql3 = "select SUM(rMoney) from tb_records where rType='"+type+"' and rDate between '" + simpleTools.dateFormat(date3[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date3[1], "yyyy-MM-dd") + "'";
        Object seasonOutput3 = recordsDao.getResultValue(seasonOutputSql3).get(0);
        String seasonOutputSql4 = "select SUM(rMoney) from tb_records where rType='"+type+"' and rDate between '" + simpleTools.dateFormat(date4[0], "yyyy-MM-dd") + "' and '" + simpleTools.dateFormat(date4[1], "yyyy-MM-dd") + "'";
        Object seasonOutput4= recordsDao.getResultValue(seasonOutputSql4).get(0);

        ObservableList observableList=FXCollections.observableArrayList(
                new PieChart.Data(strdate1, (Float) seasonOutput1),
                new PieChart.Data(strdate2, (Float) seasonOutput2),
                new PieChart.Data(strdate3, (Float) seasonOutput3),
                new PieChart.Data(strdate4, (Float) seasonOutput4)
        );

        pieChart.setTitle("饼图");// 设置饼图的标题
        pieChart.setClockwise(true);// 顺时针设置饼图的切片
        pieChart.setLabelLineLength(50);// 方法设置标签行的长度
        pieChart.setLabelsVisible(true);// 将饼图的标签设置为可见
        pieChart.setStartAngle(180);// 设置饼图的起始角度
        pieChart.getData().clear();
        pieChart.getData().addAll(observableList);
    }

}
