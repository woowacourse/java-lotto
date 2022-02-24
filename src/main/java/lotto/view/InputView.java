package lotto.view;

import java.util.Scanner;
import lotto.utils.StringUtil;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "%n지난 주 당첨 번호를 입력해 주세요.%n";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return StringUtil.toInt(scanner.nextLine());
    }

    public static String inputWinningNumbers() {
        System.out.printf(INPUT_WINNING_NUMBERS_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }
}
