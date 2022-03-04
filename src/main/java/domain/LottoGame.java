package domain;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.WinningPrizeStrategy;

public class LottoGame {
    private final WinningPrizeStrategy winningPrizeStrategy;
    private final NumberGenerateStrategy numberGenerateStrategy;

    public LottoGame(NumberGenerateStrategy numberGenerateStrategy, WinningPrizeStrategy winningPrizeStrategy) {
        this.numberGenerateStrategy = numberGenerateStrategy;
        this.winningPrizeStrategy = winningPrizeStrategy;
    }

    public LottoTickets purchaseAutoTickets(LottoMoney purchaseMoney) {
        return LottoTickets.of(purchaseMoney, numberGenerateStrategy);
    }

    public LottoResult createWinningResult(LottoTickets lottoTickets, WinningTicket winningTicket) {
        return LottoResult.of(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    public int findMatchCount(WinningPrize winningPrize) {
        return winningPrizeStrategy.findMatchCount(winningPrize);
    }

    public boolean findMatchBonus(WinningPrize winningPrize) {
        return winningPrizeStrategy.findMatchBonus(winningPrize);
    }
}
