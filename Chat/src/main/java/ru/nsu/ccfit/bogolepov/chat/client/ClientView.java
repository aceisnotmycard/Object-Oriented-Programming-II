package ru.nsu.ccfit.bogolepov.chat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class ClientView extends JFrame {

    private JTextArea messageHistoryArea;
    private JTextField messageInputField;
    private Client client;

    ClientView(Client client) {
        this.client = client;
        messageHistoryArea = new JTextArea(80, 80);
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
        messageHistoryArea.append(text);
        messageHistoryArea.setCaretPosition(messageHistoryArea.getText().length() - 1);
    }
}
