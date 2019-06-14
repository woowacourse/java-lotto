package lotto.dto;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.WinningLottoState;

public class RoundResultDto {
    private int maxRoundNo;
    private int roundNo;
    private Money money;
    private LottoTickets lottoTickets;
    private WinningLotto winningLotto;
    private WinningLottoState winningLottoState;
    private double yield;

    public int getMaxRoundNo() {
        return maxRoundNo;
    }

    public void setMaxRoundNo(int maxRoundNo) {
        this.maxRoundNo = maxRoundNo;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }

    public void setLottoTickets(LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public WinningLottoState getWinningLottoState() {
        return winningLottoState;
    }

    public void setWinningLottoState(WinningLottoState winningLottoState) {
        this.winningLottoState = winningLottoState;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }
}
