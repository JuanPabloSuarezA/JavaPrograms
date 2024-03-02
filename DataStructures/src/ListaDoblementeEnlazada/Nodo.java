
package ListaDoblementeEnlazada;


public class Nodo {
    
    private Nodo anterior,siguiente;
    private int info;

    public Nodo(int info) {
        this.info = info;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
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
