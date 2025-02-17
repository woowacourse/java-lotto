package lotto.view;

import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_NUMBER;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readLottoMoney() {
        String message = "구입금액을 입력해 주세요.";
        String response = readResponse(message);
        return validateAndParseLottoMoney(response);
    }

    public static String readWinningNumbers() {
        String message = "지난 주 당첨 번호를 입력해 주세요.";
        return readResponse(message);
    }

    public static String readBonusBall() {
        String message = "보너스 볼을 입력해 주세요.";
        return readResponse(message);
    }

    private static String readResponse(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int validateAndParseLottoMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw INVALID_LOTTO_MONEY_NUMBER.getException();
        }
    }
}
