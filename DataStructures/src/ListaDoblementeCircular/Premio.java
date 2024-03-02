/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaDoblementeCircular;

/**
 *
 * @author USER
 */
public class Premio {
    
    private String nombre;
    private int dinero;

    public Premio(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public String mostrarInformacion() {
        return "Premio{" + "nombre=" + nombre + ", dinero=" + dinero + '}';
    }

}
