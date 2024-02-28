package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int n = 4, t = 1, selectedCoins;
        String result = "";
        Scanner sc;
        int[] coinsLengths;
        int[] tablesLengths;
        sc = new Scanner(System.in);

        while (n != 0 && t != 0) {

            n = sc.nextInt();
            t = sc.nextInt();
            coinsLengths = new int[n];
            tablesLengths = new int[t];

            for (int i = 0; i < coinsLengths.length; i++) {
                coinsLengths[i] = sc.nextInt();
            }
            for (int i = 0; i < tablesLengths.length; i++) {
                tablesLengths[i] = sc.nextInt();
            }
            int count = -1;

            for (int tableLength : tablesLengths) {
                count++;

                for (int i = tableLength; i > 0; i--) {
                    selectedCoins = 0;

                    for (int coinLength : coinsLengths) {

                        if (i % coinLength == 0) {
                            selectedCoins += 1;
                        }

                        if (selectedCoins == 4) {
                            result += i + " ";
                            break;
                        }
                    }

                    if (selectedCoins == 4) {
                        break;
                    }
                }

                for (int i = tableLength; i > 0; i++) {
                    selectedCoins = 0;

                    for (int coinLength : coinsLengths) {

                        if (i % coinLength == 0) {
                            selectedCoins += 1;
                        }

                        if (selectedCoins == 4) {
                            if (count == tablesLengths.length - 1) {
                                result += i;
                            } else {
                                result += i + "\n";
                            }
                            break;
                        }
                    }
                    if (selectedCoins == 4) {
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
