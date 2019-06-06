package domain;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    static final int PRICE = 1000;
    private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

    private Set<LottoNumber> lottoNumbers;

    Lotto(Set<LottoNumber> lottoNumbers) {
        validateNumberOf(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateNumberOf(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != NUMBER_OF_LOTTO_NUMBERS) {
            throw new IllegalArgumentException("로또를 발급받으려면 서로 다른 6개의 숫자가 있어야 합니다.");
        }
    }

    Rank matchUpLottoNumbersWith(Lotto winningLotto) {
        int countOfMatchingNumbers = lottoNumbers.stream()
                .filter(winningLotto::contains)
                .collect(Collectors.toList())
                .size();

        return Rank.of(countOfMatchingNumbers);
    }

    private boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
