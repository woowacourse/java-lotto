package view;

import utils.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String DELIMITER = ",";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    private String input() {
        String input = SCANNER.nextLine();
        InputValidator.validateNull(input);
        InputValidator.validateEmpty(input);
        return input;
    }

    public int inputPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }

    public List<Integer> inputWinningNumber() {
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
        List<String> numberValues = toStrings(input());
        return toNumbers(numberValues);
    }

    private List<String> toStrings(String stringArray) {
        return Arrays.stream(stringArray.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private List<Integer> toNumbers(List<String> numberValues) {
        return numberValues.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int inputBonusBall() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }
}
