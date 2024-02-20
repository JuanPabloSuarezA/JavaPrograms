package main;

import classes.Client;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 1802);
        client.operation("sending message to server");
    }
}
