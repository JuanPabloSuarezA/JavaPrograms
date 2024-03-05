package main;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String strDic = "abcdefghijklmnñopqrstuvwxyz";

        String k = "aber";

        String msg = "paraquelacosanomesorprendacomootrosañoshecomenzadoyaconunossuaves"
                + "ejerciciosdeprecalentamientomientrasdesayunabahecontempladounabolaplateada"
                + "yunatiradeespumillonymañanameiniciareenelamoralprojimoconlosquelimpienel"
                + "parabrisasenlossemaforosestagimnasiadelcorazonmetaforicoestanimportante"
                + "comoladelotrocorazonporquelosriesgoscoronariosestanahiescondidostrasla"
                + "vidasedentariayparapetadosenfechascomoestasdenavidad";

        String msgEncrypt = encryptMsg(strDic, msg, k);
        System.out.println("Mensaje cifrado con Vigenere: ");

        for (int i = 0; i < msgEncrypt.length(); i++) {
            if (i % 5 == 0 && i > 0) {
                System.out.print(" ");
            }

            if (i % 60 == 0 && i > 0) {
                System.out.println("");
            }
            System.out.print(msgEncrypt.charAt(i));
        }
        System.out.println("");

        String llaveDescifrada = kasiskIMethod(strDic, msgEncrypt);
        String msgDecrypt = decryptMsg(strDic, msgEncrypt, llaveDescifrada);
        System.out.println("Mensaje descifrado: " + msgDecrypt);
    }

    public static String encryptMsg(String strDic, String msg, String k) {
        String msgEncrypt = "";
        char strMi, strCi;
        int mi, ci, dicLen = strDic.length();

        int lenK = k.length();
        char charK;

        int ki;

        for (int i = 0; i < msg.length(); i++) {
            strMi = msg.charAt(i);
            mi = strDic.indexOf(strMi);
            charK = k.charAt(i % lenK);
            ki = strDic.indexOf(charK);
            ci = (mi + ki) % dicLen;
            strCi = strDic.charAt(ci);
            msgEncrypt += strCi;
        }
        return msgEncrypt;
    }

    public static String decryptMsg(String strDic, String msgEncrypt, String k) {

        String msgDecrypt = "";
        char strMi, strCi;
        int mi, ci, dicLen = strDic.length();

        int lenK = k.length();
        char charK;
        int ki;

        for (int i = 0; i < msgEncrypt.length(); i++) {
            strMi = msgEncrypt.charAt(i);
            ci = strDic.indexOf(strMi);
            charK = k.charAt(i % lenK);
            ki = strDic.indexOf(charK);
            mi = (ci + dicLen - ki) % dicLen;
            strCi = strDic.charAt(mi);
            msgDecrypt += strCi;

        }
        return msgDecrypt;
    }

    public static String kasiskIMethod(String strDic, String msgEncrypt) {

        String keyK = "";
        List<Integer> cadSpaces = calcCadSpaces(msgEncrypt);
        int mcd = calcMcd(cadSpaces);
        String[] arrSubmsgEncrypt = generateSubMsgEncryot(mcd, msgEncrypt);
        Integer[][] matrixFrequenciesSubEncrypt = calcFrecArrMsgEncrypt(arrSubmsgEncrypt, strDic);
        keyK = calcAEOSRule(matrixFrequenciesSubEncrypt, strDic);
        printResKasiski(matrixFrequenciesSubEncrypt, strDic, keyK, cadSpaces, mcd, arrSubmsgEncrypt);

        return keyK;
    }

    public static List<Integer> calcCadSpaces(String msgEncrypt) {
        List<Integer> cadSpaces = new ArrayList<>();
        String subMsgEncrypt = "";
        String[] arrMsgCif;
        int spacesNum;

        for (int i = 0; i <= msgEncrypt.length() - 4; i++) {
            subMsgEncrypt = msgEncrypt.substring(i, i + 4);
            arrMsgCif = msgEncrypt.split(subMsgEncrypt);

            if (arrMsgCif.length > 2) {
                for (int j = 1; j < arrMsgCif.length - 1; j++) {
                    spacesNum = arrMsgCif[j].length() + 4;
                    if (!cadSpaces.contains(spacesNum)) {
                        cadSpaces.add(spacesNum);
                    }
                }
            }
        }
        return cadSpaces;
    }

    public static int calcMcd(List<Integer> cadSpaces) {

        int mcd = 0, tempNumB;
        int numA = 0, numB = 0;

        for (int i = 0; i < cadSpaces.size(); i++) {

            numA = cadSpaces.get(i);
            numB = mcd;
            if (mcd == 0) {
                numB = cadSpaces.get(i + 1);
            }
            if (i == 1) {
                continue;
            }
            if (numA > numB) {
                tempNumB = numB;
                numB = numA;
                numA = tempNumB;
            }
            while (numB != 0) {
                tempNumB = numB;
                numB = numA % numB;
                numA = tempNumB;
            }
            mcd = numA;
        }
        return mcd;
    }

    public static String[] generateSubMsgEncryot(int mcd, String msgEncrypt) {
        String[] arrSubmsgEncrypt = new String[mcd];
        for (int i = 0; i < arrSubmsgEncrypt.length; i++) {
            arrSubmsgEncrypt[i] = "";
        }

        for (int i = 0; i < msgEncrypt.length(); i++) {
            for (int j = 0; j < arrSubmsgEncrypt.length; j++) {
                if (i % arrSubmsgEncrypt.length == j) {
                    arrSubmsgEncrypt[j] += msgEncrypt.charAt(i);
                    break;
                }
            }
        }

        return arrSubmsgEncrypt;
    }

    public static Integer[][] calcFrecArrMsgEncrypt(String[] arrSubmsgEncrypt, String strDic) {

        Integer[][] matrixFrequenciesSubEncrypt
                = new Integer[arrSubmsgEncrypt.length][strDic.length()];
        String subMsgEncrypt;
        char dicChar;
        int charFreq;

        for (int i = 0; i < arrSubmsgEncrypt.length; i++) {
            subMsgEncrypt = arrSubmsgEncrypt[i];

            for (int j = 0; j < strDic.length(); j++) {
                dicChar = strDic.charAt(j);
                charFreq = 0;
                for (int k = 0; k < subMsgEncrypt.length(); k++) {
                    if (subMsgEncrypt.charAt(k) == dicChar) {
                        charFreq++;
                    }
                }
                matrixFrequenciesSubEncrypt[i][j] = charFreq;
            }
        }
        return matrixFrequenciesSubEncrypt;
    }

    public static String calcAEOSRule(Integer[][] matrixFrequenciesSubEncrypt, String strDic) {

        String keyK = "";
        int charClass;
        int posRule;
        boolean aeosPattern;
        Integer[] arrFreqSubMsgEncrypt;
        Integer[] aeosMinClass = {4, 4, 6, 8};
        Integer[] aeosRule = {0, 4, 15, 19}; // 0 +4 +11 + 4
        for (Integer[] matrixFrequenciesSubEncrypt1 : matrixFrequenciesSubEncrypt) {
            aeosPattern = false;
            arrFreqSubMsgEncrypt = matrixFrequenciesSubEncrypt1;
            posRule = -1;
            while (!aeosPattern) {

                posRule++;
                //Para cada letra se realiza el procedimiento
                for (int j = 0; j < aeosRule.length; j++) {
                    charClass = 1;
                    int posChar = (posRule + aeosRule[j]) % strDic.length();

                    for (int k = 0; k < arrFreqSubMsgEncrypt.length; k++) {
                        if (k != posChar && arrFreqSubMsgEncrypt[k] > arrFreqSubMsgEncrypt[posChar]) {
                            charClass++;
                        }
                    }
                    if (charClass > aeosMinClass[j]) {
                        break;
                    }
                    if (j == aeosRule.length - 1) {
                        aeosPattern = true;
                    }
                }
            }
            keyK += strDic.charAt(posRule);
        }
        return keyK;
    }

    public static void printResKasiski(Integer[][] matrix, String strDic,
            String keyK, List<Integer> cadSpaces, int mcd, String[] arrSubmsgEncrypt) {

        System.out.println("");
        System.out.println("Resultados de método Kasiski: ");
        System.out.println("");

        System.out.print("Espacios de cadenas repetidas: ");
        for (Integer e : cadSpaces) {
            System.out.print(e + " ");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("MCD entre los valores de espacios: " + mcd);

        System.out.println("");

        System.out.println("Subcriptogramas: ");

        for (int i = 0; i < arrSubmsgEncrypt.length; i++) {
            System.out.println("C" + i + ": " + arrSubmsgEncrypt[i]);
        }
        System.out.println("");

        System.out.println("Matriz de frecuencias: ");
        for (int i = 0; i < strDic.length(); i++) {

            System.out.print(strDic.charAt(i) + "   ");
        }
        System.out.println("");
        for (int i = 0; i < strDic.length(); i++) {
            if (i == strDic.length() - 1) {
                System.out.print("-");
            } else {
                System.out.print("----");
            }

        }

        System.out.println("");
        for (Integer[] matrix1 : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix1[j] < 10) {
                    System.out.print(matrix1[j] + "   ");
                } else {
                    System.out.print(matrix1[j] + "  ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Clave del cifrado: " + keyK);
    }
}
