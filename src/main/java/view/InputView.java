package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MESSAGE_TO_GET_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_FOR_LOTTO_COUNT = "%d개를 구매했습니다.%n";

    public static int scanInputMoney() {
        System.out.println(MESSAGE_TO_GET_INPUT_MONEY);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printLottoCount(int count) {
        System.out.printf(MESSAGE_FOR_LOTTO_COUNT, count);
    }
}
