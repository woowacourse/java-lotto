package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int PRICE = 1000;

    private static final int SIZE = 6;
    private static final int SUB_LIST_FROM_INDEX = 0;

    private static final List<Integer> LOTTO_NUMBERS = new ArrayList<>();

    private static final String TEXT_DELIMITER = ", ";

    private final Set<Number> numbers;

    static {
        for (int number = Number.MIN_VALUE; number <= Number.MAX_VALUE; number++) {
            LOTTO_NUMBERS.add(number);
        }
    }

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers.stream()
            .map(Number::new)
            .collect(Collectors.toSet());
    }

    public static Lotto auto() {
        Collections.shuffle(LOTTO_NUMBERS);
        return new Lotto(LOTTO_NUMBERS.subList(SUB_LIST_FROM_INDEX, SIZE));
    }

    public static Lotto of(String text) {
        String[] splitText = text.split(TEXT_DELIMITER);
        return new Lotto(toNumberList(splitText));
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    public Set<Number> getNumbers() {
        return numbers;
    }

    private static List<Integer> toNumberList(String[] splitText) {
        try {
            return Arrays.stream(splitText)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    private static void validateNumbers(List<Integer> numbers) {
        Set<Integer> removeDuplicateNumbers = new HashSet<>(numbers);
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("중복되지 않은 6개의 숫자가 필요합니다.");
        }
    }
}
