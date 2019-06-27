package lotto.domain.dto;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;

public class WinningLottoDTO {
    private int round;
    private Lotto winningLotto;
    private Number bonusNum;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(final Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public Number getBonusNum() {
        return bonusNum;
    }

    public void setBonusNum(final Number bonusNum) {
        this.bonusNum = bonusNum;
    }
}
