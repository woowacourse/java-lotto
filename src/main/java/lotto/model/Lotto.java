package lotto.model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(final List<Integer> lottoNumbers, final LottoRule rule) throws IllegalArgumentException {
        final List<Integer> numbers = rule.numbersArrange(lottoNumbers);
        if (!rule.isValidList(numbers)) {
            throw new IllegalArgumentException(rule.getNumberFormatMessage());
        }
        this.numbers = numbers;
    }

    int getMatchCount(final Lotto winningNumbers) {
        return (int) numbers.stream() // count()의 결과는 long이므로 주의
                .filter(i -> winningNumbers.hasNumber(i))
                .count();
    }

    boolean hasNumber(final int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
