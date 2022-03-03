package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.model.LottoMachine;

public class InputView {

    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_MANUAL_PURCHASE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해주세요";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "[ERROR] 숫자를 입력해주세요.";
    private static final String SEPARATOR = ",";
    private static final int LIMIT = -1;

    private static final Scanner input = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
        return inputNumber();
    }

    public static int inputManualLottoCount() {
        System.out.println(INPUT_MANUAL_PURCHASE_MESSAGE);
        return inputNumber();
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return inputNumber();
    }

    private static int inputNumber() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    public static List<List<Integer>> inputManualLottos(LottoMachine lottoMachine) {
        System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
        return IntStream.range(0, lottoMachine.getManualCount())
            .mapToObj(i -> inputLotto())
            .collect(Collectors.toList());
    }

    public static List<Integer> inputWinningLotto() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return inputLotto();
    }

    private static List<Integer> inputLotto() {
        try {
            return Arrays.stream(input.nextLine().split(SEPARATOR, LIMIT))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }
}
