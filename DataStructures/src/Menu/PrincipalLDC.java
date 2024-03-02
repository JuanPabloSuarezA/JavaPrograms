package Menu;

import ListaDoblementeCircular.Premio;
import ListaDoblementeCircular.Rueda;
import java.util.Scanner;

public class PrincipalLDC {

    public static void main(String[] args) {

        Scanner tec;
        String nombrePremio;
        int id = 1, opc = -1, direc, dinero = 10000, decision, tamanoRueda, ind, idEliminar;
        Rueda rueda = new Rueda();

        Premio premio;

        premio = new Premio("Cartuchera", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Cuaderno", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Audifonos", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Lonchera", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Zapatos", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Jean", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Bolso", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Nevera", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Microondas", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero += 10000;
        id++;
        premio = new Premio("Celular", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero = 1000000;
        id++;
        premio = new Premio("Computador", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero = 10000000;
        id++;
        premio = new Premio("Carro", dinero);
        rueda.agregarElementoFinal(premio, id);
        dinero = 100000000;
        id++;
        premio = new Premio("Avión", dinero);
        rueda.agregarElementoFinal(premio, id);

        while (opc != 0) {

            tec = new Scanner(System.in);

            System.out.println("Rueda de los premios");
            System.out.println("");
            System.out.println("Indicaciones:");
            System.out.println("");
            System.out.println("1.El mínimo número de recuadros es 7 y el máximo es 13.");
            System.out.println("2.Cada vez que gires la rueda, empieza desde el "
                    + "recuadro seleccionado en el anterior giro.");
            System.out.println("3.Para cerrar el menú ingresa 0.");
            System.out.println("4.Ten en cuenta que el recuadro en el índice 1 varía"
                    + " con cada giro que haces.");

            System.out.println("");
            System.out.println("Opciones:");
            System.out.println("");
            System.out.println("1.Realizar sorteo.");
            System.out.println("2.Agregar recuadro por índice (el primer índice es 1).");
            System.out.println("3.Eliminar recuadro por ID.");
            System.out.println("4.Modificar contenido de recuadro por ID.");
            System.out.println("5.Ver lista de premios.");
            System.out.println("");
            System.out.print("Ingresa el número de la opción deseada: ");

            try {

                opc = Integer.valueOf(tec.nextLine());

                switch (opc) {
                    case 0:
                        break;

                    case 1:

                        try {
                            System.out.print("Ingresa 1 para girar la rueda hacia la derecha o 2 para girar"
                                    + " hacia la izquierda: ");

                            direc = Integer.valueOf(tec.nextLine());

                            if (direc == 1 || direc == 2) {
                                System.out.print(rueda.realizarSorteo(direc));

                                decision = Integer.valueOf(tec.nextLine());
                                while (decision != 1 && decision != 2) {
                                    System.out.print("Debes ingresar 1 para el premio o 2 para el dinero: ");
                                    decision = Integer.valueOf(tec.nextLine());
                                }
                                System.out.println(rueda.elegirOpcion(decision));

                            } else {
                                System.out.println("No ingresaste ninguno de los dos números.");
                            }

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }

                        break;

                    case 2:
                        tamanoRueda = rueda.getTamano();

                        if (tamanoRueda < 13) {

                            try {

                                System.out.print("Ingresa el índice en el que deseas agregar el recuadro: ");
                                ind = Integer.valueOf(tec.nextLine());

                                if (ind >= 1 && ind <= tamanoRueda + 1) {
                                    System.out.print("Ingresa el nombre del premio: ");
                                    nombrePremio = tec.nextLine();
                                    System.out.print("Ingresa el dinero del premio: ");
                                    dinero = Integer.valueOf(tec.nextLine());

                                    if (dinero > 0) {
                                        premio = new Premio(nombrePremio, dinero);
                                        id++;
                                        rueda.agregarRecuadro(ind, premio, id);
                                    }
                                    else{
                                        System.out.println("El dinero debe ser positivo.");
                                    }

                                } else {
                                    System.out.println("El índice debe estar entre 1 y " + (tamanoRueda + 1));
                                }

                            } catch (Exception e) {
                                System.out.println("No ingresaste un número.");
                            }

                        } else {
                            System.out.println("No pueden haber más de 13 recuadros.");
                        }
                        break;

                    case 3:
                        tamanoRueda = rueda.getTamano();

                        if (tamanoRueda > 7) {
                            try {
                                System.out.print("Ingresa el ID del recuadro que deseas eliminar: ");
                                idEliminar = Integer.valueOf(tec.nextLine());

                                System.out.println(rueda.eliminarRecuadro(idEliminar));
                            } catch (Exception e) {
                                System.out.println("No ingresaste un número.");
                            }

                        } else {
                            System.out.println("No pueden haber menos de 7 recuadros.");
                        }

                        break;

                    case 4:

                        try {

                            System.out.print("Ingresa el ID del recuadro que deseas modificar: ");
                            idEliminar = Integer.valueOf(tec.nextLine());

                            System.out.print("Ingresa el nuevo nombre del premio: ");
                            nombrePremio = tec.nextLine();
                            System.out.print("Ingresa el nuevo dinero del premio: ");
                            dinero = Integer.valueOf(tec.nextLine());

                            premio = new Premio(nombrePremio, dinero);
                            rueda.modificarPremioRecuadro(idEliminar, premio);

                        } catch (Exception e) {
                            System.out.println("No ingresaste un número.");
                        }

                        break;

                    case 5:
                        System.out.println(rueda.imprimirRueda());
                        break;

                    default:
                        System.out.println("No elegiste ninguna opción.");
                        break;

                }
                if (opc != 0) {
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
