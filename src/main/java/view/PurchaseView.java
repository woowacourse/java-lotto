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
        Integer purchaseAmount = validatePositiveNumber(input);
        validateDividable(purchaseAmount);
        return purchaseAmount;
    }

    private Integer validatePositiveNumber(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(input);
    }

    private void validateDividable(Integer input) {
        if (input % 1000 != 0) {
            throw new IllegalArgumentException();
        }
    }


}
