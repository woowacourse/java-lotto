package lotto.domain;

import java.util.Set;

public class Lotto {
    private static final String INVALIDATE_LOTTO_NUMBERS_ERROR_MSG = "로또 번호는 중복되지 않는 6개의 숫자여야 합니다.";
    private static final int  LOTTO_NUMBERS_SIZE = 6;
    protected Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        validateDistinctNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDistinctNumbers(Set<LottoNumber> inputNumbers) {
        if (inputNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALIDATE_LOTTO_NUMBERS_ERROR_MSG);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int compare(Lotto lotto) {
        return Math.toIntExact(lotto.getLottoNumbers()
                .stream()
                .filter(lottoNumber -> this.hasLottoNumber(lottoNumber))
                .count());
    }

    public boolean hasLottoNumber(LottoNumber compareLottoNumber) {
        return this.getLottoNumbers()
                .contains(compareLottoNumber);
    }
}
