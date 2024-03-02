
package ListaEnlazada;

public class Nodo {
    
    private Nodo siguiente;
    private int info;

    public Nodo() {
    }

    public Nodo(int info) {
        this.info = info;
    }   
    
    public Nodo(Nodo siguiente, int info) {
        this.siguiente = siguiente;
        this.info = info;
    }
    

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public String mostrarInformacion() {
        return "Nodo{" + "info=" + info + '}';
    }

    
}
