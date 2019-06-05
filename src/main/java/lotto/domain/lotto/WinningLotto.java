package lotto.domain.lotto;

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

    public int match(Lotto lotto) {
        return lotto.match(winningLotto);
    }

    public boolean contains(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
