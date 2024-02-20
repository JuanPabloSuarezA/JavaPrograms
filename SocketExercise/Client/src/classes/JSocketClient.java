package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class JSocketClient {

    private InetAddress address;
    private int port;
    private Socket clientSk;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public JSocketClient(String address, int port) {
        try {
            this.port = port;
            this.address = InetAddress.getByName(address);
            this.oos = null;
            this.ois = null;
        } catch (UnknownHostException e) {
            System.out.println("Invalid ip");
        }
    }

    public void request(String data) {
        try {
            this.clientSk = new Socket(this.address, this.port);
            this.oos = new ObjectOutputStream(this.clientSk.getOutputStream());
            this.oos.flush();
            this.ois = new ObjectInputStream(this.clientSk.getInputStream());
            System.out.println("\n [Client]: Successful connection.");
            send(data);
            while (true) {
                try {
                    data = (String) this.ois.readObject();
                    if (data == null) {
                        closeService();
                    } else {
                        System.out.println("[Client]: Server Message:{" + data + "}");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("\n [Client]: Unable to recieve data.");
                }
            }
        } catch (IOException e1) {
        }
    }

    private void send(String data) {
        try {
            this.oos.writeObject("[Client]: " + data);
            this.oos.flush();
        } catch (IOException e) {
            System.out.println("\n [Client]: Unable to send Message.");
        }
    }

    private void closeService() {
        try {
            this.ois.close();
            this.oos.close();
            this.clientSk.close();
            System.out.println("\n [Client]: Connection closed.");
        } catch (IOException e) {
            System.out.println("\n [Client]: Unable to close connection.");
        }
    }
}
