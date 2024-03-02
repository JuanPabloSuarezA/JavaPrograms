
package ListaEnlazada;

public class ListaEnlazadaGenerica {
    
    private NodoGenerico<Objeto> primero;
    private int tamano;

    public ListaEnlazadaGenerica() {
    }
    
    public String imprimirLista(){
        
        NodoGenerico<Objeto> aux = primero;
        int cont=1;
        String cad="";
        
        if (tamano == 0) {
            return "La lista está vacía.";
        }
        
        while (aux != null) {            
            cad += cont  + ". " + aux.info.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getSiguiente();
        }
        
        return cad;
        
    }
    
    public void agregarElementoInicial(int info,String nombre){
        Objeto objeto = new Objeto(info,nombre);
        NodoGenerico<Objeto> nodo = new NodoGenerico<>(objeto);
        tamano++;
        
        if (tamano == 1) {
            primero = nodo;
            return;
        }
        
        nodo.setSiguiente(primero);
        primero = nodo;
        

    }
    
    public void agregarElementoFinal(int info, String nombre){
        
        NodoGenerico<Objeto> aux = primero;
        Objeto objeto = new Objeto(info,nombre);
        NodoGenerico<Objeto> nodo = new NodoGenerico<>(objeto);
        tamano++;
        
        if (tamano == 1) {
            primero = nodo;
            return;
        }
         
        while (aux.getSiguiente() != null) {            
            aux = aux.getSiguiente();
        }
        
        aux.setSiguiente(nodo);
        
    }
    
    public String buscarPorIndice(int ind){
                
        NodoGenerico<Objeto> aux = primero;
                
        if (tamano == 0) {
            return "Lista vacía";  
        }        
        
        if (ind < 1 || ind > tamano) {
            return "Índice inválido";
        }
        
        for (int i = 1; i < ind; i++) {
            aux = aux.getSiguiente();
        }
        
        return "Índice " + ind + ": " + aux.info.mostrarInformacion();
    }
    
    public String buscarPrimero(){
        return  buscarPorIndice(1);
        
    }
    public String buscarUltimo(){
        return  buscarPorIndice(tamano);
    }
    
    public String buscarPorValor(int valor){
        
        NodoGenerico<Objeto> aux = primero;
        
        if (tamano == 0) {
            return "Lista vacía";
        }
        
        while (aux != null) {            
            if (aux.info.getId() == valor) {
                return "Objeto encontrado: " + aux.info.mostrarInformacion();
            }
            aux = aux.getSiguiente();
        }
        return "Ningún objeto contiene el valor";
    }
    
    public String eliminarPorIndice(int ind){
        
        NodoGenerico<Objeto> aux = primero;
        String cad;
        
        if (tamano == 0) {
            return "Lista vacía";
        }
        
        if (ind < 1 || ind > tamano) {
            return "Índice inválido";
        }       
        
        tamano--;
        
        if (ind == 1) {
            
            cad = "Objeto eliminado: " + aux.info.mostrarInformacion();
            primero = primero.getSiguiente();
            return cad;
        }
        
        for (int i = 2; i < ind; i++) {
            aux = aux.getSiguiente();
        }
        
        cad = "Objeto eliminado: " + aux.getSiguiente().info.mostrarInformacion();
        
        aux.setSiguiente(aux.getSiguiente().getSiguiente());
        
        return cad;
    }
    
    public String eliminarPorValor(int valor){
        
        NodoGenerico<Objeto> aux = primero;
        String cad;
        
        if (tamano == 0) {
            return "Lista vacía";
        }
        
        tamano--;
        
        if (aux.info.getId() == valor) {
            primero = primero.getSiguiente();
            return "Objeto eliminado: " + aux.info.mostrarInformacion();
        }
        
        while (aux.getSiguiente() != null) {        
            if (aux.getSiguiente().info.getId() == valor) {
                
                cad = "Objeto eliminado: " + aux.getSiguiente().info.mostrarInformacion();                
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                return cad;
            }
            aux = aux.getSiguiente();
        }
        tamano++;
        return "Ningún objeto contiene el valor";
        
    }
    public String eliminarPrimero(){
        return eliminarPorIndice(1);
    }
    public String eliminarUltimo(){
        return eliminarPorIndice(tamano);
    }
    
    public String calcularPromedioEdad(){
        NodoGenerico<Objeto> aux = primero;
        
        int prom = 0;
        
        while (aux != null) {            
            prom += aux.info.getId();
            aux = aux.getSiguiente();
        }
        
        prom /= tamano;
        
        return "El promedio de edad es " + prom;
    }
    
    public String calcularEdadMayor(){
        NodoGenerico<Objeto> aux = primero;
        
        int edadMayor = 0;
        
        while (aux != null) {            
            if (aux.info.getId() > edadMayor) {
                edadMayor = aux.info.getId();
            }
            aux = aux.getSiguiente();
        }
    
        return "La edad mayor es " + edadMayor;
    }
    
    public String calcularEdadMenor(){
        NodoGenerico<Objeto> aux = primero;
        
        int edadMenor = Integer.MAX_VALUE;
        
        while (aux != null) {            
            if (aux.info.getId() < edadMenor) {
                edadMenor = aux.info.getId();
            }
            aux = aux.getSiguiente();
        }
    
        return "La edad menor es " + edadMenor;
    }
    
    public void invertirLista(){
        
        if (tamano == 0) {
            return;
        }
               
        NodoGenerico<Objeto> anteriorActual = primero;        
        NodoGenerico<Objeto> actual = primero.getSiguiente();
        anteriorActual.setSiguiente(null);
        NodoGenerico<Objeto> siguienteActual;
        
        while(actual != null){
            siguienteActual = actual.getSiguiente();     
            actual.setSiguiente(anteriorActual);    
            anteriorActual = actual; 
            actual = siguienteActual;  
        }
        
        primero = anteriorActual;
        
    }
    public void vaciarLista(){
        tamano = 0;
        primero = null;
    }
    
}
