package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<String> allCards, bettyCards, aliceCards;

        String aliceLine, bettyLine, totalLine;
        int numTradeBetty,numTradeAlice;
        String numCards;

        Scanner sc = new Scanner(System.in);
        numCards = sc.nextLine();

        while (!numCards.equals("0 0")) {

            aliceLine = sc.nextLine();
            bettyLine = sc.nextLine();
            totalLine = aliceLine + " " + bettyLine;
            
            aliceCards = new HashSet<>(Arrays.asList(aliceLine.split(" ")));
            bettyCards = new HashSet<>(Arrays.asList(bettyLine.split(" ")));
            allCards = new HashSet<>(Arrays.asList(totalLine.split(" ")));
            
            numTradeAlice = allCards.size() - bettyCards.size();
            numTradeBetty = allCards.size() - aliceCards.size();

            if (numTradeAlice < numTradeBetty) {
                System.out.println(numTradeAlice);
            } else {
                System.out.println(numTradeBetty);
            }
            numCards = sc.nextLine();
        }
    }
}
