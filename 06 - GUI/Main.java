import javax.swing.*;
import java.awt.*;

public class Main {

    public static void AbsoluteBoundsTest(){
        try {
       //     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
       //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
       //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Throwable thrown) {
            thrown.printStackTrace();
        }
        AbsoluteBoundsTest abt = new AbsoluteBoundsTest();
        abt.setVisible(true);
    }

    public static void ComponentOrientationTest(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        ComponentOrientationTest cot = new ComponentOrientationTest();
        cot.setLocation(size.width/2-205, size.height/2-110);
        cot.setVisible(true);
    }

    public static void CardLayoutTest(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        new CardLayoutTest().setVisible(true);
    }

    public static void FlowLayoutTest(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        new FlowLayoutTest().setVisible(true);
    }

    public static void GridLayoutTest(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        new GridLayoutTest().setVisible(true);
    }
    public static void GridBagLayoutTest(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        new GridBagLayoutTest().setVisible(true);
    }

    public static void main(String[] args) {
        //AbsoluteBoundsTest();
        //ComponentOrientationTest();
        //CardLayoutTest();
        //FlowLayoutTest();
        //GridLayoutTest();
        //GridBagLayoutTest();
    }
}