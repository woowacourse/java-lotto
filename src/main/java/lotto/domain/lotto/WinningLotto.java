package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.exception.DuplicateLottoNumberException;

import java.util.Objects;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (Objects.isNull(winningLotto) || Objects.isNull(bonusNumber)) {
            throw new NullPointerException();
        }

        if (winningLotto.contains(bonusNumber)) {
            throw new DuplicateLottoNumberException("로또숫자와 보너스숫자가 중복으로 입력되었습니다");
        }
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        return Rank.valueOf(lotto.match(winningLotto), lotto.contains(bonusNumber));
    }
}
