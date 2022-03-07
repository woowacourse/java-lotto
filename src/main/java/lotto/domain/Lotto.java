package lotto.domain;

import java.util.List;
import java.util.Set;
import lotto.domain.vo.LottoNumber;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = Set.copyOf(lottoNumbers);
        validateSize(lottoNumbers);
    }

    public static Lotto of(List<LottoNumber> lottoNumbers) {
        validateDuplicateNumbers(lottoNumbers);
        return new Lotto(Set.copyOf(lottoNumbers));
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

    private static void validateDuplicateNumbers(List<LottoNumber> lottoNumbers) {
        if (Set.copyOf(lottoNumbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6자리 이어야 한다.");
        }
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
