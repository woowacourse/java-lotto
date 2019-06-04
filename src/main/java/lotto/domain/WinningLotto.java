package lotto.domain;

import lotto.exception.DuplicateLottoNumberException;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
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
