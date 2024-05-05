package GameCenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    public Login(){

        LoginJFrame();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void LoginJFrame() {
        setLayout(null);
        setTitle("登录");

        JLabel userJLabel = new JLabel("用户名：");
        JLabel passJLabel = new JLabel("密码：");
        JButton loginJButton = new JButton("登录");
        JTextField loginJTextField = new JTextField();
        JPasswordField loginJPasswordField = new JPasswordField();

        userJLabel.setBounds(100,60,200,100);
        passJLabel.setBounds(100,115,200,100);
        loginJTextField.setBounds(150,100,250,30);
        loginJPasswordField.setBounds(150,150,250,30);
        loginJButton.setBounds(200,250,100,50);

        add(userJLabel);
        add(passJLabel);
        add(loginJTextField);
        add(loginJPasswordField);
        add(loginJButton);

        loginJButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(488,430);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new GameGUI();
    }
}
