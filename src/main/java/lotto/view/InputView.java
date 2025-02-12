package lotto.view;

import lotto.util.Converter;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static long inputPurchaseAmount() {
        return scanner.nextLong();
    }
}
