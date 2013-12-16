import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;

class View implements Observer {
    private TextField myTextField;
    private Button button;

    View() {
        System.out.println("View()");

        Frame frame 		= new Frame("simple MVC");
        frame.add("North", new Label("counter"));

        myTextField 		= new TextField();
        frame.add("Center", myTextField);

        Panel panel 		= new Panel();
        button	 		= new Button("PressMe");
        panel.add(button);
        frame.add("South", panel);

        frame.addWindowListener(new CloseListener());
        frame.setSize(200,100);
        frame.setLocation(100,100);
        frame.setVisible(true);

    }

    public void update(Observable obs, Object obj) {
        myTextField.setText("" + ((Integer)obj).intValue());
    }

    public void setValue(int v){
        myTextField.setText("" + v);
    }

    public void addController(Controller controller){
        System.out.println("View      : adding controller");
        button.addActionListener(controller);
    }

    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}