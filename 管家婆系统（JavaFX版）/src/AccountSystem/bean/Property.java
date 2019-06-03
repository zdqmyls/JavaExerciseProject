package AccountSystem.bean;

import java.util.ArrayList;

public class Property {
    private ArrayList inputClassificationList;// 收入分类
    private ArrayList outputClassificationList;// 支出分类

    public Property() {}

    public void setInputClassificationList(ArrayList inputClassificationList) {
        this.inputClassificationList = inputClassificationList;
    }

    public void setOutputClassificationList(ArrayList outputClassificationList) {
        this.outputClassificationList = outputClassificationList;
    }

    //初始化集合
    public ArrayList getInputClassificationList(){
        inputClassificationList=new ArrayList();
        inputClassificationList.add("工资");
        inputClassificationList.add("补贴");
        inputClassificationList.add("奖金");
        return inputClassificationList;
    }

    //初始化集合
    public ArrayList getOutputClassificationList(){
        outputClassificationList=new ArrayList();
        outputClassificationList.add("饮食");
        outputClassificationList.add("服饰");
        outputClassificationList.add("交通");
        outputClassificationList.add("住宿");
        outputClassificationList.add("文娱");
        outputClassificationList.add("生活用品");
        return outputClassificationList;
    }

}
