package classes;

public class Client {

    JSocketClient sk;

    public Client(String address, int port) {
        sk = new JSocketClient(address, port);
    }

    public void operation(String data) {
        this.sk.request(data);
    }
}
