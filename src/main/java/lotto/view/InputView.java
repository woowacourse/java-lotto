package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String COMMA = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }

    public static String getManualQuantity() { return scanner.nextLine();}

    public static List<String[]> getManualNumbers(int quantity) {
        List<String[]> manualNumbers = new ArrayList<>();
        for (int i=0; i<quantity; i++) {
            manualNumbers.add(getInput().split(COMMA));
        }
        return manualNumbers;
    }

    public static String[] getWinningNumbers() {
        return scanner.nextLine().split(COMMA);
    }

}
