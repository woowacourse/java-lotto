package domain;

import util.LottoNumberGenerator;

public class LottoMachine {

    private final int money;
    private final LottoResult lottoResult;
    private final LottoTicket lottoTicket;

    public LottoMachine(int money, LottoNumberGenerator numberGenerator) {
        this.money = money;
        this.lottoResult = new LottoResult();
        this.lottoTicket = new LottoTicket(money/1000, numberGenerator);
    }

    public double calculateProfit() {
        return Math.floor((lottoResult.sumTotalPrice() / (double) money) * 100) / (double) 100;
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
