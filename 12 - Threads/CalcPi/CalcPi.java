package CalcPi;

/**
 * Example 4. Pi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcPi extends JFrame {
    JTextArea display= new JTextArea(1, 20);
    JPanel buttonPanel = new JPanel(new GridLayout(3,1));
    JButton button0 = new JButton("Calculate PI");

    CalcPi() {
        super("PI Calculator");
        setBounds(300, 300, 300, 300);

        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PI pi = new PI(display);
                pi.start();
            }
        });

        setLayout(new BorderLayout());

        // Добавление элементов на форму
        add(display, BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);

        // Добавление кнопок на панель
        buttonPanel.add(new JPanel());
        buttonPanel.add(button0);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalcPi();
    }
}

