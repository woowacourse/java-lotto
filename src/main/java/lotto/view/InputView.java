package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_ERROR_MESSAGE = "문자가 입력되었습니다. 숫자를 입력해주세요.";
    private static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해주세요.";
    private static final String MANUAL_AMOUNT_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String WINNING_LOTTO_NUMBER_INPUT_MESSAGE = "당첨번호를 입력해주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해주세요.";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println(MONEY_INPUT_MESSAGE);
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            return inputMoney();
        }
    }

    public static int inputManualLottoAmount() {
        System.out.println(MANUAL_AMOUNT_INPUT_MESSAGE);
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            return inputManualLottoAmount();
        }
    }

    public static String inputWinningLottoNumbers() {
        System.out.println(WINNING_LOTTO_NUMBER_INPUT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static int inputBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(INPUT_ERROR_MESSAGE);
            return inputBonusNumber();
        }
    }
}
