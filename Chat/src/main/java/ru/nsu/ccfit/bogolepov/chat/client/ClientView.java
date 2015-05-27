package ru.nsu.ccfit.bogolepov.chat.client;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JFrame {

    private JTextArea messageHistoryArea;
    private JTextField messageInputField;
    private JButton listUsersButton;
    private JButton closeConnectionButton;
    private Client client;

    ClientView(Client client) {

        client.setUsername(JOptionPane.showInputDialog(this, "Username:"));

        this.client = client;
        messageHistoryArea = new JTextArea(80, 80);

        listUsersButton = new JButton("Who's online");
        listUsersButton.addActionListener(e -> client.getUsers());

        closeConnectionButton = new JButton("Close connection");
        closeConnectionButton.addActionListener(e -> {
            client.disconnect();
            System.exit(0);
        });

        JPanel upperPanel = new JPanel(new GridLayout(1, 2));
        upperPanel.add(listUsersButton);
        upperPanel.add(closeConnectionButton);
        add(upperPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 1));
        centerPanel.add(new JScrollPane(messageHistoryArea));
        messageHistoryArea.setEditable(false);
        add(centerPanel, BorderLayout.CENTER);

        messageInputField = new JTextField();
        messageInputField.addActionListener(e -> {
            client.sendMessage(messageInputField.getText());
            messageInputField.setText("");
        });
        JPanel bottomPanel = new JPanel(new GridLayout(1, 1));
        bottomPanel.add(messageInputField);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 360);
        setVisible(true);
    }

    void appendMessage(String text) {
        messageHistoryArea.append(text + "\n");
        messageHistoryArea.setCaretPosition(messageHistoryArea.getText().length() - 1);
    }
}
