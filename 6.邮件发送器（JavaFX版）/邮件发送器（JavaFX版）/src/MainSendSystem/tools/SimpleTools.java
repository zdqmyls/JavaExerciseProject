package MainSendSystem.tools;

import MainSendSystem.MainApp;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.mail.*;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

public class SimpleTools {
    /**
     * 操作结果：MD5加密
     *
     * @param str 需要加密的字符串
     * @return String 返回加密成功的字符串
     */
    public String MD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte b[] = md5.digest();

            StringBuffer sb = new StringBuffer("");
            for (int n = 0; n < b.length; n++) {
                int i = b[n];
                if (i < 0) i += 256;
                if (i < 16) sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();  //32位加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 操作结果：读取配置属性文件
     *
     * @param propertiesFilePath 配置属性文件路径
     * @return String 根据键获取的内容值
     */
    public List readReturnList(String propertiesFilePath) {
        Properties properties = new Properties();
        String returnValue;
        List list = new ArrayList();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(propertiesFilePath));
            properties.load(inputStream);
            Iterator<String> iterator = properties.stringPropertyNames().iterator();
            while (iterator.hasNext()) {
                returnValue = properties.getProperty(iterator.next());
                list.add(returnValue);
            }
            inputStream.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 操作结果：生成属性文件
     *
     * @param fileName 文件路径
     * @param maps     数据集合
     */
    public void dataWriteProperties(String fileName, Map<String, String> maps) {
        Properties properties = new Properties();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            // 写入文件
            for (String key : maps.keySet()) {
                properties.setProperty(key, String.valueOf(maps.get(key)));
            }
            FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, null);//store(...)指定的流仍保持打开状态
            fos.flush();
            fos.close();// 关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 操作结果：读取配置文件
     *
     * @param propertiesFilePath 文件路径
     * @return Map 数据集合
     */
    public Map readReturnMap(String propertiesFilePath) {
        Properties properties = new Properties();
        Map map = new HashMap();
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(propertiesFilePath));
            properties.load(inputStream);
            map.put("addresser", properties.getProperty("addresser"));
            map.put("password", properties.getProperty("password"));
            map.put("server", properties.getProperty("server"));
            inputStream.close();
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 操作结果：获取文件选择器所选中的文件
     *
     * @return File 文件选择器选中的文件
     */
    public File getSelectedFile() {
        String selectedFilePath = "";
        //实例化文件选择器
        FileChooser fileChooser = new FileChooser();
        //打开文件选择框
        File result = fileChooser.showOpenDialog(null);
        // 选择文件的路径
        selectedFilePath = result.getAbsolutePath();
        return new File(selectedFilePath);
    }

    /**
     * 操作结果：JavaFX设置按钮、标签等组件的图标
     *
     * @param labeleds   需要设置图标的按钮
     * @param imagePaths 图标的路径
     */
    public void setLabeledImage(Labeled[] labeleds, String[] imagePaths) {
        for (int i = 0; i < labeleds.length; i++) {
            labeleds[i].setGraphic(new ImageView(new Image("file:" + imagePaths[i])));
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

