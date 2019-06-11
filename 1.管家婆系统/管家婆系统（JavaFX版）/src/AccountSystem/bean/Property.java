package AccountSystem.bean;

import AccountSystem.tools.SimpleTools;

import java.io.IOException;
import java.util.ArrayList;

public class Property {
    private ArrayList inputClassificationList;// 收入分类
    private ArrayList outputClassificationList;// 支出分类

    public Property() {
    }

    public void setInputClassificationList(ArrayList inputClassificationList) {
        this.inputClassificationList = inputClassificationList;
    }

    public void setOutputClassificationList(ArrayList outputClassificationList) {
        this.outputClassificationList = outputClassificationList;
    }

    //初始化集合
    public ArrayList getInputClassificationList() throws IOException {
        inputClassificationList = new ArrayList();
        String readResult = new SimpleTools().dataReadProperties("E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\properties\\classification.ini", "input");
        String[] splitArray = new SimpleTools().convertStrToArray(readResult, ",");
        for (int i = 0; i < splitArray.length; i++) {
            inputClassificationList.add(splitArray[i]);
        }
        return inputClassificationList;
    }

    //初始化集合
    public ArrayList getOutputClassificationList() throws IOException {
        outputClassificationList = new ArrayList();
        String readResult = new SimpleTools().dataReadProperties("E:\\GitHub\\LearningSource\\实战\\管家婆系统（JavaFX版）\\src\\AccountSystem\\properties\\classification.ini", "output");
        String[] splitArray = new SimpleTools().convertStrToArray(readResult, ",");
        for (int i = 0; i < splitArray.length; i++) {
            outputClassificationList.add(splitArray[i]);
        }
        return outputClassificationList;
    }

}
