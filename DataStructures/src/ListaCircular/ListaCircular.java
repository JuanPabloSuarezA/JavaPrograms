package ListaCircular;

public class ListaCircular {

    private Nodo primero, ultimo;
    private int tamano;

    public ListaCircular() {
    }

    public int getTamano() {
        return tamano;
    }

    public String imprimirLista() {

        Nodo aux = primero;
        int cont = 1;
        String cad = "";

        if (tamano == 0) {
            return "La lista está vacía.";
        }

        do {
            cad += cont + ". " + aux.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getSiguiente();
        } while (aux != primero);

        return cad;

    }

    public String agregarElemento(int ind, int id) {

        Nodo nuevo = new Nodo(id);

        Nodo aux = primero;

        if (ind < 1 || ind > tamano + 1) {
            return "Índice inválido";
        }

        if (tamano == 0) {
            agregarElementoInicial(id);
            return "Éxito";
        }

        for (int i = 2; i < ind; i++) {
            aux = aux.getSiguiente(); //obtengo el anterior
        }
        if (ind == 1) {
            agregarElementoInicial(id);

        } else if (ind == tamano + 1) {
            agregarElementoFinal(id);

        } else {

            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            tamano++;
        }
        return "Éxito";
    }

    public void agregarElementoInicial(int id) {

        Nodo elemento = new Nodo(id);

        if (tamano > 0) {
            elemento.setSiguiente(primero);

            primero = elemento;
            ultimo.setSiguiente(primero);
            return;
        }

        primero = elemento;
        ultimo = elemento;
        primero.setSiguiente(ultimo);
        ultimo.setSiguiente(primero);

        tamano++;
    }

    public void agregarElementoFinal(int info) {

        Nodo elemento = new Nodo(info);

        if (tamano > 0) {
            elemento.setSiguiente(primero);
            ultimo.setSiguiente(elemento);
            ultimo = elemento;
        } else {
            primero = elemento;
            ultimo = elemento;
            ultimo.setSiguiente(primero);
        }
        tamano++;
    }

    public String buscarPorIndice(int ind) {

        Nodo aux = primero;

        if (tamano == 0) {
            return "Lista vacía";
        }

        if (ind < 1 || ind > tamano) {
            return "Índice inválido";
        }

        for (int i = 1; i < ind; i++) {
            aux = aux.getSiguiente();
        }

        return "Índice " + ind + ": " + aux.mostrarInformacion();
    }

    public String buscarPrimero() {
        return buscarPorIndice(1);

    }

    public String buscarUltimo() {
        return buscarPorIndice(tamano);
    }

    public String buscarPorValor(int valor) {

        Nodo aux = primero;

        if (tamano == 0) {
            return "Lista vacía";
        }

        do {
            if (aux.getNum() == valor) {
                return "Objeto encontrado: " + aux.mostrarInformacion();
            }
            aux = aux.getSiguiente();
        } while (aux != primero);
        return "Ningún objeto contiene el valor";
    }

    public String eliminarPorIndice(int ind) {

        Nodo aux = primero;
        String cad;

        if (tamano == 0) {
            return "Lista vacía";
        }

        if (ind < 1 || ind > tamano) {
            return "Índice inválido";
        }

        tamano--;

        if (ind == 1) {

            cad = "Objeto eliminado: " + aux.mostrarInformacion();

            if (tamano==0) {
                vaciarLista();
            } else {
                primero = primero.getSiguiente();

                ultimo.setSiguiente(primero);
            }

            return cad;
        }

        for (int i = 2; i < ind; i++) {
            aux = aux.getSiguiente(); //obtengo el penultimo
        }

        cad = "Objeto eliminado: " + aux.getSiguiente().mostrarInformacion();
        
        if (aux.getSiguiente() == ultimo) {
            ultimo = aux;
        }

        aux.setSiguiente(aux.getSiguiente().getSiguiente());

        return cad;
    }

    public String eliminarPorValor(int valor) {

        Nodo aux = primero;
        String cad;

        if (tamano == 0) {
            return "Lista vacía";
        }

        tamano--;

        if (aux.getNum() == valor) {
            primero = primero.getSiguiente();
            ultimo.setSiguiente(primero);
            return "Objeto eliminado: " + aux.getNum();
        }

        do {
            if (aux.getSiguiente().getNum() == valor) {

                cad = "Objeto eliminado: " + aux.getSiguiente().mostrarInformacion();
                
                if (aux.getSiguiente() == ultimo) {
                    ultimo = aux;
                }
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                return cad;
            }
            aux = aux.getSiguiente();
        }while (aux.getSiguiente() != primero);
        tamano++;
        return "Ningún objeto contiene el valor";

    }

    public String eliminarPrimero() {
        return eliminarPorIndice(1);
    }

    public String eliminarUltimo() {
        return eliminarPorIndice(tamano);
    }


    public void invertirLista() {

        if (tamano == 0) {
            return;
        }

        Nodo anteriorActual = primero;
        Nodo actual = primero.getSiguiente();
        anteriorActual.setSiguiente(null);
        Nodo siguienteActual;

        while (actual != primero) {
            siguienteActual = actual.getSiguiente();
            actual.setSiguiente(anteriorActual);
            anteriorActual = actual;
            actual = siguienteActual;
        }

        primero = anteriorActual;

    }

    public void vaciarLista() {
        tamano = 0;
        primero = null;
        ultimo = null;
    }

}
