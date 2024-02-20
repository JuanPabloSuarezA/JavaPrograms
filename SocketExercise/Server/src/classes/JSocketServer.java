package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JSocketServer {

    private int port;
    private ServerSocket serverSk;
    private Socket clientSk;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public JSocketServer(int port) {
        try {
            this.port = port;
            this.serverSk = new ServerSocket(port,100);
            this.oos = null;
            this.ois = null;
        } catch (Exception e) {
            System.out.println("[Server]: Error, please try again.");
        }
    }

    public void listening() {
        try {
            String data;
            System.out.println("""          
                                [Server]: Waiting...
                               """);
            this.clientSk = this.serverSk.accept();
            this.oos = new ObjectOutputStream(this.clientSk.getOutputStream());
            this.oos.flush();
            this.ois = new ObjectInputStream(this.clientSk.getInputStream());
            System.out.println("""                           
                                [Server]: Successful connection.
                               """);
            while (true) {
                try {
                    data = (String) this.ois.readObject();
                    if (data == null) {
                        closeService();
                    } else {
                        send("Message recieved: " + data);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("\n [Server]: Unable to get client's data.");
                }
            }
        } catch (IOException e) {
        }
    }

    private void closeService() {
        try {
            this.ois.close();
            this.oos.close();
            this.clientSk.close();
            this.serverSk.close();
            System.out.println("\n [Server]: Connection closed.");
        } catch (IOException e) {
            System.out.println("\n [Server]: Unable to close connection.");
        }
    }

    private void send(String data) {
        try {
            this.oos.writeObject("[Server]: " + data);
            this.oos.flush();
        } catch (IOException e) {
            System.out.println("\n [Server]: Unable to send client's data.");
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
