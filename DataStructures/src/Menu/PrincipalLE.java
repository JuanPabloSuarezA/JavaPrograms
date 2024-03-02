package Menu;

import ListaEnlazada.ListaEnlazada;
import java.util.Scanner;

public class PrincipalLE {

    public static void main(String[] args) {

        Scanner tec;

        int opc = -1, edad, ind;

        ListaEnlazada lista = new ListaEnlazada();

        while (opc != 0) {
            System.out.println("Ménu de opciones");
            System.out.println("");
            System.out.println("1.Imprimir la lista de objetos.");
            System.out.println("2.Agregar objeto al principio de la lista.");
            System.out.println("3.Agregar objeto al final de la lista.");
            System.out.println("4.Buscar objeto por índice.");
            System.out.println("5.Buscar objeto por valor.");
            System.out.println("6.Eliminar objeto por índice.");
            System.out.println("7.Eliminar objeto por valor.");
            System.out.println("8.Buscar el primer objeto.");
            System.out.println("9.Buscar el último objeto.");
            System.out.println("10.Eliminar el primer objeto.");
            System.out.println("11.Eliminar el último objeto.");
            System.out.println("12.Calcular el promedio de edad de los objetos.");
            System.out.println("13.Ver la mayor edad.");
            System.out.println("14.Ver la menor edad.");
            System.out.println("15.Invertir la lista de objetos.");
            System.out.println("16.Vaciar la lista de objetos.");

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
                        try {
                            System.out.print("Ingresa la edad: ");
                            edad = Integer.valueOf(tec.nextLine());

                            lista.agregarElementoInicial(edad);

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número entero.");
                        }

                        break;

                    case 3:

                        try {
                            System.out.print("Ingresa la edad: ");
                            edad = Integer.valueOf(tec.nextLine());

                            lista.agregarElementoFinal(edad);

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número entero.");
                        }

                        break;
                    case 4:

                        try {
                            System.out.println("Ten en cuenta que el índice "
                                    + "del primer nodo es 1.");
                            System.out.print("Ingresa el índice: ");
                            ind = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.buscarPorIndice(ind));

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número entero.");
                        }

                        break;
                    case 5:

                        try {
                            System.out.print("Ingresa la edad: ");
                            edad = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.buscarPorValor(edad));

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número entero.");
                        }
                        break;

                    case 6:

                        try {
                            System.out.println("Ten en cuenta que el índice del "
                                    + "primer objeto es 1.");
                            System.out.print("Ingresa el índice: ");
                            ind = Integer.valueOf(tec.nextLine());
                            System.out.println(lista.eliminarPorIndice(ind));

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número entero.");
                        }

                        break;

                    case 7:

                        try {
                            System.out.print("Ingresa la edad: ");
                            edad = Integer.valueOf(tec.nextLine());

                            System.out.println(lista.eliminarPorValor(edad));

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número entero.");
                        }
                        break;

                    case 8:
                        System.out.println(lista.buscarPrimero());
                        break;

                    case 9:
                        System.out.println(lista.buscarUltimo());
                        break;

                    case 10:
                        System.out.println(lista.eliminarPrimero());
                        break;

                    case 11:
                        System.out.println(lista.eliminarUltimo());
                        break;

                    case 12:
                        System.out.println(lista.calcularPromedioEdad());
                        break;

                    case 13:
                        System.out.println(lista.calcularEdadMayor());
                        break;

                    case 14:
                        System.out.println(lista.calcularEdadMenor());
                        break;

                    case 15:
                        lista.invertirLista();
                        break;

                    case 16:
                        lista.vaciarLista();
                        break;

                    default:
                        System.out.println("No elegiste ninguna opción.");
                }

                if (opc != 0 && opc != 2 && opc != 3 && opc != 15 && opc != 16) {
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
