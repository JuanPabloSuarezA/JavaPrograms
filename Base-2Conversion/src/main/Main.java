package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int inputNum, numCases;
        String result = "", newNum;
        StringBuilder sbr;
        Scanner sc = new Scanner(System.in);
        numCases = sc.nextInt();

        for (int i = 0; i < numCases; i++) {
            newNum = "";
            inputNum = sc.nextInt();

            while (inputNum != 1 && inputNum != 0) {
                if (inputNum < 0 && inputNum % 2 != 0.0) {
                    newNum += Integer.toString(-1 * (inputNum % -2));
                    inputNum = (inputNum / -2) + 1;
                    continue;
                }

                newNum += Integer.toString(inputNum % -2);
                inputNum = inputNum / -2;
            }

            if (inputNum == 1 || inputNum == 0) {
                newNum += inputNum;
            }

            sbr = new StringBuilder(newNum);
            sbr.reverse();

            result += "Case #" + (i + 1) + ": ";
            result += sbr.toString();
            if (i == numCases - 1) {
                continue;
            }
            result += "\n";
        }
        System.out.println(result);
    }
}
