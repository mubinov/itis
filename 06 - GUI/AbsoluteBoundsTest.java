import javax.swing.*;

public class AbsoluteBoundsTest extends JFrame {

    public AbsoluteBoundsTest(){
        super("Absolute bounds test");
        JPanel content = new JPanel();
        content.setLayout(null);
        JLabel lblFirstName = new JLabel("First name");
        lblFirstName.setBounds(5,5,95,21); // (left, top, width, height)
        JLabel lblLastName = new JLabel("Last name");
        lblLastName.setBounds(5,30,95,21);
        JTextField tfFirstName = new JTextField(20);
        tfFirstName.setBounds(100,5,120,21);
        JTextField tfLastName = new JTextField(20);
        tfLastName.setBounds(100,30,120,21);
        JButton btnOk = new JButton("OK");
        btnOk.setBounds(65,60,75,21);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(145,60,75,21);
        content.add(lblFirstName);
        content.add(lblLastName);
        content.add(tfFirstName);
        content.add(tfLastName);
        content.add(btnOk);
        content.add(btnCancel);
        setSize(230,130); // (width, height)
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(content);
    }
}