import javax.swing.*;
import java.awt.*;

public class Style {
    /**
     * 操作结果：设置按钮样式
     * @param button
     */
    public static void setButtonStyle(JButton button){
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("微软雅黑", Font.PLAIN,18));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(221,232,243));
    }

    /**
     * 操作结果：设置文本框样式
     * @param textField
     */
    public static void setTextFieldStyle(JTextField textField){
        textField.setFont(new Font("微软雅黑", Font.PLAIN,20));
        textField.setForeground(Color.BLUE);
        textField.setBackground(Color.WHITE);
        textField.setMinimumSize(new Dimension(3,5));
    }

    public static void setLabelStyle(JLabel label){
        label.setFont(new Font("微软雅黑",Font.PLAIN,16));
        label.setForeground(Color.BLACK);
    }
}
