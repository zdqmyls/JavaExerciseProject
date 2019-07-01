import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;


public class Main {
    static Point mousepoint;
    static MouseInfo mouseinfo=null;
    static Color pixel = new Color(0,0,0);
    static Integer X=0;
    static Integer Y=0;
    static Integer R=0,G=0,B=0;
    static JFrame frame=null;
    static JButton getColorbutton=null,hexToRgbButton=null,rgbToHexButton=null;
    static JTextField redTextField=null,greenTextField=null,blueTextField=null,outputHexTextField=null,rTextField=null,gTextField=null,bTextField=null,hexResultTextField=null,inputHexTextField=null,rResultTextField=null,gResultTextField=null,bResultTextField=null;
    static JLabel redLabel=null,greenLabel=null,blueLabel=null,hexLabel=null;

    public static JFrame mainScreenFrame(){
        frame=new JFrame();
        frame.setTitle("小尘取色器");
        frame.setSize(900,450);
        frame.setResizable(false);
        Methods.setTitleIcon(frame,"images/colorpicker.png");

        // 创建分隔面板
        JSplitPane splitPane=new JSplitPane();
        splitPane.setOneTouchExpandable(true);// 分隔条上显示快速 折叠/展开 两边组件的小按钮
        splitPane.setContinuousLayout(true);// 拖动分隔条时连续重绘组件
        splitPane.setLeftComponent(getScreenColor());
        splitPane.setRightComponent(transformPane());

        // 调用设置样式的方法
        setFrameAllConponentsStyle();

        frame.setContentPane(splitPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    /**
     * 操作结果：取色器面板
     * @return
     */
    public static JPanel getScreenColor(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel getColorPane=new JPanel();
        getColorPane.setLayout(new BorderLayout());
        getColorbutton = new JButton("取色");
        getColorPane.add(getColorbutton,BorderLayout.NORTH);
        JTextArea textArea = new JTextArea(10, 25);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);// 设置默认颜色
        textArea.setBorder(null);
        getColorPane.add(textArea,BorderLayout.CENTER);

        // 显示颜色面板
        JPanel buttonPane=new JPanel();
        redLabel = new JLabel("红(R):");
        redTextField = new JTextField(2);
        greenLabel = new JLabel("绿(G):");
        greenTextField = new JTextField(2);
        blueLabel = new JLabel("蓝(B):");
        blueTextField = new JTextField(2);
        hexLabel = new JLabel("HEX:");
        outputHexTextField = new JTextField(6);
        buttonPane.add(redLabel);
        buttonPane.add(redTextField);
        buttonPane.add(greenLabel);
        buttonPane.add(greenTextField);
        buttonPane.add(blueLabel);
        buttonPane.add(blueTextField);
        buttonPane.add(hexLabel);
        buttonPane.add(outputHexTextField);
        getColorPane.add(buttonPane,BorderLayout.SOUTH);

        // 设置面板
        JPanel settingPane=new JPanel();
        settingPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2,true),"设置", TitledBorder.LEFT,TitledBorder.TOP,new Font("微软雅黑",Font.PLAIN,16), BLACK));
        JCheckBox checkBox=new JCheckBox("总是在最上层",true);// 默认被选中
        checkBox.setFont(new Font("微软雅黑",Font.PLAIN,16));
        settingPane.add(checkBox);

        // 多选框监听器
        // 设置窗体是否在最上层
        checkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(checkBox.isSelected()==true){
                    frame.setAlwaysOnTop(true);
                }else if(checkBox.isSelected()==false){
                    frame.setAlwaysOnTop(false);
                }
            }
        });

        // 说明面板
        JPanel descriptionPane=new JPanel();
        descriptionPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2,true),"说明", TitledBorder.LEFT,TitledBorder.TOP,new Font("微软雅黑",Font.PLAIN,16), BLACK));
        JLabel descriptionLabel=new JLabel();
        descriptionLabel.setText("按下Alt键取色，可以直接在剪贴板复制RGB颜色");
        descriptionLabel.setFont(new Font("微软雅黑",Font.PLAIN,16));
        descriptionPane.add(descriptionLabel);

        panel.add(getColorPane,BorderLayout.NORTH);
        panel.add(settingPane,BorderLayout.CENTER);
        panel.add(descriptionPane,BorderLayout.SOUTH);

        getColorbutton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                //
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 当按下Alt键触发事件
                if(e.getKeyCode()==18){
                    try{
                        Robot robot = new Robot();
                        mousepoint = mouseinfo.getPointerInfo().getLocation();
                        //System.out.println(mousepoint);
                        pixel = robot.getPixelColor(mousepoint.x,mousepoint.y);
                        X = mousepoint.x;
                        Y = mousepoint.y;
                        R = pixel.getRed();
                        G = pixel.getGreen();
                        B = pixel.getBlue();
                        redTextField.setText(R.toString());
                        greenTextField.setText(G.toString());
                        blueTextField.setText(B.toString());
                        outputHexTextField.setText("#"+Integer.toHexString(R)+Integer.toHexString(G)+Integer.toHexString(B));//得到颜色的十六进制表示。
                        Color col=new Color(R,G,B);
                        textArea.setBackground(col);//对当前选中的颜色进行显示。
                        // 把RGB颜色复制到剪贴板中
                        // 获取系统剪贴板
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        // 获取RGB颜色
                        // 封装文本内容
                        Transferable transferable=new StringSelection(R+","+G+","+B);
                        // 把文本内容设置到系统剪贴板
                        clipboard.setContents(transferable, null);
                    }catch(AWTException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return panel;
    }


    /**
     * 操作结果：16进制与HEX格式转换面板
     * @return
     */
    public static JPanel transformPane(){
        JPanel transformColorPane=new JPanel();
        transformColorPane.setLayout(new GridLayout(2,1));

        JPanel rgbToHexPane=new JPanel();
        rgbToHexPane.setLayout(new BorderLayout());
        rgbToHexPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2,true),"RGB颜色代码转化成16位颜色", TitledBorder.LEFT,TitledBorder.TOP,new Font("微软雅黑",Font.BOLD,16), RED));

        JPanel rgbTextFieldPane=new JPanel();
        rTextField=new JTextField(2);
        gTextField=new JTextField(2);
        bTextField=new JTextField(2);
        rgbTextFieldPane.add(rTextField);
        rgbTextFieldPane.add(gTextField);
        rgbTextFieldPane.add(bTextField);

        JTextArea hexTextArea=new JTextArea(6,6);
        hexTextArea.setEditable(false);

        JPanel transformButtonPane=new JPanel();
        rgbToHexButton=new JButton("转换");
        hexResultTextField=new JTextField(5);
        transformButtonPane.add(rgbToHexButton);
        transformButtonPane.add(hexResultTextField);

        // 转换按钮的事件监听器
        rgbToHexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int R=Integer.parseInt(rTextField.getText());
                int G=Integer.parseInt(gTextField.getText());
                int B=Integer.parseInt(bTextField.getText());
                // 显示示例颜色
                hexTextArea.setBackground(new Color(R,G,B));
                // 显示转换结果
                hexResultTextField.setText(Methods.rgbToHex(R,G,B));
            }
        });

        rgbToHexPane.add(rgbTextFieldPane,BorderLayout.NORTH);
        rgbToHexPane.add(hexTextArea,BorderLayout.CENTER);
        rgbToHexPane.add(transformButtonPane,BorderLayout.SOUTH);

        transformColorPane.add(rgbToHexPane);

        /*16位颜色转换位RGB颜色*/
        JPanel hexToRgbPane=new JPanel();
        hexToRgbPane.setLayout(new BorderLayout());
        hexToRgbPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY,2,true),"十六位颜色代码转化成RGB颜色", TitledBorder.LEFT,TitledBorder.TOP,new Font("微软雅黑",Font.BOLD,16), RED));

        JPanel hexTextFieldPane=new JPanel();
        inputHexTextField=new JTextField(6);
        hexTextFieldPane.add(inputHexTextField);

        JTextArea rgbTextArea=new JTextArea();
        rgbTextArea.setEditable(false);

        JPanel transformButtonPane2=new JPanel();
        hexToRgbButton=new JButton("转换");
        rResultTextField=new JTextField(2);
        gResultTextField=new JTextField(2);
        bResultTextField=new JTextField(2);
        transformButtonPane2.add(hexToRgbButton);
        transformButtonPane2.add(rResultTextField);
        transformButtonPane2.add(gResultTextField);
        transformButtonPane2.add(bResultTextField);

        // 转换按钮的事件监听器
        hexToRgbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getHexText=inputHexTextField.getText();
                // 显示示例颜色
                rgbTextArea.setBackground(Methods.hexToRgb(getHexText));
                // 显示转换结果
                rResultTextField.setText(Integer.toString(Methods.hexToRgb(getHexText).getRed()));
                gResultTextField.setText(Integer.toString(Methods.hexToRgb(getHexText).getGreen()));
                bResultTextField.setText(Integer.toString(Methods.hexToRgb(getHexText).getBlue()));
            }
        });

        hexToRgbPane.add(hexTextFieldPane,BorderLayout.NORTH);
        hexToRgbPane.add(rgbTextArea,BorderLayout.CENTER);
        hexToRgbPane.add(transformButtonPane2,BorderLayout.SOUTH);

        transformColorPane.add(hexToRgbPane);

        return transformColorPane;
    }

    /**
     * 操作结果：设置该界面的一些样式
     */
    public static void setFrameAllConponentsStyle(){
        Style style=new Style();
        // 设置按钮样式
        style.setButtonStyle(getColorbutton);
        style.setButtonStyle(hexToRgbButton);
        style.setButtonStyle(rgbToHexButton);
        // 设置文本框样式
        style.setTextFieldStyle(redTextField);
        style.setTextFieldStyle(greenTextField);
        style.setTextFieldStyle(blueTextField);
        style.setTextFieldStyle(outputHexTextField);
        style.setTextFieldStyle(rTextField);
        style.setTextFieldStyle(gTextField);
        style.setTextFieldStyle(bTextField);
        style.setTextFieldStyle(hexResultTextField);
        style.setTextFieldStyle(inputHexTextField);
        style.setTextFieldStyle(rResultTextField);
        style.setTextFieldStyle(gResultTextField);
        style.setTextFieldStyle(bResultTextField);
        // 设置标签样式
        style.setLabelStyle(redLabel);
        style.setLabelStyle(greenLabel);
        style.setLabelStyle(blueLabel);
        style.setLabelStyle(hexLabel);
    }

}
