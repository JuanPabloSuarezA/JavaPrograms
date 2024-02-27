package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int input, numCases, digitSum, primeSum, total;
        String results = "";
        boolean isPrime;
        Scanner sc = new Scanner(System.in);
        numCases = sc.nextInt();

        for (int i = 0; i < numCases; i++) {

            input = sc.nextInt();
            input++;
            do {
                primeSum = 0;
                total = input;
                digitSum = calcDigitSum(total);
                isPrime = false;

                for (int j = 2; j*j <= total; j++) {

                    while (total % j == 0) {
                        primeSum += calcDigitSum(j);
                        total /= j;
                    }
                }
                if (total > 1) {
                    primeSum += calcDigitSum(total);
                }
                if (total == input) {
                    isPrime = true;
                }
                input++;
            } while (digitSum != primeSum || isPrime);

            results += Integer.toString(input - 1);
            if (i != numCases - 1) {
                results += "\n";
            }
        }
        System.out.println(results);
    }

    public static int calcDigitSum(int num) {
        int digitSum = 0, copyNum = num;
        while (copyNum != 0) {
            digitSum += copyNum % 10;
            copyNum /= 10;
        }
        return digitSum;
    }
}
