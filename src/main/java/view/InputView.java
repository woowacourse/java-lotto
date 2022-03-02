package view;

import utils.InputValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);
    public static final String MANUAL_QUANTITY_INPUT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String MANUAL_NUMBER_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    private static String input() {
        String input = scanner.nextLine();
        InputValidator.validateNull(input);
        InputValidator.validateEmpty(input);
        return input;
    }

    public static int inputPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }

    public static int inputManualQuantity() {
        System.out.println(MANUAL_QUANTITY_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }

    public static List<List<Integer>> inputManualNumber(int quantity) {
        ArrayList<List<Integer>> manualNumbers = new ArrayList<>();
        System.out.println(MANUAL_NUMBER_INPUT_MESSAGE);
        for(int i = 0; i < quantity; i++){
            manualNumbers.add(toIntegerList(input()));
        }
        return manualNumbers;
    }

    public static List<Integer> inputWinningNumber() {
        System.out.println(WINNING_NUMBER_INPUT_MESSAGE);
        return toIntegerList(input());
    }

    private static List<Integer> toIntegerList(String stringArray) {
        return Arrays.stream(stringArray.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusBall() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return Integer.parseInt(input());
    }
}
