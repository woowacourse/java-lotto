package view;

import constant.OutputMessage;
import java.util.Scanner;

public class PurchaseView {
    public void printPurchaseGuide() {
        System.out.println(OutputMessage.purchaseGuide);
    }

    public Integer readPurchaseAmount() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        validate(input);
        return Integer.parseInt(input);
    }

    private void validate(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException();
        }
    }


}
