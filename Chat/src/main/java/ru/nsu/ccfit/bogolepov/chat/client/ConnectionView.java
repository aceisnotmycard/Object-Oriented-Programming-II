package ru.nsu.ccfit.bogolepov.chat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Window showing fields for username, hostname and port
 */
public class ConnectionView extends JFrame {

    private JTextField hostTextField;
    private JTextField portTextField;
    private JTextField usernameTextField;
    private JButton confirmButton;

    private String host;
    private int port;
    private String username;

    public ConnectionView(Client client) {

        hostTextField = new JTextField("Host");
        portTextField = new JTextField("Port");
        usernameTextField = new JTextField("Username");
        confirmButton = new JButton("Log in");

        hostTextField.setColumns(20);
        portTextField.setColumns(20);
        usernameTextField.setColumns(20);

        confirmButton.addActionListener(e -> {
            host = hostTextField.getText();
            port = new Integer(portTextField.getText());
            username = usernameTextField.getText();
            client.start(host, port, username);
            setVisible(false);
            dispose();
        });

        add(hostTextField);
        add(portTextField);
        add(usernameTextField);
        add(confirmButton);

        setLayout(new GridLayout(4, 1));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
