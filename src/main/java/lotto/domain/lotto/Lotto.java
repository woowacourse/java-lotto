package lotto.domain.lotto;

import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidCountOfLottoNumberException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;
    private final boolean isAuto;

    public Lotto(List<Integer> lottoNumbers, boolean isAuto) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(toList());
        this.isAuto = isAuto;
    }

    private void checkLottoNumbers(List<Integer> lottoNumbers) {
        if (Objects.isNull(lottoNumbers) || lottoNumbers.isEmpty()) {
            throw new NullPointerException();
        }

        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new DuplicateLottoNumberException("중복되는 숫자가 있습니다");
        }

        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new InvalidCountOfLottoNumberException("로또 번호의 개수가 올바르지 않습니다.");
        }
    }

    public int countMatchedLottoNumber(Lotto lotto) {
        return (int) lotto.lottoNumbers.stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    boolean contains(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public boolean isAuto() {
        return isAuto;
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
