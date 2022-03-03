package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Lotto {
    public static final int MIN_BOUND = 1;
    public static final int MAX_BOUND = 45;

    private static final List<Integer> numbers = IntStream.rangeClosed(MIN_BOUND, MAX_BOUND).boxed().collect(toList());
    private static final int CHOICE_NUMBER_SIZE = 6;

    private static final String ERROR_DUPLICATE_NUMBERS = "[ERROR] 선택한 번호중에 중복되는 값이 있습니다.";
    private static final String ERROR_NOT_IN_RANGE = "[ERROR] 1부터 45까지의 번호로 입력해주세요.";
    private static final String ERROR_NOT_ENOUGH_NUMBER = "[ERROR] 6개의 숫자를 입력해주세요";

    private final List<Integer> lotto;

    public Lotto() {
        Collections.shuffle(numbers);
        lotto = collectRightSizeOfNumber();
        Collections.sort(lotto);
    }

    public Lotto(List<Integer> inputValues) {
        checkSize(inputValues);
        checkNumbersRange(inputValues);
        checkDuplicateNumbers(inputValues);
        this.lotto = inputValues;
    }


    private void checkSize(List<Integer> inputValues) {
        if (inputValues.size() != CHOICE_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_ENOUGH_NUMBER);
        }
    }

    private void checkNumbersRange(List<Integer> inputValues) {
        if (inputValues.stream().anyMatch(i -> i < MIN_BOUND || i > MAX_BOUND)) {
            throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
        }
    }

    private void checkDuplicateNumbers(List<Integer> inputValues) {
        HashSet<Integer> integers = new HashSet<>(inputValues);
        if (integers.size() != inputValues.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBERS);
        }
    }

    public int countSameNumber(Lotto numbers) {
        return (int) lotto.stream()
                .filter(numbers.getLotto()::contains)
                .count();
    }

    private List<Integer> collectRightSizeOfNumber() {
        return numbers.stream().limit(CHOICE_NUMBER_SIZE).collect(toList());
    }

    public boolean contains(int targetNumber) {
        return lotto.contains(targetNumber);
    }

    public List<Integer> getLotto() {
        return lotto;
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
