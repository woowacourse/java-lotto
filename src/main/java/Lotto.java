import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int SIZE = 6;
    private static final String SIZE_ERROR_MESSAGE = "6개의 숫자가 필요합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복은 허용하지 않습니다.";

    private final Set<Number> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
            .map(String::valueOf)
            .map(Number::new)
            .collect(Collectors.toSet());
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

    public boolean contains(Number number) {
        return numbers.contains(number);
    }
}
