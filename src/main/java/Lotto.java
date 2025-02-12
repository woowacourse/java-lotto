import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 구매한 로또 번호
public class Lotto {
    List<Integer> numbers;

    public Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        numbers = lottoNumbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또는 6개의 숫자로 구성돼야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        if (distinctNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또는 서로 다른 숫자로 구성돼야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호는 1부터 45 사이 정수만 가능합니다.");
            }
        }
    }
}
