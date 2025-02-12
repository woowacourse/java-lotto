package view;

import constant.OutputMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PurchaseView {
    public void printPurchaseGuide() {
        System.out.println(OutputMessage.purchaseGuide);
    }

    public Integer readPurchaseAmount()  {
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
