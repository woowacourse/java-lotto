package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_PURCHASE_MONEY = 1000;

    private static final int LOTTO_NUMBER_SIZE_STANDARD = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(final Set<LottoNumber> lottoNumbers) {
        checkNumberSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkNumberSize(final Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE_STANDARD) {
            throw new IllegalArgumentException("[ERROR] 로또 넘버는 6개가 입력되어야 합니다.");
        }
    }

    public boolean containNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int calculateMatchCount(final Lotto compareLotto) {
        return (int) lottoNumbers.stream()
                .filter(compareLotto::containNumber)
                .count();
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Set.copyOf(lottoNumbers);
    }
}
