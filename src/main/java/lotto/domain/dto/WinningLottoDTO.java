package lotto.domain.dto;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;

public class WinningLottoDTO {
    private int round;
    private Number bonusNumber;
    private Lotto winningLotto;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public Number getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(final Number bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(final Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }
}
