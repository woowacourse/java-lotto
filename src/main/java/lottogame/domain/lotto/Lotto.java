package lottogame.domain.lotto;

import java.util.List;

public class Lotto {
    private final LottoNumber lottoNumber;

    public Lotto(LottoNumber lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public List<Integer> values() {
        return lottoNumber.values();
    }

    public int matchCount(WinningLotto winningLotto) {
        return lottoNumber.matchCount(winningLotto);
    }

    public boolean contains(Integer number) {
        return lottoNumber.contains(number);
    }

    public boolean contains(LottoNumber bonusBall) {
        return lottoNumber.contains(bonusBall);
    }
}
