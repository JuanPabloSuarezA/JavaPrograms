
package ListaDoblementeEnlazada;


public class NodoGenerico<T> {
    
    
    public NodoGenerico<T>  anterior,siguiente;
    public T info;

    public NodoGenerico(T info) {
        this.info = info;
    }

    public NodoGenerico<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoGenerico<T> anterior) {
        this.anterior = anterior;
    }

    public NodoGenerico<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGenerico<T> siguiente) {
        this.siguiente = siguiente;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
    
}
