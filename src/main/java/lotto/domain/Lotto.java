package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public static Lotto create(final List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (!lottoNumbers.equals(sorted(lottoNumbers))) {
            throw new IllegalStateException("오름차순이 아닙니다.");
        }

        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalStateException("중복은 불가능합니다.");
        }
    }

    private List<LottoNumber> sorted(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }

    //TODO WinnerLotto와 Lotto를 비교하는 메서드
}
