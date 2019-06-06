package lotto.domain.lotto;

import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidCountOfLottoNumberException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;
    private final Set<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toSet());

        if (this.lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new DuplicateLottoNumberException("중복되는 숫자가 있습니다");
        }
    }

    private void checkLottoNumbers(List<Integer> lottoNumbers) {
        if (Objects.isNull(lottoNumbers) || lottoNumbers.isEmpty()) {
            throw new NullPointerException();
        }

        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new InvalidCountOfLottoNumberException("로또 번호의 개수가 올바르지 않습니다.");
        }
    }

    public int match(Lotto lotto) {
        if (Objects.isNull(lotto)) {
            throw new NullPointerException();
        }

        return (int) lotto.lottoNumbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    public boolean contains(LottoNumber bonusNumber) {
        if (Objects.isNull(bonusNumber)) {
            throw new NullPointerException();
        }

        return lottoNumbers.contains(bonusNumber);
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

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
