package lotto.domain;

import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_SIZE_STANDARD = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        checkNumberSize(lottoNumbers);
        checkDuplicateNumber(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkNumberSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE_STANDARD) {
            throw new IllegalArgumentException("[ERROR] 로또 넘버는 6개가 입력되어야 합니다.");
        }
    }

    private void checkDuplicateNumber(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != calculateDistinctSize(lottoNumbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 넘버는 중복될 수 없습니다.");
        }
    }

    private long calculateDistinctSize(final List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count();
    }

    public boolean containNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int match(final Lotto compareLotto) {
        return (int) lottoNumbers.stream()
                .filter(compareLotto::containNumber)
                .count();
    }
}
