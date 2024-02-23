package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int nCases, nNumbers, k;
        Scanner sc;

        List<String> pairs = new ArrayList<>();
        List<List> cases = new ArrayList<>();
        List<Integer> caseNumbers = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        sc = new Scanner(System.in);
        nCases = sc.nextInt();

        for (int r = 0; r < nCases; r++) {

            nNumbers = sc.nextInt();
            k = sc.nextInt();
            caseNumbers.add(k);

            for (int i = 0; i < nNumbers; i++) {
                caseNumbers.add(sc.nextInt());
            }
            sc.nextLine();

            cases.add(new ArrayList<>(caseNumbers));
            caseNumbers.clear();
        }

        for (int u = 0; u < nCases; u++) {

            caseNumbers = cases.get(u);
            k = caseNumbers.get(0);
            caseNumbers.remove(0);

            int Ai, Aj;

            for (int i = 0; i < caseNumbers.size(); i++) {

                Ai = caseNumbers.get(i);

                for (int j = 0; j < caseNumbers.size(); j++) {

                    Aj = caseNumbers.get(j);

                    if (Aj >= Ai && i != j && (Ai + Aj) % k == 0) {
                        pairs.add(String.valueOf(Ai) + "," + String.valueOf(Aj));
                    }
                }
            }

            String pair1, pair2;

            for (int i = 0; i < pairs.size(); i++) {

                pair1 = pairs.get(i);

                for (int j = 0; j < pairs.size(); j++) {

                    pair2 = pairs.get(j);

                    if (pair1.equals(pair2) && i != j) {
                        pairs.remove(j);
                    }
                }
            }
            results.add(pairs.size());
            pairs.clear();
        }

        for (int i = 0; i < results.size(); i++) {
            System.out.println("Case " + (i + 1) + ": " + results.get(i));
        }
    }
}
