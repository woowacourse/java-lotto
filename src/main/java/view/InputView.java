package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_IS_NULL = "입력 값은 널 값일 수 없습니다.";
    private static final String INPUT_IS_EMPTY = "입력 값은 비어있을 수 없습니다.";
    private static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    private String input() {
        String input = SCANNER.nextLine();
        validateNull(input);
        validateEmpty(input);
        return input;
    }

    public static void validateNull(String purchaseAmount) {
        if (purchaseAmount == null) {
            throw new NullPointerException(INPUT_IS_NULL);
        }
    }

    public static void validateEmpty(String purchaseAmount) {
        if (purchaseAmount.isEmpty()) {
            throw new IllegalArgumentException(INPUT_IS_EMPTY);
        }
    }

    public int inputPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }

    public String inputWinningNumber() {
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
        return input();
    }

    public int inputBonusBall() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }

    public int inputManualLottoCount() {
        System.out.print(System.lineSeparator());
        System.out.println(MANUAL_LOTTO_COUNT_MESSAGE);
        return Integer.parseInt(input());
    }

    public List<String> inputManualLottoNumber(int manualLottoCount) {
        System.out.print(System.lineSeparator());
        System.out.println(MANUAL_LOTTO_NUMBER_INPUT_MESSAGE);

        List<String> manualLottoValues = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottoValues.add(input());
        }
        return manualLottoValues;
    }
}
