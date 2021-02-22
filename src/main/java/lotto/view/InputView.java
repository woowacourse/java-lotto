package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String REQUEST_MANUAL_LOTTO_NUMBERS_MSG = "수동으로 구매할 로또 번호를 입력해 주세요.";
    private static final String REQUEST_MANUAL_LOTTO_SIZE_MSG = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String COMMA = ",";
    private static final String REQUEST_INPUT_MONEY_MSG = "구입 금액을 입력해주세요.";
    private static final String MONEY_MUST_BE_DIGIT_MSG = "구입 금액은 숫자여야 합니다.";
    private static final String REQUEST_INPUT_WINNING_NUMBERS_MSG = "지난주 당첨번호를 입력해주세요.";
    private static final String REQUEST_BONUS_NUMBER_MSG = "보너스 번호를 입력해주세요.";
    private static final String MUST_BE_DIGIT_ERROR_MSG = "숫자가 아닌 문자가 포함되어 있습니다.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int takeLottoMoney() {
        System.out.println(REQUEST_INPUT_MONEY_MSG);
        try {
            return inputDecimal();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MONEY_MUST_BE_DIGIT_MSG);
        }
    }

    public void printRequestMessageForInputManualLottoNumbers(){
        System.out.println(REQUEST_MANUAL_LOTTO_NUMBERS_MSG);
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println(REQUEST_INPUT_WINNING_NUMBERS_MSG);
        return inputToIntegerList();
    }

    public int takeBonusNumber() {
        System.out.println(REQUEST_BONUS_NUMBER_MSG);
        return inputDecimal();
    }

    public int inputSizeOfManualLotto() {
        System.out.println(REQUEST_MANUAL_LOTTO_SIZE_MSG);
        try {
            return inputDecimal();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MUST_BE_DIGIT_ERROR_MSG);
        }
    }

    public List<Integer> inputManualLottoNumbers() {
        return inputToIntegerList();
    }

    private int inputDecimal() {
        return Integer.parseInt(scanner.nextLine());
    }

    private List<Integer> inputToIntegerList() {
        String input = scanner.nextLine();
        List<String> splitNumbers = Arrays.asList(input.split(COMMA));
        List<String> trimNumbers = splitNumbers.stream().map(String::trim).collect(Collectors.toList());

        return stringsToIntegers(trimNumbers);
    }

    private List<Integer> stringsToIntegers(List<String> strings) {
        try {
            return strings.stream()
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MUST_BE_DIGIT_ERROR_MSG);
        }
    }
}
