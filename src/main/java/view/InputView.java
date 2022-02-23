package view;

import utils.InputValidation;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";

    private final static Scanner scanner = new Scanner(System.in);

    public static int inputPrice() {
        try {
            System.out.println(INPUT_PRICE);
            return InputValidation.validatePrice(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPrice();
        }
    }

}
