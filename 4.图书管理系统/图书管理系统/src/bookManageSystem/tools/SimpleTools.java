package bookManageSystem.tools;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

public class SimpleTools {
    /**
     * 操作结果：统一界面字体
     *
     * @param font 字体
     */
    public void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }

    }

    /**
     * 操作结果：判断文本是否为空
     * @param str 字符串
     * @return 如果是非空的返回true，否则返回false
     */
    public boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 操作结果：获取JAR包所在的路径
     *
     * @return String JAR包所在的路径
     */
    public String getJARPath() {
        /**
         * 方法二：获取当前可执行jar包所在目录
         */
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        String filePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码，支持中文
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 获取jar包所在目录
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }

        File file = new File(filePath);
        filePath = file.getAbsolutePath();//得到windows下的正确路径
        return filePath;
    }
}

