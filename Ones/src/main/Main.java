package main;

import java.util.Scanner;

/**
 *
 * @author jpsa4
 */
public class Main {

    public static void main(String[] args) {

        String n;
        int input,num,count;

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            n = sc.nextLine();
            if (n.equals("")) {
                break;
            }
            input = Integer.parseInt(n);

            num = 1;
            count = 1;
            while (num > 0) {
                if (num < input) {
                    num = (num * 10 + 1);
                    count++;
                    continue;
                }
                num = num % input;
            }
            System.out.println(count);
        }
    }
}
