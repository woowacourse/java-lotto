package domain;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.WinningPrizeStrategy;

public class LottoGame {
    public static final int TICKET_PRICE = 1000;

    private final WinningPrizeStrategy winningPrizeStrategy;
    private final NumberGenerateStrategy numberGenerateStrategy;

    public LottoGame(NumberGenerateStrategy numberGenerateStrategy, WinningPrizeStrategy winningPrizeStrategy) {
        this.numberGenerateStrategy = numberGenerateStrategy;
        this.winningPrizeStrategy = winningPrizeStrategy;
    }

    public LottoTickets purchaseAutoTickets(LottoMoney purchaseMoney) {
        return LottoTickets.generateTickets(purchaseMoney, numberGenerateStrategy);
    }

    public LottoResult createWinningResult(LottoTickets lottoTickets, WinningTicket winningTicket) {
        return LottoResult.toExtract(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    public int findMatchCount(WinningPrize winningPrize) {
        return winningPrizeStrategy.findMatchCount(winningPrize);
    }

    public boolean findMatchBonus(WinningPrize winningPrize) {
        return winningPrizeStrategy.findMatchBonus(winningPrize);
    }
}
