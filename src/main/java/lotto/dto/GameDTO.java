package lotto.dto;

import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

public class GameDTO {
    private int round;
    private Money money;
    private int numberOfManualLotto;
    private LottoTickets lottoTickets;
    private WinningLotto winningLotto;
    private LottoResult lottoResult;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public int getNumberOfManualLotto() {
        return numberOfManualLotto;
    }

    public void setNumberOfManualLotto(int numberOfManualLotto) {
        this.numberOfManualLotto = numberOfManualLotto;
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

    public LottoResult getLottoResult() {
        return lottoResult;
    }

    public void setLottoResult(LottoResult lottoResult) {
        this.lottoResult = lottoResult;
    }
}
