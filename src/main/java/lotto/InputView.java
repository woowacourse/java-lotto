package lotto;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputMoney() {
        return SCANNER.nextInt();
    }

    public static List<Number> inputNumbers() {
        return Collections.emptyList();
    }

}
