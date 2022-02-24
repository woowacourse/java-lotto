package model;

import java.util.List;
import java.util.Map;

public class LottoGame {
    private Money money;
    private LottoTickets lottoTickets;
    private WinningNumbers winningNumbers;
    private WinningStatistics statistics;

    public void insertMoney(final int money) {
        this.money = new Money(money);
    }

    public void purchaseLottoTickets(final GenerateStrategy generateStrategy) {
        final int purchaseCount = money.generatePurchaseCount();
        lottoTickets = new LottoTickets(purchaseCount, generateStrategy);
    }

    public List<LottoTicket> lottoTickets() {
        return lottoTickets.tickets();
    }

    public void insertWinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        this.winningNumbers = new WinningNumbers(winningNumbers, bonusNumber);
    }

    public Map<WinningRank, Integer> winningResult() {
        statistics = winningNumbers.winningResult(lottoTickets);
        return statistics.getStatistics();
    }

    public Double lottoRateOfReturn() {
        return statistics.getLottoRateOfReturn(money.getMoney());
    }
}
