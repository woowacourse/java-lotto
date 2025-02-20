package view;

import static model.LottoNumbers.LOTTO_NUMBER_COUNT;
import static model.LottoNumbers.MAXIMUM_LOTTO_NUMBER;
import static model.LottoNumbers.MINIMUM_LOTTO_NUMBER;
import static view.ResultView.NUMBER_COUNT_EXCEPTION;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WinLottoView {
    public static final String POSITIVE_NUMBER_EXCEPTION = "양의 정수를 입력해주세요.";
    public static final String NUMBER_BOUND_EXCEPTION = "1~45 사이의 숫자를 입력해주세요.";
    public static final String NUMBER_DUPLICATE_EXCEPTION = "중복이 아닌 숫자를 입력해주세요";
    public static final String WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    public static final String DELIMITER = ",";

    public void printWinNumberGuide() {
        System.out.println(WIN_NUMBERS);
    }

    public List<Integer> readWinNumbers() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> splitInput = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
        validateNumberCount(splitInput);
        splitInput.forEach(this::validatePositiveNumber);
        List<Integer> winNumbers = splitInput.stream().mapToInt(this::validatePositiveNumber).boxed().toList();
        winNumbers.forEach(this::validateBound);
        validateDuplicate(winNumbers);
        return winNumbers;
    }

    public void printBonusNumberGuide() {
        System.out.println(BONUS_NUMBER);
    }

    public Integer readBonusNumber() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Integer bonusNumber = validatePositiveNumber(input);
        validateBound(bonusNumber);
        return bonusNumber;
    }


    private void validateNumberCount(List<String> winNumbers) {
        if (winNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(NUMBER_COUNT_EXCEPTION);
        }
    }

    private Integer validatePositiveNumber(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException(POSITIVE_NUMBER_EXCEPTION);
        }
        return Integer.parseInt(input);
    }

    private void validateBound(Integer input) {
        if (input < MINIMUM_LOTTO_NUMBER || input > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(NUMBER_BOUND_EXCEPTION);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_EXCEPTION);
        }
    }
}
