
package ListaCircular;


public class Nodo {
    private int num;
    private Nodo siguiente;

    public Nodo(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public String mostrarInformacion() {
        return "Nodo{" + "num=" + num + '}';
    }
    
}
