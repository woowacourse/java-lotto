package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMIT = ",";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String PURCHASE_MONEY_INPUT_REQUEST = "구입금액을 입력해주세요.";
    private static final String MANUAL_LOTTO_LINE_PURCHASE_COUNT_INPUT_REQUEST = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_LINE_NUMBERS_INPUT_REQUEST = "\n수동으로 구매할 번호를 입력해주세요.";
    private static final String WINNING_LOTTO_LINE_INPUT_REQUEST = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_INPUT_REQUEST = "보너스 번호를 입력해 주세요.";

    private InputView() {
    }

    public static int getMoneyUserInput() {
        System.out.println(PURCHASE_MONEY_INPUT_REQUEST);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int getManualPurchaseCountUserInput() {
        System.out.println(MANUAL_LOTTO_LINE_PURCHASE_COUNT_INPUT_REQUEST);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<Integer> getLottoNumbersUserInput() {
        String LottoNumbersInput = SCANNER.nextLine();
        List<String> parsedLottoNumbersInput = Arrays
            .asList(LottoNumbersInput.replace(" ", "").split(DELIMIT));
        return parsedLottoNumbersInput.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static int getBonusLottoNumberUserInput() {
        System.out.println(BONUS_BALL_INPUT_REQUEST);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static void printManualLottoLineNumbersInputRequestMessage() {
        System.out.println(MANUAL_LOTTO_LINE_NUMBERS_INPUT_REQUEST);
    }

    public static void printWinningLottoLineInputRequestMessage() {
        System.out.println(WINNING_LOTTO_LINE_INPUT_REQUEST);
    }

}