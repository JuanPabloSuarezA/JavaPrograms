
package ListaDoblementeCircular;

public class Recuadro<Premio>{
    public Premio info;
    public Recuadro<Premio> anterior;
    public Recuadro<Premio> siguiente;
    public int id;

    public Recuadro(Premio info, int id) {
        this.info = info;
        this.anterior = null;
        this.siguiente = null;
        this.id = id;
    }

    public Recuadro(Premio info, Recuadro<Premio> anterior, int id) {
        this.info = info;
        this.anterior = anterior;
        this.siguiente = null;
        this.id = id;
    }


    public Recuadro(Premio info, Recuadro<Premio> anterior, Recuadro<Premio> siguiente, int id) {
        this.info = info;
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.id = id;
    }

    public Premio getInfo() {
        return info;
    }

    public void setInfo(Premio info) {
        this.info = info;
    }

    public Recuadro<Premio> getAnterior() {
        return anterior;
    }

    public void setAnterior(Recuadro<Premio> anterior) {
        this.anterior = anterior;
    }

    public Recuadro<Premio> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Recuadro<Premio> siguiente) {
        this.siguiente = siguiente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String mostrarId(){
        return "Id: " + getId() + " ";
    }
    
    
    
}
