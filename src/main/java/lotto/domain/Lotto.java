package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int SIZE = 6;
    private static final String SIZE_ERROR_MESSAGE = "6개의 숫자가 필요합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복은 허용하지 않습니다.";
    private static final List<Number> LOTTO_NUMBERS = initLottoNumbers();
    private static final int SUB_LIST_FROM_INDEX = 0;
    private static final int SUB_LIST_TO_INDEX = 6;

    private final Set<Number> numbers;

    public Lotto() {
        Collections.shuffle(LOTTO_NUMBERS);
        this.numbers = new HashSet<>(LOTTO_NUMBERS.subList(SUB_LIST_FROM_INDEX, SUB_LIST_TO_INDEX));
    }

    Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
            .map(String::valueOf)
            .map(Number::new)
            .collect(Collectors.toSet());
    }

    public boolean contains(Number number) {
        return numbers.contains(number);
    }

    private static List<Number> initLottoNumbers() {
        List<Number> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            Number number = new Number(String.valueOf(i));
            numbers.add(number);
        }
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        int noDuplicateCount = (int)numbers.stream()
            .distinct()
            .count();

        if (noDuplicateCount != SIZE) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    public WinningPrice getWinningPrice(List<Number> winningNumbers, Number bonusNumber) {
        int count = 0;
        boolean containsBonus = false;
        for (Number number : winningNumbers) {
            if (numbers.contains(number)) {
                count++;
            }
        }
        if (count == 5 && numbers.contains(bonusNumber)) {
            containsBonus = true;
        }

        return WinningPrice.of(count, containsBonus);
    }

    public Set<Number> getNumbers() {
        return numbers;
    }
}
