import java.util.List;

public class Lotto {

    private final Numbers numbers;
    private static final int LOTTO_COUNT=6;

    public Lotto(Numbers numbers) {
        validate(numbers.getNumbers());
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    private void validate(List<Integer> numbers) {
        validateDistinct(numbers);
        validateSize(numbers);
    }

    private void validateDistinct(List<Integer> numbers) {
        if (numbers.stream().distinct().count() == LOTTO_COUNT) {
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
}
