
package ListaDoblementeEnlazada;

public class Objeto {
    
    private int id;
    private String nombre;

    public Objeto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String mostrarInformacion() {
        return "Objeto{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
}
