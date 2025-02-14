import java.util.Comparator;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        numbers.sort(Comparator.naturalOrder());
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateDistinct(numbers);
        validateSize(numbers);
    }

    private void validateDistinct(List<Integer> numbers) {
        if (numbers.stream().distinct().count() == 6) {
            return;
        }
        throw new IllegalArgumentException("6자리로 입력해주세요");
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() == 6) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "[" + String.join(", ", numbers.stream().map(String::valueOf).toList()) + "]";
    }
}
