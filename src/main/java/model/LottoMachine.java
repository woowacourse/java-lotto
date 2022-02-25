package model;

import java.util.List;
import java.util.Map;

import model.LottoNumberGenerator.GenerateStrategy;
import model.lottotickets.LottoTickets;
import model.money.Money;
import model.winning.WinningNumbers;
import model.winning.Rank;
import model.winning.Statistics;

public class LottoMachine {
    private Money money;
    private LottoTickets lottoTickets;
    private WinningNumbers winningNumbers;
    private Statistics statistics;

    public void insertMoney(final int money) {
        this.money = new Money(money);
    }

    public void purchaseLottoTickets(final GenerateStrategy generateStrategy) {
        final int purchaseCount = money.generatePurchaseCount();
        lottoTickets = new LottoTickets(purchaseCount, generateStrategy);
    }

    public List<List<Integer>> lottoTickets() {
        return lottoTickets.tickets();
    }

    public void insertWinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        this.winningNumbers = new WinningNumbers(winningNumbers, bonusNumber);
    }

    public Map<Rank, Integer> winningResult() {
        statistics = lottoTickets.winningResult(winningNumbers.winningNumbers(), winningNumbers.bonusNumber());
        return statistics.getStatistics();
    }

    public Double lottoRateOfReturn() {
        return statistics.getLottoRateOfReturn(money.getMoney());
    }
}
