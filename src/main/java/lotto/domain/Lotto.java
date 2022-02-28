package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.vo.LottoNumber;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicateNumbers(lottoNumbers);
        this.lottoNumbers = new HashSet<>(lottoNumbers);
    }

    public int countMatchNumbers(Lotto lotto) {
        return (int)lotto.lottoNumbers.stream()
            .filter(this::contains)
            .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getNumbers() {
        return Set.copyOf(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6자리 이어야 한다.");
        }
    }

    private void validateDuplicateNumbers(List<LottoNumber> lottoNumbers) {
        if (Set.copyOf(lottoNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
