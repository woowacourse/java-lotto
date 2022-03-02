package lotto.view;

import java.util.List;
import java.util.Scanner;
import lotto.util.InputConvertor;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_NUMBERS_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return InputConvertor.toNaturalNumber(scanner.nextLine());
    }

    public static List<Integer> inputWinningLotto() {
        OutputView.printNewLine();
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return InputConvertor.toInt(
                InputConvertor.splitInput(scanner.nextLine(), INPUT_NUMBERS_DELIMITER));
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return InputConvertor.toInt(scanner.nextLine());
    }
}
