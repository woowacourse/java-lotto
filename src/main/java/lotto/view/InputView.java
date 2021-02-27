package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String WRONG_INPUT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다.";

    public static String requestInput() {
        return SCANNER.nextLine();
    }

    public static int inputMoney() {
        int money;
        try {
            money = SCANNER.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(WRONG_INPUT_ERROR);
        }
        return money;
    }
}
