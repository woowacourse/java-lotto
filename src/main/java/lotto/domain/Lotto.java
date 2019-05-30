package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;
    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (Objects.isNull(lottoNumbers) || lottoNumbers.isEmpty()) {
            throw new NullPointerException();
        }

        if (new HashSet<>(lottoNumbers).size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복되는 숫자가 있습니다");
        }

        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호의 개수가 올바르지 않습니다.");
        }
    }

    public int match(Lotto lotto) {
        return (int) lotto.lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumbers.contains(lottoNumber))
                .count();
    }

    public boolean contains(BonusNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber.getNumber());
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
