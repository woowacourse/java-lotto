package view;

import static validator.LottoNumberValidators.validateAndParseNumber;

import java.util.Scanner;

public class InputView {

    private static final String REQUEST_TOTAL_LOTTO_PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public static int requestTotalLottoPrice() {
        print(REQUEST_TOTAL_LOTTO_PRICE_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    public static String requestWinningNumbers() {
        print(REQUEST_WINNING_NUMBERS_INPUT_MESSAGE);
        return readline();
    }

    public static int requestBonusNumber() {
        print(REQUEST_BONUS_BALL_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    private static void print(String value) {
        System.out.println(value);
    }

    private static String readline() {
        return scanner.nextLine();
    }
}
