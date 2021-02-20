package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;

import java.util.*;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(List<Integer> numbers, int bonusBall) {
        lotto = new Lotto(new LottoNumber(numbers));
        this.bonusBall = new LottoNumber(bonusBall);
        validate(numbers, bonusBall);
    }

    private void validate(List<Integer> numbers, int bonusBall) {
        if (numbers.contains(bonusBall)) {
            throw new DuplicateLottoNumberException();
        }
    }

    public boolean contains(Integer number) {
        return lotto.contains(number);
    }

    public boolean matchBonusBall(Lotto lotto) {
        return lotto.contains(bonusBall);
    }
}
