package view;

import domain.WinningNumbers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String QUESTION_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String QUESTION_MANUAL_AMOUNT = "수동으로 구매할 수를 입력해 주세요.";
    private static final String QUESTION_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String QUESTION_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String QUESTION_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String NONE_NUMERIC_ERROR = "[ERROR] 숫자만 입력이 가능합니다.";

    private static final int NO_AMOUNT = 0;
    private static final int START_INDEX = 0;


    private static final Scanner scanner = new Scanner(System.in);

    public static int askMoneyInput() {
        System.out.println(QUESTION_MONEY_INPUT);
        return checkAndConvertInteger(scanner.nextLine());
    }

    public static int askManualAmount() {
        System.out.println(QUESTION_MANUAL_AMOUNT);
        return checkAndConvertInteger(scanner.nextLine());
    }

    public static List<List<Integer>> askManualLottoNumbers(int amount) {
        if (amount == NO_AMOUNT) {
            return new ArrayList<>();
        }

        System.out.println(QUESTION_MANUAL_LOTTO_NUMBERS);

        return askLottoNumbers(amount);
    }

    public static WinningNumbers askWinningNumbers() {
        return new WinningNumbers(askWinningNumber(), askBonusNumber());
    }

    public static List<Integer> askWinningNumber() {
        System.out.println(QUESTION_WINNING_NUMBERS);
        List<String> numbers = Arrays.asList(scanner.nextLine().split(NUMBER_DELIMITER));
        return covertNumbersToInteger(numbers);
    }

    public static int askBonusNumber() {
        System.out.println(QUESTION_BONUS_NUMBER);
        return checkAndConvertInteger(scanner.nextLine());
    }

    private static List<List<Integer>> askLottoNumbers(int amount) {
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        for (int i = START_INDEX; i < amount; i++) {
            List<String> numbers = Arrays.asList(scanner.nextLine().split(NUMBER_DELIMITER));
            lottoNumbers.add(covertNumbersToInteger(numbers));
        }

        return new ArrayList<>(lottoNumbers);
    }

    private static List<Integer> covertNumbersToInteger(List<String> numbers) {
        return numbers.stream()
            .map(InputView::checkAndConvertInteger)
            .sorted()
            .collect(Collectors.toList());
    }

    private static int checkAndConvertInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NONE_NUMERIC_ERROR);
        }
    }


}
