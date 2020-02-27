package lotto.domain.ticket.manual;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualNumber {
    private static final String COMMA = ",";
    private final Set<Integer> numbers;

    public ManualNumber(String numberLine) {
        Set<Integer> numbers = collectNumbers(numberLine);
        validateSize(numbers);
        this.numbers = numbers;
    }

    private Set<Integer> collectNumbers(String numberLine) {
        return Arrays.stream(numberLine.split(COMMA))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void validateSize(Set<Integer> numbers) {
        int size = numbers.size();
        if (size != 6) {
            throw new IllegalArgumentException(String.format("%d : 로또 번호는 6개만 허용됩니다.", size));
        }
    }

    public Set<Integer> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }
}
