package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String INPUT_PURCHASE_MONEY = "구매금액을 입력해 주세요.";
    private static final String INPUT_PURCHASE_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_PURCHASE_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WIN_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_LOTTO_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final String INPUT_LOTTO_NUMBERS_DELEIMITER = ",";
    private static final String INPUT_WIN_LOTTO_NUMBERS_DELIMITER = ",";
    private static final String INPUT_BLANK = " ";
    private static final String INPUT_NOT_BLANK = "";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println(INPUT_PURCHASE_MONEY);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 구매금액은 숫자로 입력하세요");
        }
    }

    public static int inputPurchaseManualCount() {
        System.out.println(INPUT_PURCHASE_MANUAL_LOTTO_COUNT);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<List<Integer>> inputManualLottos(int counts) {
        System.out.println(INPUT_PURCHASE_MANUAL_LOTTO_NUMBERS);
        return IntStream.range(0, counts)
                .mapToObj(index -> inputManualLotto())
                .collect(Collectors.toList());
    }

    private static List<Integer> inputManualLotto() {
        try  {
            return Arrays.stream(scanner.nextLine().split(INPUT_LOTTO_NUMBERS_DELEIMITER))
                    .map(InputView::removeBlank)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 로또 번호는 숫자로 입력하세요");
        }
    }

    public static List<Integer> inputWinLotto() {
        System.out.println(INPUT_WIN_LOTTO_NUMBERS);
        try {
            return Arrays.stream(scanner.nextLine().split(INPUT_WIN_LOTTO_NUMBERS_DELIMITER))
                    .map(InputView::removeBlank)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 로또 번호는 숫자로 입력하세요");
        }
    }

    private static String removeBlank(final String input) {
        return input.replaceAll(INPUT_BLANK, INPUT_NOT_BLANK);
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_LOTTO_NUMBER);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 보너스 번호는 숫자로 입력하세요");
        }
    }
}
