package domain;

import util.LottoNumberGenerator;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private static final int SECOND_DECIMAL_DIGIT = 100;

    private final int money;
    private final LottoResult lottoResult;
    private final LottoTicket lottoTicket;

    public LottoMachine(int money, LottoNumberGenerator numberGenerator) {
        this.money = money;
        this.lottoResult = new LottoResult();
        this.lottoTicket = new LottoTicket(money / LOTTO_PRICE, numberGenerator);
    }

    public double calculateProfit() {
        return Math.floor( //TODO
                (lottoResult.sumTotalPrice() / (double) money) * SECOND_DECIMAL_DIGIT
        ) / (double) SECOND_DECIMAL_DIGIT;
    }

    public void putLotto(LottoRank rank) {
        this.lottoResult.putLottoRank(rank);
    }

    public LottoResult getResults(WinningLotto winningLotto) {
        for (Lotto lotto : lottoTicket.getLottos()) {
            lottoResult.putLottoRank(winningLotto.countLottoRank(lotto));
        }
        return lottoResult;
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }
}
