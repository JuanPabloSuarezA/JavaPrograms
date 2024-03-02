package menu;

import ListaDoblementeEnlazada.ListaDobleEnlazada;
import java.util.Scanner;

public class PrincipalLDE {

    public static void main(String[] args) {

        Scanner tec;


        int opc = -1, id, num;

        ListaDobleEnlazada lista = new ListaDobleEnlazada();
        
        
        for (int i = 1; i < 15; i++) {
            lista.agregarElementoFinal(i);
        }

        while (opc != 0) {
            System.out.println("Ménu de opciones");
            System.out.println("");
            System.out.println("1.Imprimir la lista de objetos.");
            System.out.println("2.Agregar objeto al principio de la lista.");
            System.out.println("3.Agregar objeto al final de la lista.");
            System.out.println("4.Imprimir la lista al revés.");
            System.out.println("5.Buscar objeto por índice.");
            System.out.println("6.Eliminar objeto por índice.");
            System.out.println("7.Buscar primer objeto.");
            System.out.println("8.Buscar último objeto.");
            System.out.println("9.Eliminar primer objeto.");
            System.out.println("10.Eliminar último objeto.");
            System.out.println("11.Vaciar la lista.");
            System.out.println("12.Invertir la lista.");
            System.out.println("13.Mostrar siguientes por ID.");
            System.out.println("14.Mostrar anteriores por ID.");
            System.out.println("15.eliminar objeto por ID.");
            System.out.println("Nota: El primer ID es 10");

            System.out.println("");
            System.out.print("Ingresa el número de la opción deseada: ");

            tec = new Scanner(System.in);

            try {
                opc = Integer.valueOf(tec.nextLine());

                switch (opc) {

                    case 0:
                        break;

                    case 1:
                        System.out.println(lista.imprimirLista());

                        break;
                    case 2:

                        System.out.print("Ingresa el id: ");
                        id = Integer.valueOf(tec.nextLine());

                        lista.agregarElementoInicial(id);
                        id++;

                        break;

                    case 3:

                        System.out.print("Ingresa el id: ");
                        id = Integer.valueOf(tec.nextLine());

                        lista.agregarElementoFinal(id);
                        id++;
                        break;

                    case 4:
                        System.out.println(lista.imprimirReversa());
                        break;

                    case 5:
                        try {

                            System.out.print("Ingresa el índice(el primero es 1): ");
                            num = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.buscarPorIndice(num));

                        } catch (Exception e) {
                            System.out.println("No pusiste un número entero");
                        }
                        break;

                    case 6:
                        try {

                            System.out.print("Ingresa el índice(el primero es 1): ");
                            num = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.eliminarPorIndice(num));

                        } catch (Exception e) {
                            System.out.println("No pusiste un número entero");
                        }
                        break;

                    case 7:
                        System.out.println(lista.buscarPrimero());
                        break;

                    case 8:
                        System.out.println(lista.buscarUltimo());
                        break;

                    case 9:
                        System.out.println(lista.eliminarPrimero());
                        break;

                    case 10:
                        System.out.println(lista.eliminarUltimo());
                        break;

                    case 11:
                        lista.vaciarLista();
                        break;

                    case 12:
                        lista.invertirLista();
                        break;

                    case 13:
                        try {

                            System.out.print("Ingresa el id: ");
                            num = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.mostrarSiguientesPorId(num));

                        } catch (Exception e) {
                            System.out.println("No pusiste un número entero");
                        }
                        break;

                    case 14:
                        try {

                            System.out.print("Ingresa el id: ");
                            num = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.mostrarAnterioresPorId(num));

                        } catch (Exception e) {
                            System.out.println("No pusiste un número entero");
                        }
                        break;

                    case 15:
                        try {

                            System.out.print("Ingresa el id: ");
                            num = Integer.valueOf(tec.nextLine());
                            if (num > 0) {
                                System.out.println(lista.eliminarPorValor(num));
                            } else {
                                System.out.println("El número debe ser mayor a 0");
                            }
                        } catch (Exception e) {
                            System.out.println("No pusiste un número entero");
                        }
                        break;

                    default:
                        System.out.println("No elegiste ninguna opción.");
                }

                if (opc != 0 && opc != 2 && opc != 3) {
                    System.out.println("");
                    System.out.print("Oprime enter para ver el menú de nuevo");
                    tec.nextLine();
                }

            } catch (Exception e) {
                System.out.println("");
                System.out.println("Debes ingresar un número entero.");
            }

            System.out.println("");

        }
    }
}
