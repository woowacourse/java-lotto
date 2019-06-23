package lotto.dto;

import lotto.domain.LottoGame;
import lotto.domain.Money;

public class LottoGameDto {

    private boolean isGenerated;
    private int round;
    private Money money;
    private LottoGame lottoGame;

    public boolean getIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(boolean generated) {
        isGenerated = generated;
    }

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

    public LottoGame getLottoGame() {
        return lottoGame;
    }

    public void setLottoGame(LottoGame lottoGame) {
        this.lottoGame = lottoGame;
    }
}
