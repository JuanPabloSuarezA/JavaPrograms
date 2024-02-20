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
public class CaesarCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String strDic = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóú,. ";
        
        int b = 30;
        
        String msg = "El hijo de rana, Rinrín renacuajo salió esta mañana muy tieso" 
                + ", muy majo. Con pantalón corto, corbata a la moda, sombrero encintado"
                +" y chupa de boda.";
        
        String msgCif =  cifrarMensaje(strDic, msg, b);
        System.out.println("Mensaje cifrado: " + msgCif);
        String msgDescif = descifrarMensaje(strDic, msgCif, b);
        System.out.println("Mensaje descifrado: " + msgDescif);
    }
    
    public static String cifrarMensaje (String strDic, String msg, int b){
        String msgCif ="";
        char strMi,strCi;
        int mi, ci,dicLen = strDic.length();
        
        for (int i = 0; i < msg.length(); i++) {
            strMi = msg.charAt(i);
            mi = strDic.indexOf(strMi);
            ci = (mi + b) % dicLen;
            strCi = strDic.charAt(ci);
            msgCif += strCi;
        }
        return msgCif;
    }
    public static String descifrarMensaje (String strDic, String msgCif, int b){
        
        String msgDescif ="";
        char strMi,strCi;
        int mi, ci,dicLen = strDic.length();
        
        for (int i = 0; i < msgCif.length(); i++) {
            strMi = msgCif.charAt(i);
            ci = strDic.indexOf(strMi);
            mi = (ci + dicLen - b) % dicLen;
            strCi = strDic.charAt(mi);
            msgDescif += strCi;
            
        }
        return msgDescif;
    }
    
}
