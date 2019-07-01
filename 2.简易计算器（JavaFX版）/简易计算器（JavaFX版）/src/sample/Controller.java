package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.Vector;

public class Controller {

    @FXML
    private TextField resultTextField;

    // 操作数1，为了程序的安全，初值一定设置，这里我们设置为0。
    String str1 = "0";

    // 操作数2
    String str2 = "0";

    // 运算符
    String signal = "+";

    // 运算结果
    String result = "";

    // 以下k1至k2为状态开关
    // 开关1用于选择输入方向，将要写入str1或str2
    int k1 = 1;
    // 开关2用于记录符号键的次数，如果 k2>1 说明进行的是 2+3-9+8 这样的多符号运算
    int k2 = 1;
    // 开关3用于标识 str1 是否可以被清0 ，等于1时可以，不等于1时不能被清0
    int k3 = 1;
    // 开关4用于标识 str2 是否可以被清0
    int k4 = 1;
    // 开关5用于控制小数点可否被录入，等于1时可以，不为1时，输入的小数点被丢掉
    int k5 = 1;
    // store的作用类似于寄存器，用于记录是否连续按下符号键
    Button store;

    @SuppressWarnings("rawtypes")
    Vector vt = new Vector(20, 10);

    // ”清除“按钮的事件监听器
    public void clearButtonEvent(ActionEvent event) {
        do_clear_event(event);
    }

    // ”%”求模按钮的事件监听器
    public void moduleButtonEvent(ActionEvent event) {
        do_mark_event(event);
    }

    // “*”按钮的事件监听器
    public void multiplyButtonEvent(ActionEvent event) {
        do_mark_event(event);
    }

    // ”/“除按钮的事件监听器
    public void dividedButtonEvent(ActionEvent event) {
        do_mark_event(event);
    }

    // ”-“按钮的事件监听器
    public void subtractButtonEvent(ActionEvent event) {
        do_mark_event(event);
    }

    // ”+“按钮的事件监听器
    public void addButtonEvent(ActionEvent event) {
        do_mark_event(event);
    }

    // ”.“按钮的事件监听器
    public void decimalPointButtonEvent(ActionEvent event) {
        do_decimalPoint_event(event);
    }

    // “=”按钮的事件监听器
    public void equalsButtonEvent(ActionEvent event) {
        do_equals_event(event);
    }

    public void sevenButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void nightButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void nineButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void fourButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void fiveButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void sixButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void oneButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void twoButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void threeButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void zeroZeroButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    public void zeroButtonEvent(ActionEvent event) {
        do_number_event(event);
    }

    /**
     * 操作结果：数字键处理事件
     *
     * @param event
     */
    public void do_number_event(ActionEvent event) {
        String ss = ((Button) event.getSource()).getText();
        store = (Button) event.getSource();
        vt.add(store);
        if (k1 == 1) {
            if (k3 == 1) {
                str1 = "";
                k5 = 1;// 还原开关k5状态
            }
            str1 = str1 + ss;
            k3 = k3 + 1;
            resultTextField.setText(str1);// 显示结果
        } else if (k1 == 2) {
            if (k4 == 1) {
                str2 = "";
                k5 = 1;// 还原开关k5状态
            }
            str2 = str2 + ss;
            k4 = k4 + 1;
            resultTextField.setText(str2);
        }
    }

    /**
     * 操作结果：运算符号键处理事件
     *
     * @param event
     */
    public void do_mark_event(ActionEvent event) {
        String ss2 = ((Button) event.getSource()).getText();
        store = (Button) event.getSource();
        vt.add(store);

        if (k2 == 1) {
            // 开关 k1 为 1 时向数 1 写输入值，为2时向数2写输入值。
            k1 = 2;
            k5 = 1;
            signal = ss2;
            k2 = k2 + 1;// 按符号键的次数
        } else {
            int a = vt.size();
            Button c = (Button) vt.get(a - 2);

            if (!(c.getText().equals("+"))
                    && !(c.getText().equals("-"))
                    && !(c.getText().equals("*"))
                    && !(c.getText().equals("/"))
                    && !(c.getText().equals("%"))
            ) {
                cal();
                str1 = result;
                // 开关 k1 为 1 时，向数 1 写值，为2时向数2写
                k1 = 2;
                k5 = 1;
                k4 = 1;
                signal = ss2;
            }
            k2 = k2 + 1;

        }
    }

    /**
     * 操作结果：清除键处理事件
     *
     * @param event
     */
    public void do_clear_event(ActionEvent event) {
        store = (Button) event.getSource();
        vt.add(store);
        k5 = 1;
        k2 = 1;
        k1 = 1;
        k3 = 1;
        k4 = 1;
        str1 = "0";
        str2 = "0";
        signal = "";
        result = "";
        resultTextField.setText(result);
        vt.clear();
    }

    /**
     * 操作结果：等号键处理事件
     *
     * @param event
     */
    public void do_equals_event(ActionEvent event) {
        store = (Button) event.getSource();
        vt.add(store);
        cal();

        // 还原各个开关的状态
        k1 = 1;
        k2 = 1;
        k3 = 1;
        k4 = 1;

        str1 = result;
    }

    /**
     * 操作结果：小数点键处理事件
     *
     * @param event
     */
    public void do_decimalPoint_event(ActionEvent event) {
        store = (Button) event.getSource();
        vt.add(store);
        if (k5 == 1) {
            String ss2 = ((Button) event.getSource()).getText();
            if (k1 == 1) {
                if (k3 == 1) {
                    str1 = "";
                    // 还原开关k5状态
                    k5 = 1;
                }
                str1 = str1 + ss2;

                k3 = k3 + 1;

                // 显示结果
                resultTextField.setText(str1);

            } else if (k1 == 2) {
                if (k4 == 1) {
                    str2 = "";
                    // 还原开关k5的状态
                    k5 = 1;
                }
                str2 = str2 + ss2;

                k4 = k4 + 1;

                resultTextField.setText(str2);
            }
        }

        k5 = k5 + 1;
    }

    /**
     * 操作结果：计算逻辑
     */
    public void cal() {
        // 操作数1
        double a2;
        // 操作数2
        double b2;
        // 运算符
        String c = signal;
        // 运算结果
        double result2 = 0;

        if (c.equals("")) {
            resultTextField.setText("Please input operator");

        } else {
            // 手动处理小数点的问题
            if (str1.equals("."))
                str1 = "0.0";
            if (str2.equals("."))
                str2 = "0.0";
            a2 = Double.valueOf(str1).doubleValue();
            b2 = Double.valueOf(str2).doubleValue();

            if (c.equals("+")) {
                result2 = a2 + b2;
            }
            if (c.equals("-")) {
                result2 = a2 - b2;
            }
            if (c.equals("*")) {
                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                result2 = m1.multiply(m2).doubleValue();
            }
            if (c.equals("/")) {
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }

            }
            if (c.equals("%")) {
                result2 = a2 % b2;
            }

            result = ((new Double(result2)).toString());

            resultTextField.setText(result);
        }
    }
}
