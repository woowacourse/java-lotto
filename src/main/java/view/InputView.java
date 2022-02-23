package view;

import java.util.Scanner;

public class InputView {
    private static final String REQUEST_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String REQUEST_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String REQUEST_NUMBER_MESSAGE = "숫자를 입력해 주세요";
    private static final Scanner scanner = new Scanner(System.in);

    public static int requestAmount() {
        try {
            System.out.println(REQUEST_AMOUNT_MESSAGE);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(REQUEST_NUMBER_MESSAGE);
            return requestAmount();
        }
    }

    public static String requestWinNumbers() {
        System.out.println(REQUEST_WIN_NUMBERS);
        return scanner.nextLine();
    }

    public static int requestBonusNumber() {
        try {
            System.out.println(REQUEST_BONUS_NUMBER_MESSAGE);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(REQUEST_NUMBER_MESSAGE);
            return requestAmount();
        }
    }
}
