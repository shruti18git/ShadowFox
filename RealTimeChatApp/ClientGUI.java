import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClientGUI {

    private String clientName;
    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientGUI(String host, int port) {
        setupGUI();

        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = in.readLine()) != null) {
                        messageArea.append(serverMsg + "\n");
                    }
                } catch (IOException e) {
                    messageArea.append("Disconnected from server.\n");
                }
            }).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to connect to server.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void setupGUI() {
        clientName = JOptionPane.showInputDialog("Enter your name:");
        if (clientName == null || clientName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        frame = new JFrame("Chat - " + clientName);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        frame.setVisible(true);
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            out.println(message);
            if (message.equalsIgnoreCase("exit")) {
                closeConnection();
                System.exit(0);
            }
            inputField.setText("");
        }
    }

    private void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            // ignore
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI("localhost", 1234));
    }
}
