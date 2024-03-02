
package ListaEnlazada;

public class NodoGenerico<T> {
    
    public NodoGenerico<T> siguiente;
    public T info;

    public NodoGenerico(T info) {
        this.info = info;
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

    public String mostrarInformacion() {
        return "NodoGenerico{" + "info=" + info + '}';
    }

}
