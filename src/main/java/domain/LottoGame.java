package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoGame {
    private final LottoTickets lottoTickets;
    private final WinningResult winningResult;

    public LottoGame(LottoTickets lottoTickets,
                     WinningTicket winningTicket,
                     WinningPrizeStrategy winningPrizeStrategy) {
        this.lottoTickets = lottoTickets;
        this.winningResult = WinningResult.from(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }

    public double getLottoRateOfReturn() {
        double totalReturn = calculateTotalReturn();
        double purchaseMoney = (double) lottoTickets.getTickets().size() * LottoTickets.TICKET_PRICE;
        return totalReturn / purchaseMoney;
    }

    private int calculateTotalReturn() {
        return winningResult.getCountOfWinning()
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
