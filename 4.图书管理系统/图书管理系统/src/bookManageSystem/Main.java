package bookManageSystem;

import bookManageSystem.tools.SimpleTools;
import bookManageSystem.view.LogupFrame;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new SimpleTools().initGlobalFont(new Font("微软雅黑", Font.PLAIN, 16));
        new LogupFrame().setVisible(true);
    }
}
