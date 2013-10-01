import java.awt.*;
import javax.swing.*;

public class ComponentOrientationTest extends JFrame {

    private final static int WIDTH = 410;
    private final static int HEIGHT = 220;

    public ComponentOrientationTest() {
        super("Component orientation test");
        JPanel content = new JPanel(new BorderLayout(5, 5));
        content.add(createLabel("Top"), BorderLayout.NORTH);
        content.add(createLabel("Bottom"), BorderLayout.SOUTH);
        content.add(createLabel("Left"), BorderLayout.WEST);
        content.add(createLabel("Right"), BorderLayout.EAST);
        content.add(createLabel("Center"), BorderLayout.CENTER);
        setContentPane(content);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JLabel createLabel(String caption) {
        JLabel lbl = new JLabel(caption);
        lbl.setPreferredSize(new Dimension(100, 50));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setBorder(BorderFactory.createLineBorder(new Color(0xff8000), 3));
        return lbl;
    }
}