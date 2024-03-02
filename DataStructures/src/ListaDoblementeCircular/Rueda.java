package ListaDoblementeCircular;

import java.util.Random;

public class Rueda {

    private Recuadro<Premio> primero, ultimo;
    private int tamano;

    public Rueda() {
        this.tamano = 0;
        this.primero = null;
        this.ultimo = null;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public String realizarSorteo(int d) {
        String cad;
        Recuadro<Premio> elemento = primero;
        cad = primero.mostrarId() + primero.info.mostrarInformacion() + "\n";
        Random r = new Random();
        int azar = r.nextInt(21 - 7) + 7;

        for (int i = 1; i < azar; i++) {
            if (d == 1) {
                elemento = elemento.getSiguiente();
            } else {
                elemento = elemento.getAnterior();
            }

            cad += elemento.mostrarId() + elemento.info.mostrarInformacion() + "\n";
        }

        primero = elemento;
        ultimo = elemento.getAnterior();

        cad += "El número elegido al azar fue " + azar + "\n";

        cad += "Si quieres el premio " + elemento.info.getNombre()
                + " ingresa 1, o si quieres los " + elemento.info.getDinero() + " pesos ingresa 2: ";

        return cad;
    }

    public String elegirOpcion(int el) {

        if (el == 1) {
            return "Has ganado el premio " + primero.info.getNombre() + "!";
        } else {
            return "Has ganado " + primero.info.getDinero() + " pesos!";
        }
    }

    public void agregarRecuadro(int ind, Premio premio, int id) {

        Recuadro<Premio> nuevo = new Recuadro<>(premio, id);

        Recuadro<Premio> elemento = primero;

        for (int i = 2; i <= ind; i++) {
            elemento = elemento.getSiguiente();
        }
        if (ind == 1) {
            agregarElementoInicial(premio, id);

        }
        else if (ind == tamano + 1) {
            agregarElementoFinal(premio, id);

        } else {

            elemento.getAnterior().setSiguiente(nuevo); //el anterior apunta al nuevo
            nuevo.setAnterior(elemento.getAnterior()); //el nuevo apunta al anterior 
            nuevo.setSiguiente(elemento); //el nuevo apunta al que reemplaza
            elemento.setAnterior(nuevo); //el que reemplaza apunta al nuevo
        }
        tamano++;

    }

    public String eliminarRecuadro(int id) {

        String cad = "Se eliminó ";
        Recuadro<Premio> elemento = primero;

        do {
            if (elemento.getId() == id) {

                cad += elemento.info.mostrarInformacion();
                elemento.getAnterior().setSiguiente(elemento.getSiguiente());
                elemento.getSiguiente().setAnterior(elemento.getAnterior());
                if (elemento == primero) {
                    primero = elemento.getSiguiente();
                }
                if (elemento == ultimo) {
                    ultimo = elemento.getAnterior();
                }
                tamano--;
                return cad;

            }
            elemento = elemento.getSiguiente();
        } while (elemento != primero);
        return "No existe el recuadro con ese ID.";

    }

    public String modificarPremioRecuadro(int id, Premio premio) {

        String cad = "Recuadro Modificado :";
        Recuadro<Premio> elemento = primero;

        do {
            if (elemento.getId() == id) {

                elemento.setInfo(premio);
                cad += elemento.mostrarId() + elemento.info.mostrarInformacion();
                return cad;
            }
            elemento = elemento.getSiguiente();
        } while (elemento != primero);
        return "No existe el recuadro con ese ID.";

    }

    public void agregarElementoInicial(Premio premio, int id) {

        Recuadro<Premio> elemento = new Recuadro<>(premio, id);

        if (tamano > 0) {
            elemento.setSiguiente(primero);
            primero.setAnterior(elemento);
            primero = elemento;
            elemento.setAnterior(ultimo);
            ultimo.setSiguiente(primero);
        } else {
            primero = elemento;
            ultimo = elemento;
            primero.setAnterior(ultimo);
            ultimo.setSiguiente(primero);
        }
        tamano++;
    }

    public void agregarElementoFinal(Premio premio, int id) {

        Recuadro<Premio> elemento = new Recuadro<>(premio, id);

        if (tamano > 0) {
            elemento.setSiguiente(primero);
            ultimo.setSiguiente(elemento);
            elemento.setAnterior(ultimo);
            ultimo = elemento;
            primero.setAnterior(ultimo);
        } else {
            primero = elemento;
            ultimo = elemento;
            primero.setAnterior(ultimo);
            ultimo.setSiguiente(primero);
        }
        tamano++;
    }
    
    public String imprimirRueda(){
        
        String cad="";
        Recuadro<Premio> elemento = primero;
        
        do {            
            cad += elemento.mostrarId() + elemento.info.mostrarInformacion() + "\n";
            elemento = elemento.getSiguiente();
            
        } while (elemento != primero);
        return cad;
    }
}
