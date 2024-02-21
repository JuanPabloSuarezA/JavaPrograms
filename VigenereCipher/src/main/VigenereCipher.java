/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author jpsa4
 */
public class VigenereCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String strDic = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóú,. ";
       
        String k = "hola";
        
         String msg = "El hijo de rana, Rinrín renacuajo salió esta mañana muy tieso" 
                + ", muy majo. Con pantalón corto, corbata a la moda, sombrero encintado"
                +" y chupa de boda.";
        
        String msgCif =  cifrarMensaje(strDic, msg, k);
        System.out.println("Cifrado Vigenere: ");
        System.out.println("");
        System.out.println("Mensaje cifrado: " );
        
        System.out.println(msgCif);
        
        String msgDescif = descifrarMensaje(strDic, msgCif, k);
        System.out.println("");
        System.out.println("Mensaje descifrado: ");
        System.out.println(msgDescif);
       
    }
    
    public static String cifrarMensaje (String strDic, String msg,String k){
        String msgCif ="";
        char strMi,strCi;
        int mi, ci,dicLen = strDic.length();
        
        int lenK = k.length();
        char letraK; 
        
        int ki ;
        
        for (int i = 0; i < msg.length(); i++) {
            strMi = msg.charAt(i);
            mi = strDic.indexOf(strMi);
            letraK = k.charAt(i % lenK);
            ki = strDic.indexOf(letraK);
            ci = (mi + ki) % dicLen;
            strCi = strDic.charAt(ci);
            msgCif += strCi;
        }
        return msgCif;
    }
    
    
    public static String descifrarMensaje (String strDic, String msgCif, String k){
        
        String msgDescif ="";
        char strMi,strCi;
        int mi, ci,dicLen = strDic.length();
        
        int lenK = k.length();
        char letraK; 
        int ki ;
        
        for (int i = 0; i < msgCif.length(); i++) {
            strMi = msgCif.charAt(i);
            ci = strDic.indexOf(strMi);
            letraK = k.charAt(i % lenK);
            ki = strDic.indexOf(letraK);
            mi = (ci + dicLen - ki) % dicLen;
            strCi = strDic.charAt(mi);
            msgDescif += strCi;
            
        }
        return msgDescif;
    }
}
