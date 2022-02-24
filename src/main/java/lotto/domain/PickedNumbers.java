package lotto.domain;

import lotto.Constant;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class PickedNumbers {
    private static final List<Integer> numbers = IntStream.rangeClosed(Constant.FIRST_NUM, Constant.LAST_NUM).boxed().collect(toList());
    private List<Integer> pickedNumbers;
    private static final String DUPLICATION_ERROR_MESSAGE = "중복값이 있습니다";
    private static final String NOT_IN_RANGE_MESSAGE = "범위내에 없습니다";

    public PickedNumbers() {
        Collections.shuffle(numbers);
        generateRandom6AscendingNumber();
    }

    public PickedNumbers(String input) {
        pickedNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
        validateRange();
        validateDuplicate();
    }

    public int findMatchCount(PickedNumbers numbers) {
        return pickedNumbers.stream()
                .filter(numbers.getPickedNumbers()::contains)
                .collect(toList())
                .size();
    }

    public boolean isContainNumber(int targetNumber) {
        return pickedNumbers.contains(targetNumber);
    }

    public List<Integer> getPickedNumbers() {
        return pickedNumbers;
    }

    private void validateDuplicate() {
        boolean unique = pickedNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!unique) {
            throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
        }
    }

    private void validateRange() {
        if (pickedNumbers.stream().anyMatch(i -> i < Constant.FIRST_NUM || i > Constant.LAST_NUM)) {
            throw new IllegalArgumentException(NOT_IN_RANGE_MESSAGE);
        }
    }

    private void generateRandom6AscendingNumber() {
        pickedNumbers = numbers.stream().limit(6).collect(toList());
        Collections.sort(pickedNumbers);
    }

    @Override
    public String toString() {
        return pickedNumbers.toString();
    }
}
