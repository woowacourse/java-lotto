package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class ChoiceNumber {
    private static final String ERROR_DUPLICATE_NUMBERS = "[ERROR] 선택한 번호중에 중복되는 값이 있습니다.";
    private static final String ERROR_NOT_IN_RANGE = "[ERROR] 1부터 45까지의 번호로 입력해주세요.";

    private static final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(toList());
    private static final String NUMBER_DELIMITER = ",";
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 45;
    private static final int CHOICE_NUMBER_SIZE = 6;
    private static final String ERROR_NOT_INTEGER = "[ERROR] 번호는 숫자로 입력해주세요";
    private static final String ERROR_NOT_ENOUGH_NUMBER = "[ERROR] 6개의 숫자를 입력해주세요";

    private final List<Integer> choiceNumbers;

    public ChoiceNumber() {
        Collections.shuffle(numbers);
        choiceNumbers = collectRightSizeOfNumber();
        Collections.sort(choiceNumbers);
    }

    public ChoiceNumber(String input) {
        choiceNumbers = convertToIntList(input);
        validateChoiceNumber();
    }

    private List<Integer> convertToIntList(String input) {
        try {
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                    .mapToInt(eachText -> Integer.parseInt(eachText.trim()))
                    .boxed()
                    .collect(toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private void validateChoiceNumber() {
        checkSize();
        checkNumbersRange();
        checkDuplicateNumbers();
    }

    private void checkSize() {
        if (choiceNumbers.size() != CHOICE_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_ENOUGH_NUMBER);
        }
    }

    private void checkNumbersRange() {
        if (choiceNumbers.stream().anyMatch(i -> i < MIN_BOUND || i > MAX_BOUND)) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    private void checkDuplicateNumbers() {
        boolean unique = choiceNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!unique) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBERS);
        }
    }

    public int countSameNumber(ChoiceNumber numbers) {
        return (int) choiceNumbers.stream()
                .filter(numbers.getChoiceNumbers()::contains)
                .count();
    }

    private List<Integer> collectRightSizeOfNumber() {
        return numbers.stream().limit(CHOICE_NUMBER_SIZE).collect(toList());
    }

    public boolean contains(int targetNumber) {
        return choiceNumbers.contains(targetNumber);
    }

    public List<Integer> getChoiceNumbers() {
        return choiceNumbers;
    }

    @Override
    public String toString() {
        return choiceNumbers.toString();
    }
}
