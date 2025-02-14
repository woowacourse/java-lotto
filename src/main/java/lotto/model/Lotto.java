package lotto.model;

import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final List<LottoNumber> numbers;

    public Lotto(final List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(final List<LottoNumber> numbers) {
        validateSize(numbers);
    }

    private void validateSize(final List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자가 6개가 아닙니다.");
        }
    }

    public boolean has(final LottoNumber number) {
        return numbers.contains(number);
    }

    public int calculateMatchingCount(final Lotto otherLotto) {
        return (int) otherLotto.numbers.stream()
                .filter(this::has)
                .count();
    }

    public List<LottoNumber> getNumbers() {
        return numbers.stream()
                .sorted()
                .toList();
    }

}
