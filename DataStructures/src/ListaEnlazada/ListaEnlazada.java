
package ListaEnlazada;

public class ListaEnlazada {
    
    private Nodo primero;
    private int tamano;

    public ListaEnlazada() {
    }
    
    public String imprimirLista(){
        
        Nodo aux = primero;
        int cont=1;
        String cad="";
        
        if (tamano == 0) {
            return "La lista está vacía.";
        }
        
        while (aux != null) {            
            cad += cont  + ". " + aux.mostrarInformacion() + "\n";
            cont++;
            aux = aux.getSiguiente();
        }
        
        return cad;
        
    }
    
    public void agregarElementoInicial(int info){
        Nodo nodo = new Nodo(info);
        tamano++;
        
        if (tamano == 1) {
            primero = nodo;
            return;
        }
        
        nodo.setSiguiente(primero);
        primero = nodo;
        

    }
    
    public void agregarElementoFinal(int info){
        Nodo nodo = new Nodo(info);
        Nodo aux = primero;
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
    
    public String buscarPrimero(){
        return  buscarPorIndice(1);
        
    }
    public String buscarUltimo(){
        return buscarPorIndice(tamano);
    }
    
    public String buscarPorValor(int valor){
        
        Nodo aux = primero;
        
        if (tamano == 0) {
            return "Lista vacía";
        }
        
        while (aux != null) {            
            if (aux.getInfo() == valor) {
                return "Objeto encontrado: " + aux.mostrarInformacion();
            }
            aux = aux.getSiguiente();
        }
        return "Ningún objeto contiene el valor";
    }
    
    public String eliminarPorIndice(int ind){
        
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
            primero = primero.getSiguiente();
            return cad;
        }
        
        for (int i = 2; i < ind; i++) {
            aux = aux.getSiguiente();
        }
        
        cad = "Objeto eliminado: " + aux.getSiguiente().mostrarInformacion();
        
        aux.setSiguiente(aux.getSiguiente().getSiguiente());
        
        return cad;
    }
    
    public String eliminarPorValor(int valor){
        
        Nodo aux = primero;
        String cad;
        
        if (tamano == 0) {
            return "Lista vacía";
        }
        
        tamano--;
        
        if (aux.getInfo() == valor) {
            primero = primero.getSiguiente();
            return "Objeto eliminado: " + aux.getInfo();
        }
        
        while (aux.getSiguiente() != null) {        
            if (aux.getSiguiente().getInfo() == valor) {
                
                cad = "Objeto eliminado: " + aux.getSiguiente().mostrarInformacion();                
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
        Nodo aux = primero;
        
        int prom = 0;
        
        while (aux != null) {            
            prom += aux.getInfo();
            aux = aux.getSiguiente();
        }
        
        prom /= tamano;
        
        return "El promedio de edad es " + prom;
    }
    
    public String calcularEdadMayor(){
        Nodo aux = primero;
        
        int edadMayor = 0;
        
        while (aux != null) {            
            if (aux.getInfo() > edadMayor) {
                edadMayor = aux.getInfo();
            }
            aux = aux.getSiguiente();
        }
    
        return "La edad mayor es " + edadMayor;
    }
    
    public String calcularEdadMenor(){
        Nodo aux = primero;
        
        int edadMenor = Integer.MAX_VALUE;
        
        while (aux != null) {            
            if (aux.getInfo() < edadMenor) {
                edadMenor = aux.getInfo();
            }
            aux = aux.getSiguiente();
        }
    
        return "La edad menor es " + edadMenor;
    }
    
    public void invertirLista(){
        
        if (tamano == 0) {
            return;
        }
               
        Nodo anteriorActual = primero;        
        Nodo actual = primero.getSiguiente();
        anteriorActual.setSiguiente(null);
        Nodo siguienteActual;
        
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
