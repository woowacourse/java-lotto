package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String MESSAGE_BUDGET = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요. (쉼표로 구분)";
    private static final String MESSAGE_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String MESSAGE_MANUAL_LOTTO_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MESSAGE_FORMAT_MANUAL_LOTTO_NUMBER = "구매할 로또 번호 : ";
    private static final String COMMA = ",";
    private static final String ERROR_BLANK = "[ERROR] 값을 입력해주세요.";
    private static final String ERROR_INVALID_NUMBER_FORMAT = "[ERROR] 숫자가 아니거나 지나치게 큰 값을 입력하셨습니다.";

    private static InputView instance;

    private OutputView outputView = OutputView.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
        }
        return instance;
    }

    private String deleteWhiteSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }

    public String scanBudget() {
        System.out.println(MESSAGE_BUDGET);
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        outputView.newLine();;
        validEmptyString(inputString);
        return inputString;
    }

    public List<Integer> scanWinningNumber() {
        System.out.println(MESSAGE_WINNING_NUMBER);
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        validEmptyString(inputString);
        outputView.newLine();
        return Arrays.stream(inputString.split(COMMA))
                .map(this::parseInteger)
                .collect(Collectors.toList());
    }

    public int scanBonusBall() {
        System.out.println(MESSAGE_BONUS_BALL);
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        validEmptyString(inputString);
        outputView.newLine();
        return parseInteger(inputString);
    }

    public int scanManualLottoQuantity() {
        System.out.println(MESSAGE_MANUAL_LOTTO_QUANTITY);
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        validEmptyString(inputString);
        outputView.newLine();
        return parseInteger(inputString);
    }

    private int parseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER_FORMAT);
        }
    }

    private void validEmptyString(String string) {
        if (string.equals("")) {
            throw new IllegalArgumentException(ERROR_BLANK);
        }
    }

    public List<Integer> scanManualLottoNumbers() {
        System.out.print(MESSAGE_FORMAT_MANUAL_LOTTO_NUMBER);
        String inputString = deleteWhiteSpaces(scanner.nextLine());
        validEmptyString(inputString);
        return Arrays.stream(inputString.split(COMMA))
                .map(this::parseInteger)
                .collect(Collectors.toList());
    }
}
