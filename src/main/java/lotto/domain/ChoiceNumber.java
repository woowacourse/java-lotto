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

    private final List<Integer> choiceNumbers;

    public ChoiceNumber() {
        Collections.shuffle(numbers);
        choiceNumbers = getChoiceSizeNumber();
        Collections.sort(choiceNumbers);
    }

    public ChoiceNumber(String input) {
        choiceNumbers = Arrays.stream(input.split(NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
        validateRange();
        validateDuplicate();
    }

    private void validateRange() {
        if (choiceNumbers.stream().anyMatch(i -> i < MIN_BOUND || i > MAX_BOUND)) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    private void validateDuplicate() {
        boolean unique = choiceNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!unique) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBERS);
        }
    }

    private List<Integer> getChoiceSizeNumber() {
        return numbers.stream().limit(CHOICE_NUMBER_SIZE).collect(toList());
    }

    public List<Integer> getChoiceNumbers() {
        return choiceNumbers;
    }

    public int countSameNumber(ChoiceNumber numbers) {
        return (int) choiceNumbers.stream()
                .filter(numbers.getChoiceNumbers()::contains)
                .count();
    }

    public boolean contains(int targetNumber) {
        return choiceNumbers.contains(targetNumber);
    }

    @Override
    public String toString() {
        return choiceNumbers.toString();
    }
}
