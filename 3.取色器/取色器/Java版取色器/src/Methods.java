import javax.swing.*;
import java.awt.*;

public class Methods {
    /**
     * 操作结果：将三位RGB值转换为HEX格式
     * @param R int
     * @param G int
     * @param B int
     * @return String HEX格式字符串
     */
    public static String rgbToHex(int R,int G,int B){
        return "#"+Integer.toHexString(R)+Integer.toHexString(G)+Integer.toHexString(B);
    }

    /**
     * 操作结果：将HEX格式字符串转换成Color对象
     * @param hex HEX格式字符串
     * @return Color 转换完成的颜色对象
     */
    public static Color hexToRgb(String hex){
        String rHex=hex.substring(1,3);
        String gHex=hex.substring(3,5);
        String bHex=hex.substring(5,7);
        int R=Integer.parseInt(rHex,16);
        int G=Integer.parseInt(gHex,16);
        int B=Integer.parseInt(bHex,16);
        return new Color(R,G,B);
    }

    /**
     * 操作结果：设置主窗体的图标标题
     * @param frame 窗体
     * @param titleIconPath 标题图片路径
     */
    public static void setTitleIcon(JFrame frame, String titleIconPath) {
        //Java提供的GUI默认工具类对象
        Toolkit kit=Toolkit.getDefaultToolkit();
        //为指定窗口设置图标标题
        frame.setIconImage(kit.createImage(titleIconPath));
    }
}
