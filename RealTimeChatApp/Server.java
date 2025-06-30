import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {
    private static Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket);

                ClientHandler clientHandler = new ClientHandler(socket, clientHandlers);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Set<ClientHandler> clientHandlers;
    private String clientName;

    public ClientHandler(Socket socket, Set<ClientHandler> clientHandlers) throws IOException {
        this.socket = socket;
        this.clientHandlers = clientHandlers;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Enter your name: ");
        this.clientName = in.readLine();
        broadcast("[SERVER] " + clientName + " joined the chat.");
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                if (message.startsWith("@")) {
                    String[] parts = message.split(" ", 2);
                    if (parts.length == 2) {
                        String targetName = parts[0].substring(1);
                        String privateMsg = parts[1];
                        sendPrivateMessage(targetName, privateMsg);
                    }
                } else {
                    broadcast(formatMessage(clientName, message));
                }
            }
        } catch (IOException e) {
            System.out.println(clientName + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // ignore
            }
            clientHandlers.remove(this);
            broadcast("[SERVER] " + clientName + " left the chat.");
        }
    }

    private void broadcast(String message) {
        for (ClientHandler handler : clientHandlers) {
            handler.out.println(message);
        }
    }

    private void sendPrivateMessage(String targetName, String message) {
        boolean found = false;
        for (ClientHandler handler : clientHandlers) {
            if (handler.clientName.equalsIgnoreCase(targetName)) {
                handler.out.println("[Private] " + formatMessage(clientName, message));
                found = true;
                break;
            }
        }
        if (!found) {
            out.println("[SERVER] User '" + targetName + "' not found.");
        }
    }

    private String formatMessage(String name, String message) {
        String time = new SimpleDateFormat("hh:mm a").format(new Date());
        return "[" + time + "] " + name + ": " + message;
    }
}
