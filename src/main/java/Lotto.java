import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private List<Number> numbers;

    public Lotto(List<Integer> lottoNumbers) {
        validate(lottoNumbers);
        numbers = lottoNumbers.stream()
                .sorted()
                .map(Number::new)
                .toList();
    }

    public boolean isExist(Number number) {
        return numbers.contains(number);
    }

    public LottoPrize getLottoPrize(WinningLotto winningLotto) {
        final int matchedWinningCount = numbers.stream().filter(winningLotto::isMatchWinningNumber).toList().size();
        final boolean isMatchedBonus = numbers.stream().anyMatch(winningLotto::isMatchBonus);
        return LottoPrize.of(matchedWinningCount, isMatchedBonus);
    }

    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        String joined = numbers.stream()
                .map(Number::value)
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
