package view;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {
    private JTextField textField1;
    private JPanel mainPanel;
    private JButton button1;

    public HomeView() throws HeadlessException {
        add(mainPanel);
        setVisible(true);
    }
}
