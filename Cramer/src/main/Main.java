/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        int tam=0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Cual es el tamaño de la matriz");
        tam=teclado.nextInt();
        int a[][]=new int[tam][tam]; //ecuacion
        int b[]=new int[tam]; //resultado de a
        float cmr[]=new float[tam]; //temp

        System.out.println("Inserte los valores de la matriz");
        insertarM(a); //llamada a procedimiento
        System.out.println("Inserte los valores del resultado");
        insertarV(b);
        cmr=cramer(a,b);
        System.out.println("Los valores para las X's son:");
        mostrarX(cmr);
    }

    //Declaracion de procedimiento para matriz
    public static void insertarM(int a[][]){
        Scanner teclado = new Scanner(System.in);
        for(int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length;j++){
                System.out.println("Inserte un valor de la posicion :[ "+i+" , "+j+" ]: " );
                a[i][j]=teclado.nextInt();
            }
        }
    }

    public static void mostrarM(int a[][]){
        for(int i=0; i<a.length; i++){
            for (int j=0; j<a[i].length;j++){
                System.out.println("El resultado de la matriz en la posicion  [ "+i+" , "+j+ " ] es: "+a[i][j]);
            }
        }
    }
    //Declaracion de procedimientos para vector
    public static void insertarV(int a[]){
        Scanner teclado = new Scanner(System.in);
       for(int i=0; i<a.length; i++){
                System.out.println("Inserte un valor entero del vector en la posicion: [ "+i+" ]: " );
                a[i]=teclado.nextInt();
        }       
    }

    public static void mostrarV(int a[]){
        for(int i=0; i<a.length; i++){
            System.out.println("El resultado de la matriz en la posicion  [ "+i+" ] es: "+a[i]);
        }

    }

    public static int determinante(int a[][]){
        int c[][]=new int[a.length+(a.length-1)][a.length];
        int det = 0;
        //almacena los resultados parciales
        int par[]=new int[(a.length)*2];

        for(int i=0;i<a.length;i++){
            System.arraycopy(a[i], 0, c[i], 0, a[i].length);
        }
        int k=0;
        for(int i=a.length;i<c.length;i++){
            System.arraycopy(a[k], 0, c[i], 0, a.length);
            k++;
        }

        //calcula la suma de los productos y la inserta en par
        k=0;
        int temp=1;
        int inc=1;

        for (int i=0; i< a.length;i++){
            for(int j=0; j<a[i].length;j++){
                temp=temp*c[k][j];
                k++;
            }
            k=inc;
            par[i]=temp;
            temp=1;
            inc++;
        }

        //calcula la resta de los productos y la inserta en par
        k=a.length-1;
        temp=1;
        inc=a.length-1;
        int l=(par.length)/2;

        for (int[] a1 : a) {
            for (int j = 0; j < a1.length; j++) {
                temp=temp*c[k][j];
                k--; //k=k-1;
            }
            par[l]=-temp;
            temp=1;
            inc++;
            k=inc;
            l++;
        }
        det=suma(par);
        return det;
    }

    //sustituye los valores de b en a en la posicion pos
    public static int [][] sustituye(int a[][], int b[], int pos){
        int c[][] =new int[a.length][a.length];

        for(int i=0;i<a.length;i++){
            for(int j=0; j<a[i].length; j++){
                if(j==pos){
                    c[i][j]=b[i];
                }
                else{
                    c[i][j]=a[i][j];
                }
            }
        }
        return c;
    }

    public static int suma(int a[]){
        int result=0;
        for(int i=0; i<a.length; i++){
            result=result+a[i];
        }

        return result;
    }

    ///funcion Main
    public static float[] cramer(int a[][], int b[]){
        float Rcramer[]=new float[b.length];
        int det=determinante(a);
        if(det==0){
            System.out.println("No tiene solucion");
            return Rcramer;
        }

        int detTemp;
        int c[][]=new int[a.length][a.length];

        for(int i=0; i<a.length; i++){
            c=sustituye(a,b,i);
            detTemp=determinante(c);
            Rcramer[i]=(float)detTemp/(float)det;
        }
        return Rcramer;
    }

    //muestra los resultados de X
    public static void mostrarX(float a[]){
        for(int i=0; i<a.length; i++){
            System.out.println("El resultado de X_"+i+" es: "+a[i]);
        }
    }
}