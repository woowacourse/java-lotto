package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String COMMA = ",";

    public static String inputPurchaseAmount(){
        OutputView.printInputPurchaseAmountMessage();
        return scanner.nextLine();
    }
}
