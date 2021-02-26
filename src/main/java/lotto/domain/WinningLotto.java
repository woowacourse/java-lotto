package lotto.domain;

import lotto.exception.LottoNumberException;
import lotto.util.LottoGenerator;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final List<Integer> winningNumbers, final LottoNumber bonusNumber) {
        Lotto winningLotto = LottoGenerator.generate(winningNumbers);
        if (winningLotto.contains(bonusNumber)) {
            throw new LottoNumberException("중복된 번호입니다.");
        }
        this.winningNumbers = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank matchRank(final Lotto lotto) {
        return LottoRank.of(winningNumbers.matchCount(lotto), lotto.contains(bonusNumber));
    }
}
