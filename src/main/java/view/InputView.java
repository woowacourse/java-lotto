package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final String NOT_DIGIT_MONEY_EXCEPTION_MESSAGE = "금액은 숫자만 입력해야 합니다";
    private static final String NOT_DIGIT_WINNING_NUMBER_EXCEPTION_MESSAGE = "당첨 번호는 숫자만 입력해야 합니다";
    private static final String NOT_DIGIT_BONUS_NUMBER_EXCEPTION_MESSAGE = "보너스 번호는 숫자만 입력해야 합니다";
    private static final String NOT_DIGIT_MANUAL_LOTTO_COUNT_EXCEPTION_MESSAGE = "수동으로 구매할 로뚜 수는 숫자만 입력해야 합니다";
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<Integer> requestWinningNumbers() {
        return readLottoNumber();
    }

    public List<List<Integer>> requestManualLottoNumbers(int manualLottoCount) {
        List<List<Integer>> manualLottoNumbers = new ArrayList<>(manualLottoCount);
        while (manualLottoCount-- > 0) {
            manualLottoNumbers.add(readLottoNumber());
        }
        return manualLottoNumbers;
    }

    private List<Integer> readLottoNumber() {
        try {
            return Arrays.stream(splitByDelimiter(readLine()))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NOT_DIGIT_WINNING_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private String[] splitByDelimiter(String userInput) {
        return userInput.split(DELIMITER);
    }

    public int requestMoney() {
        return parseInt(NOT_DIGIT_MONEY_EXCEPTION_MESSAGE);
    }

    public int requestBonusNumber() {
        return parseInt(NOT_DIGIT_BONUS_NUMBER_EXCEPTION_MESSAGE);
    }

    public int requestManualLottoQuantity() {
        return parseInt(NOT_DIGIT_MANUAL_LOTTO_COUNT_EXCEPTION_MESSAGE);
    }

    private int parseInt(String exceptionMessage) {
        try {
            return Integer.parseInt(readLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private String readLine() {
        return SCANNER.nextLine();
    }
}
