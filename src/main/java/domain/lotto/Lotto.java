package domain.lotto;

import domain.result.LottoRank;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> findLottoNumbers() {
        List<LottoNumber> lottoNumbers = this.lottoNumbers.getLottoNumbers();
        return Collections.unmodifiableList(lottoNumbers);
    }

    public LottoRank findMatchesNumber(WinningLotto winningLotto) {
        return winningLotto.winningMatchCount(lottoNumbers);
    }
}
