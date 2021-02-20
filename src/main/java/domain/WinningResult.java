package domain;

import domain.ticket.LottoTicket;

import java.util.List;

public class WinningResult {
    private final LottoMoney lottoMoney;
    private final List<Ranking> rankings;

    public WinningResult(final WinningNumbers winningNumbers, final List<LottoTicket> lottoTickets,
                         LottoMoney lottoMoney) {
        this.rankings = winningNumbers.getRankings(lottoTickets);
        this.lottoMoney = lottoMoney;
    }

    public int countNumberOfRank(final Ranking ranking) {
        return (int) rankings.stream()
                .filter(current -> current == ranking)
                .count();
    }

    public double getProfitRate() {
        return ((double) getTotalProfit()) / lottoMoney.getValue();
    }

    private int getTotalProfit() {
        return rankings.stream()
                .mapToInt(Ranking::getMoney)
                .sum();
    }
}