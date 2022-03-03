package domain;

import java.util.*;

import static domain.RandomNumbersGenerator.LOTTO_LENGTH;

public class Lotto {
    private static final String LOTTO_SIZE_MESSAGE = "[ERROR] 로또의 숫자는 중복 없이 6개여야 합니다.";

    private final Set<LottoNumber> numbers;

    public Lotto(Set<LottoNumber> numbers) {
        validateSize(numbers);
        this.numbers = new HashSet<>(numbers);
    }

    private void validateSize(Set<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(LOTTO_SIZE_MESSAGE);
        }
    }

    public Set<LottoNumber> getNumbers() {
        return new HashSet<>(numbers);
    }

    public static Lotto from(Set<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public int getMatchCount(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean contains(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

}
