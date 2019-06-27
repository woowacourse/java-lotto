package lotto.domain.dto;

import lotto.domain.model.Lotto;

public class LottoDTO {
    private int round;
    private Lotto lotto;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public void setLotto(final Lotto lotto) {
        this.lotto = lotto;
    }
}
