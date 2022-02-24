package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {

    private static final String QUESTION_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String QUESTION_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String QUESTION_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String NONE_NUMERIC_ERROR = "[ERROR] 숫자만 입력이 가능합니다.";
    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 로또번호는 중복될 수 없습니다.";
    private static final String OUT_OF_RANGE_ERROR = "[ERROR] 1이상 45이하 값을 입력해주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public static String askMoneyInput() {
        System.out.println(QUESTION_MONEY_INPUT);
        return scanner.nextLine();
    }

    public static List<Integer> askLottoNumbers() {
        List<Integer> lottoNumbers = askWinningNumbers();
        lottoNumbers.add(askBonusNumber());
        checkNumbersRange(lottoNumbers);
        checkDuplicate(lottoNumbers);
        return lottoNumbers;
    }

    private static void checkNumbersRange(List<Integer> numbers) {
        for (int number : numbers) {
            checkBoundary(number);
        }
    }

    private static void checkBoundary(int number) {
        if (number > 1 && number < 46) {
            throw new IllegalArgumentException(OUT_OF_RANGE_ERROR);
        }
    }

    private static List<Integer> askWinningNumbers() {
        System.out.println(QUESTION_WINNING_NUMBERS);
        List<String> numbers = Arrays.asList(scanner.nextLine().split(NUMBER_DELIMITER));
        return covertNumbersToInteger(numbers);
    }

    private static int askBonusNumber() {
        System.out.println(QUESTION_BONUS_NUMBER);
        return checkAndConvertInteger(scanner.nextLine());
    }

    private static List<Integer> covertNumbersToInteger(List<String> numbers) {
        return numbers.stream()
                .map(InputView::checkAndConvertInteger)
                .collect(Collectors.toList());
    }

    private static int checkAndConvertInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NONE_NUMERIC_ERROR);
        }
    }

    private static void checkDuplicate(List<Integer> numbers) {
        Set<Integer> noDuplicatedNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicatedNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }
    }
}
