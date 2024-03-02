package ListaDoblementeEnlazada;

public class ListaDobleEnlazadaGenerica {

    private NodoGenerico<Objeto> primero, ultimo;
    private int tamano;

    public ListaDobleEnlazadaGenerica() {
    }

    public String imprimirLista() {

        NodoGenerico<Objeto> aux = primero;
        int cont = 1;
        String cad = "";

        if (tamano == 0) {
            return "Lista vacía";
        }

        while (aux != null) {
            cad += cont + ". " + aux.info.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getSiguiente();
        }
        return cad;
    }

    public String imprimirReversa() {

        NodoGenerico<Objeto> aux = ultimo;
        int cont = 1;
        String cad = "";

        if (tamano == 0) {
            return "Lista vacía";
        }

        while (aux != null) {
            cad += cont + ". " + aux.info.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getAnterior();
        }
        return cad;
    }

    public void agregarElementoInicial(int info) {
        
        Objeto objeto = new Objeto(info);

        NodoGenerico<Objeto> nodo = new NodoGenerico<>(objeto);
        tamano++;

        if (tamano == 1) {
            primero = nodo;
            ultimo = nodo;
            return;
        }

        nodo.setSiguiente(primero);
        primero.setAnterior(nodo);
        primero = nodo;
    }

    public void agregarElementoFinal(int info) {

        Objeto objeto = new Objeto(info);

        NodoGenerico<Objeto> nodo = new NodoGenerico<>(objeto);
        tamano++;

        if (tamano == 1) {
            primero = nodo;
            ultimo = nodo;
            return;
        }

        nodo.setAnterior(ultimo);
        ultimo.setSiguiente(nodo);
        ultimo = nodo;
    }


    public NodoGenerico<Objeto> buscarPorIndiceGeneral(int ind) {
        NodoGenerico<Objeto> aux = primero;

        if (tamano == 0 || ind < 1 || ind > tamano) {
            return null;
        }

        for (int i = 1; i < ind; i++) {
            aux = aux.getSiguiente();
        }

        return aux;

    }

    public String buscarPorIndice(int ind) {

        NodoGenerico<Objeto> aux = buscarPorIndiceGeneral(ind);

        if (tamano == 0) {
            return "Lista vacía";
        }

        if (aux == null) {
            return "Índice inválido";
        }

        return "Índice " + ind + ": " + aux.info.mostrarInformacion();
    }

    public String buscarPrimero() {
        return buscarPorIndice(1);

    }

    public String buscarUltimo() {
        return buscarPorIndice(tamano);
    }
    
    public NodoGenerico<Objeto> buscarPorValorGeneral(int id) {
        NodoGenerico<Objeto> aux = primero;

        while (aux != null) {
            if (aux.info.getId() == id) {
                return aux;
            }
            aux = aux.getSiguiente();
        }

        return null;
    }

    public String buscarPorValor(int id) {
        NodoGenerico<Objeto> aux = buscarPorValorGeneral(id);

        if (aux == null) {
            return "Objeto no encontrado";
        }

        return "Objeto encontrado: " + aux.info.mostrarInformacion();

    }

    public Boolean eliminarGeneral(NodoGenerico<Objeto> aux) {

        if (aux == null) {
            return false;
        }
        NodoGenerico<Objeto> anterior = aux.getAnterior();
        NodoGenerico<Objeto> siguiente = aux.getSiguiente();

        tamano--;

        if (tamano == 0) {
            vaciarLista();
        }
        else if (aux == primero) {
            primero = siguiente;
            primero.setAnterior(null);
            
        } else if (aux == ultimo) {
            ultimo = anterior;
            ultimo.setSiguiente(null);
        } else {
            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }
        return true;
    }

    public String eliminarPorIndice(int ind) {
        
        NodoGenerico<Objeto> aux = buscarPorIndiceGeneral(ind);
        
        if (tamano == 0) {
            return "Lista vacía";
        } 
        
        if (eliminarGeneral(aux)) {
            return "Objeto eliminado: " + aux.info.mostrarInformacion();
        }        
        return "Índice inválido";
    }

    public String eliminarPrimero() {
        return eliminarPorIndice(1);
    }

    public String eliminarUltimo() {
        return eliminarPorIndice(tamano);
    }
    
    public String eliminarPorValor(int id){
        NodoGenerico<Objeto> aux = buscarPorValorGeneral(id);
        
        if (tamano == 0) {
            return "Lista vacía";
        } 
        
        if (eliminarGeneral(aux)) {
            return "Objeto eliminado: " + aux.info.mostrarInformacion();
        }        
        return "Objeto no encontrado";
    }

    public void invertirLista() {
        
        if (tamano == 0) {
            return;
        }

        NodoGenerico<Objeto> anteriorActual = primero;
        ultimo = primero;

        NodoGenerico<Objeto> actual = primero.getSiguiente();

        primero.setSiguiente(null);
        primero.setAnterior(actual);

        NodoGenerico<Objeto> siguienteActual;

        while (actual != null) {

            siguienteActual = actual.getSiguiente(); //se guarda el cuarto

            actual.setSiguiente(anteriorActual); //el siguiente del tercero es el segundo
            actual.setAnterior(siguienteActual); //el anterior del tercero es el cuarto

            //para la siguiente ronda
            anteriorActual = actual;

            actual = siguienteActual;

        }

        primero = anteriorActual;
    }

    public String mostrarSiguientesPorId(int id) {

        NodoGenerico<Objeto> aux = buscarPorValorGeneral(id);
        String cad = "";
        int cont = 1;

        if (aux == null) {
            return "Objeto no encontrado";
        }

        while (aux.getSiguiente() != null) {

            cad += cont + ". " + aux.getSiguiente().info.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getSiguiente();
        }

        if (cad.equals("")) {
            return "No tiene siguientes";
        }
        return cad;

    }

    public String mostrarAnterioresPorId(int id) {

        NodoGenerico<Objeto> aux = buscarPorValorGeneral(id);
        String cad = "";
        int cont = 1;

        if (aux == null) {
            return "Objeto no encontrado";
        }

        while (aux.getAnterior() != null) {

            cad += cont + ". " + aux.getAnterior().info.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getAnterior();
        }

        if (cad.equals("")) {
            return "No tiene anteriores";
        }
        return cad;
    }

    public void vaciarLista() {
        tamano = 0;
        primero = null;
        ultimo = null;
    }
}
