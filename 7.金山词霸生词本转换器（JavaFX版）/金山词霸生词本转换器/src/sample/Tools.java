package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Tools {

    /**
     * 操作结果：得到TXT文件的内容
     *
     * @param txtPath txt文件路径
     * @param number  单词个数
     * @return String[][] 二维数组
     */
    public String[][] getTxt(String txtPath, int number) {
        String[][] dataList = new String[number][3];
        for (int i = 0; i < dataList.length; i++) {
            for (int j = 0; j < dataList[i].length; j++) {
                dataList[i][j] = "";
            }
        }
        try {
            File file = new File(txtPath);
            if (file.isFile() && file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
                String s = null;
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    if (i >= number) {
                        break;
                    }
                    // 单词
                    if (String.valueOf(s.charAt(0)).equals("+")) {
                        dataList[i][0] = s.substring(1, s.length());
                    }
                    // 词性/词义
                    while ((s = br.readLine()) != null) {
                        if (String.valueOf(s.charAt(0)).equals("#")) {
                            sb.append(s.substring(1, s.length()) + " ");
                        } else {
                            dataList[i][2] = sb.toString();
                            sb = new StringBuilder();//清空StringBuilder
                            if (String.valueOf(s.charAt(0)).equals("&")) {
                                dataList[i][1] = s.substring(1, s.length());
                                i += 1;
                                break;
                            }
                        }
                        // 单词
                        if (String.valueOf(s.charAt(0)).equals("+")) {
                            dataList[i][0] = s.substring(1, s.length());
                        }
                    }
                }
                br.close();
            } else {
                System.out.println("The file is not exist!");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return dataList;
    }

    /**
     * 操作结果：获取TXT文件的单词和音标
     *
     * @param txtPath txt文件路径
     * @param number  单词个数
     * @return String[][] 二维数组
     */
    public String[][] getSounds(String txtPath, int number) {
        String[][] dataList = new String[number][2];
        for (int i = 0; i < dataList.length; i++) {
            for (int j = 0; j < dataList[i].length; j++) {
                dataList[i][j] = "";
            }
        }
        try {
            File file = new File(txtPath);
            if (file.isFile() && file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
                String s = null;
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    if (i >= number) {
                        break;
                    }
                    // 单词
                    if (String.valueOf(s.charAt(0)).equals("+")) {
                        dataList[i][0] = s.substring(1, s.length());
                    }
                    // 音标
                    if (String.valueOf(s.charAt(0)).equals("&")) {
                        dataList[i][1] = s.substring(1, s.length());
                        i += 1;
                    }
                }
                br.close();
            } else {
                System.out.println("The file is not exist!");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return dataList;
    }

    /**
     * 操作结果：获取TXT文件的单词和释义
     *
     * @param txtPath txt文件路径
     * @param number  单词个数
     * @return String[][] 二维数组
     */
    public String[][] getMeaning(String txtPath, int number) {
        String[][] dataList = new String[number][2];
        for (int i = 0; i < dataList.length; i++) {
            for (int j = 0; j < dataList[i].length; j++) {
                dataList[i][j] = "";
            }
        }
        try {
            File file = new File(txtPath);
            if (file.isFile() && file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
                String s = null;
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    if (i >= number) {
                        break;
                    }
                    // 单词
                    if (String.valueOf(s.charAt(0)).equals("+")) {
                        dataList[i][0] = s.substring(1, s.length());
                    }
                    // 词性/词义
                    while ((s = br.readLine()) != null) {
                        if (String.valueOf(s.charAt(0)).equals("#")) {
                            sb.append(s.substring(1, s.length()) + " ");
                        } else {
                            dataList[i][1] = sb.toString();
                            sb = new StringBuilder();//清空StringBuilder
                            i += 1;
                            break;
                        }
                    }
                }
                br.close();
            } else {
                System.out.println("The file is not exist!");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return dataList;
    }

    /**
     * 操作结果：导出数据内容到excel中
     *
     * @param title  String[] 表头数据
     * @param values String[][] 表数据（非表头的）
     */
    public String exportExcel(String[] title, String[][] values, String exportPath) {
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        HSSFRow row = null;
        HSSFCellStyle style = null;
        HSSFCell cell = null;
        //第一步，创建一个HSSFWorkbook对象，对应一个excel文件
        wb = new HSSFWorkbook();
        //第二步，在wb添加一个sheet，对应excel文件中的sheet
        sheet = wb.createSheet("Sheet1");
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        row = sheet.createRow(0);
        //第四步，创建单元格，并设置值表头 设置表头居中
        style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中格式
        //第五步，添加数据到表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
//            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        }
        //第六步，写入实体数据，实际应用中这些数据从数据库获得
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
//                row.createCell((short)j).setEncoding(HSSFCell.ENCODING_UTF_16);
                row.createCell((short) j).setCellValue(values[i][j]);
            }
        }
        //第七步，输出excel文件
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");//定义日期格式
        String name = exportPath + format.format(date) + ".xls";//拼接文件名
        //创建一个文件字节输入流
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(name);
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
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
        Alert alert = new Alert(alterType, message, new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
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
}
