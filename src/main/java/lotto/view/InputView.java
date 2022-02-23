package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PURCHASE_MONEY = "구매금액을 입력해 주세요.";
    private static final String INPUT_WIN_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_LOTTO_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final String INPUT_WIN_LOTTO_NUMBERS_DELIMITER = ",";
    private static final String INPUT_BLANK = " ";
    private static final String INPUT_NOT_BLANK = "";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println(INPUT_PURCHASE_MONEY);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Integer> inputWinLotto() {
        System.out.println(INPUT_WIN_LOTTO_NUMBERS);
        return Arrays.stream(scanner.nextLine().split(INPUT_WIN_LOTTO_NUMBERS_DELIMITER))
                .map(InputView::removeBlank)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static String removeBlank(String input) {
        return input.replaceAll(INPUT_BLANK, INPUT_NOT_BLANK);
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_LOTTO_NUMBER);
        return Integer.parseInt(scanner.nextLine());
    }
}
