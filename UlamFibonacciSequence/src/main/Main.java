package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        Scanner sc;
        int xTerm, ulamNumber = 4;
        BigInteger result;

        sc = new Scanner(System.in);
        xTerm = sc.nextInt();

        BigInteger ulamXterm = calcUlamXterm(xTerm, ulamNumber);
        result = calcFibonacciXterm(xTerm).multiply(ulamXterm);
        System.out.println(result);

    }

    public static BigInteger calcUlamXterm(int xTerm, int ulamNumber) {
        List<Integer> ulamList = new ArrayList<>();

        ulamList.add(1);
        ulamList.add(2);
        ulamList.add(3);

        while (ulamList.size() < xTerm) {

            List<Integer> sumsList = new ArrayList<>();

            for (int j = 0; j <= ulamList.size() - 1; j++) {

                for (int k = 0; k <= ulamList.size() - 1; k++) {

                    if (ulamList.get(j) > ulamList.get(k)) {
                        sumsList.add(ulamList.get(j) + ulamList.get(k));
                    }
                }
            }
            int count = 0;

            for (int k = 0; k <= sumsList.size() - 1; k++) {

                if (sumsList.get(k) == ulamNumber) {
                    count += 1;
                }
            }
            if (count == 1) {
                ulamList.add(ulamNumber);
            }

            ulamNumber++;
        }
        return new BigInteger(Integer.toString(ulamList.get(xTerm - 1)));
    }

    public static BigInteger calcFibonacciXterm(int num) {

        BigInteger num0 = new BigInteger("0");
        BigInteger num1 = new BigInteger("1");

        List<BigInteger> fibonacciList = new ArrayList<>();

        fibonacciList.add(num0);
        fibonacciList.add(num1);

        for (int i = 2; i <= num; i++) {

            num1 = num0.add(fibonacciList.get(i - 1));
            num0 = num1.add(fibonacciList.get(i - 2));

            fibonacciList.add(num0);
            num0 = new BigInteger("0");
        }
        return fibonacciList.get(num);
    }
}
