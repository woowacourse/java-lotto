package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final String IS_NULL_OR_BLANK_ERROR_MESSAGE = "입력값이 확인되지 않습니다.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입럭해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBER_SPLIT_DELIMITER = ",";

    private static final InputView instance = new InputView();
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public int inputPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        String userInput = inputLine();
        return parse(userInput);
    }

    public Set<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String userInput = inputLine();
        List<String> splitInputData = splitInputData(userInput);
        return splitInputData.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String userInput = inputLine();
        return parse(userInput.trim());
    }

    public static InputView getInstance() {
        return instance;
    }

    private List<String> splitInputData(String inputData) {
        return Arrays.stream(inputData.split(LOTTO_NUMBER_SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private String inputLine() {
        String userInput = scanner.nextLine();
        inputNullOrBlankCheck(userInput);
        return userInput;
    }

    private void inputNullOrBlankCheck(String inputText) {
        if (isNullOrBlank(inputText)) {
            throw new IllegalArgumentException(IS_NULL_OR_BLANK_ERROR_MESSAGE);
        }
    }

    private boolean isNullOrBlank(String inputText) {
        return inputText == null || inputText.isBlank();
    }

    private static int parse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}
