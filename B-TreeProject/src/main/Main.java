package main;

import classes.ArchivoNIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static class Libro implements Comparable {

        public Libro izquierdo, derecho, padre;
        private String titulo, autor, fecha;
        private int ISBN;

        public Libro(String titulo, String autor, String fecha, int ISBN) {
            this.titulo = titulo;
            this.autor = autor;
            this.fecha = fecha;
            this.ISBN = ISBN;
        }

        @Override
        public int compareTo(Object o) {
            Libro libro2 = (Libro) o;
            return this.titulo.compareTo(libro2.titulo);
        }

        @Override
        public String toString() {
            return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", fecha=" + fecha + ", ISBN=" + ISBN + '}';
        }

    }

    static class Biblioteca {

        public Libro raiz;
        private List<Libro> librosAgregados;

        public Biblioteca() {
            this.raiz = null;
            this.librosAgregados = new ArrayList<>();
        }

        public Boolean agregarLibro(Libro libroNuevo, Libro libroActual) {

            if (raiz == null) {
                raiz = libroNuevo;
                librosAgregados.add(libroNuevo);
                return true;
            }

            if (libroActual == null) {
                libroActual = raiz;
                for (int i = 0; i < librosAgregados.size(); i++) {
                    if (libroNuevo.ISBN == librosAgregados.get(i).ISBN) {
                        return false;
                    }
                }
            }

            if (libroNuevo.compareTo(libroActual) == 0) {
                return false;
            } else if (libroNuevo.compareTo(libroActual) > 0) {

                if (libroActual.derecho == null) {
                    libroActual.derecho = libroNuevo;
                    libroNuevo.padre = libroActual;
                    librosAgregados.add(libroNuevo);
                    return true;
                } else {
                    return agregarLibro(libroNuevo, libroActual.derecho);
                }
            } else if (libroNuevo.compareTo(libroActual) < 0) {

                if (libroActual.izquierdo == null) {
                    libroActual.izquierdo = libroNuevo;
                    libroNuevo.padre = libroActual;
                    librosAgregados.add(libroNuevo);
                    return true;
                } else {
                    return agregarLibro(libroNuevo, libroActual.izquierdo);
                }
            }

            return false;
        }

        public Libro buscarLibro(String titulo, Libro libroActual) {

            if (raiz == null) {
                return null;
            }

            if (libroActual == null) {
                libroActual = raiz;
            }

            if (titulo.compareTo(libroActual.titulo) == 0) {
                return libroActual;
            } else if (titulo.compareTo(libroActual.titulo) > 0) {

                if (libroActual.derecho == null) {
                    return null;
                } else {
                    return buscarLibro(titulo, libroActual.derecho);
                }
            } else if (titulo.compareTo(libroActual.titulo) < 0) {

                if (libroActual.izquierdo == null) {
                    return null;
                } else {
                    return buscarLibro(titulo, libroActual.izquierdo);
                }
            }
            return null;
        }

        public Boolean eliminarLibro(String titulo) {

            Boolean eliminado = false;

            if (raiz == null) {
                return eliminado;
            }

            for (int i = 0; i < librosAgregados.size(); i++) {
                if (librosAgregados.get(i).titulo.equals(titulo)) {

                    if (librosAgregados.get(i) != raiz) {
                        if (librosAgregados.get(i).padre.derecho == librosAgregados.get(i)) {
                            librosAgregados.get(i).padre.derecho = null;
                        } else {
                            librosAgregados.get(i).padre.izquierdo = null;
                        }
                    }

                    librosAgregados.remove(i);
                    raiz = null;
                    eliminado = true;
                }
            }

            if (eliminado) {
                List<Libro> copyLibros = new ArrayList<>(librosAgregados);
                librosAgregados = new ArrayList<>();
                for (int i = 0; i < copyLibros.size(); i++) {
                    agregarLibro(copyLibros.get(i), raiz);
                }
            }

            return eliminado;
        }

        public String listarLibros() {
            String cad = "";
            Libro aux;
            for (int i = 0; i < librosAgregados.size(); i++) {
                aux = librosAgregados.get(i);
                cad += (i + 1) + ". Titulo: " + aux.titulo + " Autor: " + aux.autor
                        + " ISBN: " + aux.ISBN + " Fecha: " + aux.fecha + "\n";
            }
            return cad;
        }

        public String recorridoBFS() { //Método para visualizar estructura por Niveles.
            String cad = "";
            Queue<Libro> cola = new LinkedList<>();

            if (raiz != null) {
                cola.add(raiz);
                while (!cola.isEmpty()) {
                    Libro libro = cola.poll(), aux;
                    int contadorNivel = 1;

                    aux = libro.padre;
                    while (aux != null) {
                        contadorNivel++;
                        aux = aux.padre;
                    }
                    cad += "Nivel " + contadorNivel + " -->" + libro.toString() + " Padre: " + libro.padre + "\n";
                    if (libro.izquierdo != null) {
                        cola.add(libro.izquierdo);
                    }
                    if (libro.derecho != null) {
                        cola.add(libro.derecho);
                    }
                }
            }
            return cad;
        }
    }

    static class Colegio {

        private int idColegio;
        private String nombreColegio;
        private Biblioteca biblioteca;

        public Colegio(int idColegio, String nombreColegio) {
            this.idColegio = idColegio;
            this.nombreColegio = nombreColegio;
            this.biblioteca = new Biblioteca();
        }

        @Override
        public String toString() {
            return "Colegio{" + "ID=" + idColegio + ", Nombre=" + nombreColegio + '}';
        }

    }

    static class Pagina {

        private Pagina padre;
        private Colegio[] colegios; //conjunto de valores
        private Pagina[] paginas; //referencias a otras paginas
        private int cantColegios;

        public Pagina(int grado) {
            this.colegios = new Colegio[grado * 2 + 1];
            this.paginas = new Pagina[grado * 2 + 3];
            this.cantColegios = 0;
            this.padre = null;
        }

        public Pagina(int grado, Pagina padre) {
            this.padre = padre;
            this.colegios = new Colegio[grado * 2 + 1];
            this.paginas = new Pagina[grado * 2 + 3];
            this.cantColegios = 0;
        }

        public int getCantColegios() {
            return cantColegios;
        }

        public void setCantColegios(int cantColegios) {
            this.cantColegios = cantColegios;
        }

        public Pagina getPadre() {
            return padre;
        }

        public void setPadre(Pagina padre) {
            this.padre = padre;
        }

        public Colegio[] getColegios() {
            return colegios;
        }

        public void setColegios(Colegio[] colegios) {
            this.colegios = colegios;
        }

        public Pagina[] getPaginas() {
            return paginas;
        }

        public void setPaginas(Pagina[] paginas) {
            this.paginas = paginas;
        }
    }

    static class ArbolB {

        private Pagina raiz;
        private int grado;
        private List<Colegio> colegiosAgregados;

        public ArbolB(int grado) {
            this.raiz = new Pagina(grado);
            this.grado = grado;
            this.colegiosAgregados = new ArrayList<>();
        }

        //Busqueda
        public Colegio buscarColegio(int idColegio, Pagina pagActual) {
            Colegio colegioAux;

            for (int i = 0; i < pagActual.cantColegios; i++) {
                colegioAux = pagActual.colegios[i];
                if (colegioAux.idColegio == idColegio) {
                    return colegioAux;
                }
                if (i == pagActual.cantColegios - 1 && pagActual.paginas[0] != null) {

                    for (int j = 0; j < pagActual.cantColegios; j++) {

                        if (j == 0 && idColegio < pagActual.colegios[j].idColegio) {
                            return buscarColegio(idColegio, pagActual.paginas[j]);
                        }

                        if (pagActual.cantColegios == 1) { //Caso especial
                            return buscarColegio(idColegio, pagActual.paginas[j + 1]);
                        }

                        if (idColegio > pagActual.colegios[j].idColegio
                                && idColegio < pagActual.colegios[j + 1].idColegio) {
                            return buscarColegio(idColegio, pagActual.paginas[j + 1]);
                        }

                        if (j == pagActual.cantColegios - 2
                                && idColegio > pagActual.colegios[j + 1].idColegio) {
                            return buscarColegio(idColegio, pagActual.paginas[j + 2]);
                        }
                    }
                }
            }
            return null;
        }

        //Ordenar claves de colegio
        public void ordenarIDcolegios(Pagina pagina) {

            Colegio aux;

            for (int i = 0; i < pagina.cantColegios; i++) {
                for (int j = 0; j < pagina.cantColegios - 1; j++) {
                    if (pagina.colegios[j].idColegio > pagina.colegios[j + 1].idColegio) {
                        aux = pagina.colegios[j];
                        pagina.colegios[j] = pagina.colegios[j + 1];
                        pagina.colegios[j + 1] = aux;
                    }
                }
            }
        }

        //Ordenar referencias a paginas
        public void ordenarPaginas(Pagina pagina) {

            Pagina aux;
            int cantPaginas = 0;

            for (int i = 0; i < pagina.paginas.length; i++) {
                if (pagina.paginas[i] != null) {
                    cantPaginas++;
                } else {
                    break;
                }
            }

            for (int i = 0; i < cantPaginas; i++) {
                for (int j = 0; j < cantPaginas - 1; j++) {
                    if (pagina.paginas[j].colegios[0].idColegio > pagina.paginas[j + 1].colegios[0].idColegio) {
                        aux = pagina.paginas[j];
                        pagina.paginas[j] = pagina.paginas[j + 1];
                        pagina.paginas[j + 1] = aux;
                    }
                }
            }
        }

        //Inserción
        public Boolean agregarColegio(Colegio colegio, Pagina pagActual) {
            if (pagActual == raiz) {
                for (int i = 0; i < colegiosAgregados.size(); i++) { //Para impedir colegios con mismo id                   
                    if (colegiosAgregados.get(i).idColegio == colegio.idColegio) {
                        return false;
                    }
                }
            }

            if (pagActual.paginas[0] == null) {//Si es una hoja

                for (int i = 0; i < pagActual.colegios.length; i++) {
                    if (pagActual.colegios[i] == null) {
                        pagActual.colegios[i] = colegio;
                        colegiosAgregados.add(colegio);
                        pagActual.setCantColegios(pagActual.cantColegios + 1);
                        ordenarIDcolegios(pagActual);
                        break;
                    }
                }

                if (pagActual.cantColegios == grado * 2 + 1) {//división      
                    Boolean exitoRetraso = false;

                    //Intento de retrasar la división
                    Pagina padre = pagActual.padre;
                    int numeroPagina = -1;
                    if (padre != null) {
                        for (int i = 0; i < padre.paginas.length; i++) {
                            if (padre.paginas[i] == pagActual) {
                                numeroPagina = i;
                            }
                        }
                        if (numeroPagina > 0) { //tiene hermano izquierdo
                            Pagina hermanoIzquierdo = padre.paginas[numeroPagina - 1];
                            //Comprobación de espacio en su hermano izquierdo

                            if (hermanoIzquierdo.cantColegios < grado * 2) {
                                exitoRetraso = true;

                                Colegio colegioMenor = pagActual.colegios[0];

                                Colegio colegioDelPadreAreemplazar = padre.colegios[numeroPagina - 1];

                                padre.colegios[numeroPagina - 1] = colegioMenor;

                                for (int i = 0; i < hermanoIzquierdo.colegios.length; i++) { //agregamos el colegio al hermano
                                    if (hermanoIzquierdo.colegios[i] == null) {
                                        hermanoIzquierdo.colegios[i] = colegioDelPadreAreemplazar;
                                        hermanoIzquierdo.setCantColegios(hermanoIzquierdo.cantColegios + 1);
                                        ordenarIDcolegios(hermanoIzquierdo);
                                        break;
                                    }
                                }

                                //Elimino el colegio del que se iba a dividir
                                pagActual.colegios[0] = pagActual.colegios[pagActual.cantColegios - 1];
                                pagActual.colegios[pagActual.cantColegios - 1] = null;
                                pagActual.setCantColegios(pagActual.cantColegios - 1);
                                ordenarIDcolegios(pagActual);

                            }

                        }

                        if (padre.paginas[numeroPagina + 1] != null && !exitoRetraso) {//tiene hermano derecho
                            Pagina hermanoDerecho = padre.paginas[numeroPagina + 1];
                            //Comprobación de espacio en su hermano derecho

                            if (hermanoDerecho.cantColegios < grado * 2) {
                                exitoRetraso = true;

                                Colegio colegioMayor = pagActual.colegios[grado * 2];

                                Colegio colegioDelPadreAreemplazar = padre.colegios[numeroPagina];

                                padre.colegios[numeroPagina] = colegioMayor;

                                for (int i = 0; i < hermanoDerecho.colegios.length; i++) { //agregamos el colegio al hermano
                                    if (hermanoDerecho.colegios[i] == null) {
                                        hermanoDerecho.colegios[i] = colegioDelPadreAreemplazar;
                                        hermanoDerecho.setCantColegios(hermanoDerecho.cantColegios + 1);
                                        ordenarIDcolegios(hermanoDerecho);
                                        break;
                                    }
                                }

                                //Elimino el colegio del que se iba a dividir
                                pagActual.colegios[grado * 2] = null;
                                pagActual.setCantColegios(pagActual.cantColegios - 1);
                            }

                        }

                    }

                    if (!exitoRetraso) {
                        divisionPagina(pagActual);
                    }
                }
                return true;

            } else {

                int idColegio = colegio.idColegio;

                for (int j = 0; j < pagActual.cantColegios; j++) {

                    if (j == 0 && idColegio < pagActual.colegios[j].idColegio) {
                        return agregarColegio(colegio, pagActual.paginas[j]);
                    }

                    if (pagActual.cantColegios == 1) { //Caso especial
                        return agregarColegio(colegio, pagActual.paginas[j + 1]);
                    }

                    if (idColegio > pagActual.colegios[j].idColegio
                            && idColegio < pagActual.colegios[j + 1].idColegio) {
                        return agregarColegio(colegio, pagActual.paginas[j + 1]);
                    }

                    if (j == pagActual.cantColegios - 2
                            && idColegio > pagActual.colegios[j + 1].idColegio) {
                        return agregarColegio(colegio, pagActual.paginas[j + 2]);
                    }
                }
            }
            return false;

        }

        //División de inserción
        public void divisionPagina(Pagina pagina) {

            Colegio[] colegios = pagina.colegios;
            Colegio medio;
            Pagina paginaIzquierda = new Pagina(grado), paginaDerecha = new Pagina(grado);

            if (pagina.cantColegios > grado * 2) {

                medio = colegios[grado];

                //*****Para todos los casos***************************
                for (int i = 0; i < grado; i++) { //Agregar las mitades a los nuevos hijos
                    paginaIzquierda.colegios[i] = pagina.colegios[i];
                    paginaIzquierda.setCantColegios(paginaIzquierda.cantColegios + 1);

                    paginaDerecha.colegios[i] = pagina.colegios[i + grado + 1];
                    paginaDerecha.setCantColegios(paginaDerecha.cantColegios + 1);
                }

                //Dejar solo el medio en la pagina
                pagina.setColegios(new Colegio[grado * 2 + 1]);
                pagina.colegios[0] = medio;
                pagina.setCantColegios(1);

                //**************************************************
                if (pagina.paginas[0] != null) { //cuando tiene hijos             

                    //tengo las pagIz y pagDerecha generadas
                    //las referencias las tiene la pagina con solo una clave
                    //tomar referencias de la pagina y apuntarlas a pagIZ y pag Der
                    for (int i = 0; i < grado + 1; i++) { //Agregar la mitad de las referencias a pagina nueva
                        paginaIzquierda.paginas[i] = pagina.paginas[i]; // de 0 a 3
                        pagina.paginas[i].setPadre(paginaIzquierda);
                        paginaDerecha.paginas[i] = pagina.paginas[i + grado + 1]; // de 4 a 7
                        pagina.paginas[i + grado + 1].setPadre(paginaDerecha);
                    }

                    for (int i = 0; i < pagina.paginas.length; i++) {//elimino las referencias de la pagina
                        pagina.paginas[i] = null;
                    }
                }

                if (pagina.padre == null) { //cuando no tiene padre es la raiz

                    //referencia al padre
                    paginaIzquierda.setPadre(pagina);
                    paginaDerecha.setPadre(pagina);

                    //referencia a los hijos nuevos
                    pagina.paginas[0] = paginaIzquierda;
                    pagina.paginas[1] = paginaDerecha;

                } else {
                    Pagina padre = pagina.padre;
                    for (int i = 0; i < padre.colegios.length; i++) {
                        if (padre.colegios[i] == null) {
                            padre.colegios[i] = medio;
                            padre.setCantColegios(padre.cantColegios + 1);
                            ordenarIDcolegios(padre);
                            break;
                        }
                    }

                    //referencia al padre
                    paginaIzquierda.setPadre(padre);
                    paginaDerecha.setPadre(padre);

                    //referencia a los hijos
                    for (int i = 0; i < padre.paginas.length; i++) {
                        if (padre.paginas[i] == pagina) { //eliminamos la referencia a la pagina dividida
                            padre.paginas[i] = paginaIzquierda;
                        }
                        if (padre.paginas[i] == null) {

                            padre.paginas[i] = paginaDerecha;
                            break;
                        }
                    }
                    ordenarPaginas(padre); //ordenamos las paginas segun las claves de colegio

                    if (padre.cantColegios == grado * 2 + 1) {//división
                        divisionPagina(padre); //Recursividad
                    }
                }
            }
        }

        public Boolean eliminarColegio(int idColegio) {
            Boolean eliminado = false;
            for (int i = 0; i < colegiosAgregados.size(); i++) {
                if (colegiosAgregados.get(i).idColegio == idColegio) {
                    eliminado = true;
                    colegiosAgregados.remove(i);
                }
            }
            List<Colegio> copiaColegiosAgregados = new ArrayList<>(colegiosAgregados);
            colegiosAgregados = new ArrayList<>();

            if (eliminado) { //Crear el arbol de nuevo 
                raiz = new Pagina(grado);

                for (int i = 0; i < copiaColegiosAgregados.size(); i++) {

                    agregarColegio(copiaColegiosAgregados.get(i), raiz);
                }
            }

            return eliminado;
        }

        public String listarColegios() {
            String cad = "";
            Colegio aux;
            for (int i = 0; i < colegiosAgregados.size(); i++) {
                aux = colegiosAgregados.get(i);
                cad += (i + 1) + ". Nombre: " + aux.nombreColegio + " ID: " + aux.idColegio + "\n";
            }
            return cad;
        }

        public String recorridoBFS() { //Método para visualizar estructura por Niveles.
            String cad = "";
            Queue<Pagina> cola = new LinkedList<>();
            cola.add(raiz);
            while (!cola.isEmpty()) {
                Pagina pagina = cola.poll(), aux;
                int contadorNivel = 1;
                aux = pagina.padre;
                while (aux != null) {
                    contadorNivel++;
                    aux = aux.padre;
                }
                cad += "Nivel " + contadorNivel + " -->" + Arrays.toString(pagina.colegios) + "\n";
                for (int i = 0; i < pagina.paginas.length; i++) {
                    if (pagina.paginas[i] != null) {
                        cola.add(pagina.paginas[i]);
                    }
                }
            }
            return cad;
        }
    }

    public static void main(String[] args) throws IOException {
        ArchivoNIO objArchivo = new ArchivoNIO("colegios.txt");
        Scanner teclado;
        int opc = -1, isbn, idColegio;
        String titulo, autor, fecha, nombreColegio, lista, lineaTXT;
        Colegio colegio;
        Libro libro;
        ArbolB arbolB = new ArbolB(3);
        /*El grado del árbol es 3 porque
                                         las páginas tienen máximo 6 claves*/

 /*Formato de cada linea de colegios.txt:
        
          IDcolegio|nombreColegio|tituloLibro1|ISBNLibro1|AUTORLibro1|FECHALibro1|tituloLibro2.....
        
        -Para cada colegio se agregan varios libros al lado derecho.
         */
        while (opc != 0) {
            System.out.println("Menú");
            System.out.println("");
            System.out.println("1.Agregar colegio.");
            System.out.println("2.Eliminar colegio.");
            System.out.println("3.Buscar colegio por id.");
            System.out.println("4.Listar colegios existentes.");
            System.out.println("5.Agregar libro en un colegio específico.");
            System.out.println("6.Eliminar libro en un colegio específico.");
            System.out.println("7.Buscar libro en un colegio específico.");
            System.out.println("8.Listar libros de un colegio específico.");
            System.out.println("9.Generar Árbol-B a partir de un archivo de texto."
                    + "(Según los datos de colegios.txt)");
            System.out.println("10.Eliminar todos los colegios (incluyendo colegios.txt).");
            System.out.println("11.Imprimir Niveles del Árbol-B de los colegios.");
            System.out.println("");

            teclado = new Scanner(System.in);
            try {
                System.out.print("Ingresa el número de la opción deseada: ");
                opc = Integer.valueOf(teclado.nextLine());
                System.out.println("");

                switch (opc) {
                    case 0:
                        System.out.println("Cerrando menú...");
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case 1:
                        try {
                            System.out.print("Ingresa el nombre del colegio: ");
                            nombreColegio = teclado.nextLine();
                            if (nombreColegio.contains("|")) {
                                System.out.println("| es un caracter prohibido!");
                                break;
                            }
                            if (nombreColegio.equals("")) {
                                System.out.println("Debes ingresar un nombre para el colegio.");
                                break;
                            }
                            System.out.print("Ingresa el id del colegio: ");
                            idColegio = Integer.valueOf(teclado.nextLine());
                            colegio = new Colegio(idColegio, nombreColegio);
                            if (arbolB.agregarColegio(colegio, arbolB.raiz)) {
                                System.out.println("Colegio agregado con éxito.");

                                lineaTXT = colegio.idColegio + "|" + colegio.nombreColegio + "|";
                                objArchivo.borrarFilaIDColegio(idColegio); //Para evitar repetidos
                                objArchivo.agregarFila(lineaTXT);
                            } else {
                                System.out.println("El ID ya existe.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Ingresa el id del colegio que deseas eliminar: ");
                            idColegio = Integer.valueOf(teclado.nextLine());

                            if (arbolB.eliminarColegio(idColegio)) {
                                System.out.println("Colegio eliminado.");
                                objArchivo.borrarFilaIDColegio(idColegio);
                            } else {
                                System.out.println("Colegio no encontrado.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Ingresa el id del colegio que deseas buscar: ");
                            idColegio = Integer.valueOf(teclado.nextLine());
                            colegio = arbolB.buscarColegio(idColegio, arbolB.raiz);
                            if (colegio != null) {
                                System.out.println("Colegio encontrado.");
                                System.out.println(colegio.toString());
                            } else {
                                System.out.println("Colegio no encontrado.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }
                        break;

                    case 4:
                        lista = arbolB.listarColegios();
                        if (!lista.equals("")) {
                            System.out.println(lista);
                        } else {
                            System.out.println("No hay colegios.");
                        }
                        break;

                    case 5:
                        try {
                            System.out.print("Ingresa el id del colegio en donde vas a agregar el libro: ");
                            idColegio = Integer.valueOf(teclado.nextLine());
                            colegio = arbolB.buscarColegio(idColegio, arbolB.raiz);
                            if (colegio != null) {
                                System.out.print("Ingresa el titulo del libro: ");
                                titulo = teclado.nextLine();

                                System.out.print("Ingresa el autor del libro: ");
                                autor = teclado.nextLine();
                                System.out.print("Ingresa la fecha de publicación del libro: ");
                                fecha = teclado.nextLine();
                                System.out.print("Ingresa el código ISBN del libro: ");
                                isbn = Integer.valueOf(teclado.nextLine());

                                libro = new Libro(titulo, autor, fecha, isbn);

                                if (titulo.contains("|") || autor.contains("|") || fecha.contains("|")) {
                                    System.out.println("| es un caracter prohibido!");
                                    break;
                                }

                                if (titulo.equals("") || autor.equals("") || fecha.equals("")) {
                                    System.out.println("Todos los valores del libro deben ser ingresados.");
                                    break;
                                }

                                if (colegio.biblioteca.agregarLibro(libro, null)) {
                                    System.out.println("Libro agregado con éxito.");

                                    lineaTXT = colegio.idColegio + "|" + colegio.nombreColegio + "|";

                                    for (int i = 0; i < colegio.biblioteca.librosAgregados.size(); i++) {
                                        libro = colegio.biblioteca.librosAgregados.get(i);
                                        lineaTXT += libro.titulo + "|" + libro.ISBN + "|"
                                                + libro.autor + "|" + libro.fecha + "|";
                                    }
                                    objArchivo.reemplazarFilaIDColegio(idColegio, lineaTXT);
                                } else {
                                    System.out.println("Este libro ya existe o su código ISBN está repetido.");
                                }
                            } else {
                                System.out.println("Colegio no encontrado.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }
                        break;
                    case 6:
                        try {
                            System.out.print("Ingresa el id del colegio en donde vas a eliminar el libro: ");
                            idColegio = Integer.valueOf(teclado.nextLine());
                            colegio = arbolB.buscarColegio(idColegio, arbolB.raiz);
                            if (colegio != null) {
                                System.out.print("Ingresa el titulo del libro que quieres eliminar: ");
                                titulo = teclado.nextLine();

                                if (titulo.contains("|")) {
                                    System.out.println("| es un caracter prohibido!");
                                    break;
                                }

                                if (colegio.biblioteca.eliminarLibro(titulo)) {
                                    System.out.println("Libro eliminado.");

                                    lineaTXT = colegio.idColegio + "|" + colegio.nombreColegio + "|";

                                    for (int i = 0; i < colegio.biblioteca.librosAgregados.size(); i++) {
                                        libro = colegio.biblioteca.librosAgregados.get(i);
                                        lineaTXT += libro.titulo + "|" + libro.ISBN + "|"
                                                + libro.autor + "|" + libro.fecha + "|";
                                    }
                                    objArchivo.reemplazarFilaIDColegio(idColegio, lineaTXT);
                                } else {
                                    System.out.println("Libro no encontrado.");
                                }
                            } else {
                                System.out.println("Colegio no encontrado.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }

                        break;

                    case 7:
                        try {
                            System.out.print("Ingresa el id del colegio en donde vas a buscar el libro: ");
                            idColegio = Integer.valueOf(teclado.nextLine());
                            colegio = arbolB.buscarColegio(idColegio, arbolB.raiz);
                            if (colegio != null) {
                                System.out.print("Ingresa el título del libro que quieres buscar: ");
                                titulo = teclado.nextLine();
                                libro = colegio.biblioteca.buscarLibro(titulo, null);
                                if (libro != null) {
                                    System.out.println("Libro encontrado.");
                                    System.out.println(libro.toString());
                                } else {
                                    System.out.println("Libro no encontrado.");
                                }
                            } else {
                                System.out.println("Colegio no encontrado.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }
                        break;

                    case 8:
                        try {
                            System.out.print("Ingresa el id del colegio en donde vas a listar los libros: ");
                            idColegio = Integer.valueOf(teclado.nextLine());
                            colegio = arbolB.buscarColegio(idColegio, arbolB.raiz);
                            if (colegio != null) {
                                lista = colegio.biblioteca.listarLibros();
                                if (!lista.equals("")) {
                                    System.out.println(lista);
                                } else {
                                    System.out.println("No hay libros.");
                                }
                            } else {
                                System.out.println("Colegio no encontrado.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }
                        break;

                    case 9:
                        arbolB = new ArbolB(3); //Reinicio el árbol para generarlo desde cero
                        List<String[]> listaDeLineas = objArchivo.obtenerContenidoArchivo();
                        String[] linea;

                        if (listaDeLineas.isEmpty()) {
                            System.out.println("No hay información en el archivo de texto.");
                        }

                        /*Formato de cada linea del txt:
                       IDcolegio|nombreColegio|tituloLibro1|ISBNLibro1|AUTORLibro1|FECHALibro1|tituloLibro2.....
                         */
                        for (int i = 0; i < listaDeLineas.size(); i++) {
                            linea = listaDeLineas.get(i);

                            idColegio = Integer.valueOf(linea[0]);
                            nombreColegio = linea[1];
                            colegio = new Colegio(idColegio, nombreColegio);
                            for (int j = 2; j < linea.length - 3; j = j + 4) {
                                titulo = linea[j];
                                isbn = Integer.valueOf(linea[j + 1]);
                                autor = linea[j + 2];
                                fecha = linea[j + 3];
                                libro = new Libro(titulo, autor, fecha, isbn);
                                colegio.biblioteca.agregarLibro(libro, null);
                            }
                            arbolB.agregarColegio(colegio, arbolB.raiz);
                        }

                        break;

                    case 10:
                        arbolB = new ArbolB(3);
                        objArchivo.reset();
                        break;

                    case 11:
                        System.out.println(arbolB.recorridoBFS());
                        break;

                    default:
                        System.out.println("No ingresaste ninguna de las "
                                + "opciones disponibles.");
                        break;

                }
            } catch (Exception e) {
                System.out.println("No ingresaste un número.");
            }
            System.out.println("");

        }

    }
}
