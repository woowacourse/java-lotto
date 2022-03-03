package lotto.view;

import lotto.utils.ConverterUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입급액을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String inputManualLottoCount() {
        System.out.println();
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static void inputManualLottoMessage() {
        System.out.println();
        System.out.println(INPUT_MANUAL_LOTTO_NUMBER_MESSAGE);
    }

    public static List<Integer> inputManualLottos() {
        return inputLottoNumbers();
    }

    public static List<Integer> inputWinningLotto() {
        System.out.println();
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        return inputLottoNumbers();
    }

    public static List<Integer> inputLottoNumbers() {
        return Arrays.stream(SCANNER.nextLine()
                .split(DELIMITER))
                .map(String::trim)
                .map(ConverterUtils::convertStringToInt)
                .collect(Collectors.toList());
    }

    public static String inputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return SCANNER.nextLine();
    }
}
