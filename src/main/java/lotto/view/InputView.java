package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final String PRINT_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String PRINT_INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String PRINT_INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String PRINT_INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PRINT_INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoney() {
        System.out.println(PRINT_INPUT_MONEY);
        return scanner.nextLine();
    }

    public static String inputManualLottoCount() {
        System.out.println();
        System.out.println(PRINT_INPUT_MANUAL_LOTTO_COUNT);
        return scanner.nextLine();
    }

    public static void printManualLottoGuideMesseage() {
        System.out.println();
        System.out.println(PRINT_INPUT_MANUAL_LOTTO_NUMBERS);
    }

    public static String inputManualLottoNumbers() {
        return scanner.nextLine();
    }

    public static String inputLottoWinningNumbers() {
        System.out.println(PRINT_INPUT_WINNING_NUMBER);
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println(PRINT_INPUT_BONUS_NUMBER);
        return scanner.nextLine();
    }
}
