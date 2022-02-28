package lotto.domain;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import lotto.domain.vo.LottoNumber;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        sortNumbers(numbers);
        this.numbers = numbers;
    }

    private void sortNumbers(List<LottoNumber> numbers) {
        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
    }

    private void validateNumbers(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicateNumbers(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            String exceptionMessage = MessageFormat.format("로또 번호는 {0}자리 이어야 한다.", LOTTO_SIZE);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void validateDuplicateNumbers(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }
    }

    public int countMatchNumbers(Lotto lotto) {
        return (int) lotto.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }
}
