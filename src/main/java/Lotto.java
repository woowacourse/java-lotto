import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final Money PRICE = new Money("1_000");

    private List<Number> numbers;

    public Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        numbers = lottoNumbers.stream().map(Number::new).toList();
    }

    public static int getLottoCount(Money money) {
        if (money.getValue() % PRICE.getValue() != 0) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d원 단위만 가능합니다.", PRICE.getValue()));
        }
        return money.getValue() / PRICE.getValue();
    }

    public List<Integer> getNumbers() {
        return numbers.stream().map(Number::getValue).toList();
    }

    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        String joined = numbers.stream()
                .map(Number::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        stringBuilder.append(joined);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
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
}
