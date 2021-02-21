package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또는 6개의 번호가 있어야합니다.");
        }

        if (!lottoNumbers.stream().allMatch((i) -> i >= 1 && i <= 45)) {
            throw new IllegalArgumentException("유효하지 않은 로또 번호입니다.");
        }
    }

    public boolean isBonusMatch(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotto)) return false;

        Lotto lotto = (Lotto) o;

        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }
}
