package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoGame {
    private static final int INIT_COUNT = 0;
    private static final int COUNT_UNIT = 1;
    private static final int WINNING_FLAG = 3;
    private static final int TICKET_PRICE = 1000;

    private final LottoTickets lottoTickets;
    private final WinningTicket winningTicket;
    private final WinningPrizeStrategy winningPrizeStrategy;

    public LottoGame(LottoTickets lottoTickets,
                     Set<Integer> winningNumbers,
                     int bonusNumber,
                     WinningPrizeStrategy winningPrizeStrategy) {
        this.lottoTickets = lottoTickets;
        winningTicket = new WinningTicket(winningNumbers, bonusNumber);
        this.winningPrizeStrategy = winningPrizeStrategy;
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets.getTickets();
    }

    public Map<WinningPrize, Integer> winningResults() {
        Map<WinningPrize, Integer> result = initWinningCount();
        lottoTickets.getTickets().stream()
                .filter(this::isWinning)
                .forEach(lottoTicket -> countingWinningTicket(result, lottoTicket));
        return result;
    }

    public Double getLottoRateOfReturn() {
        double totalReturn = calculateTotalReturn();
        double purchaseMoney = (double) lottoTickets.size() * TICKET_PRICE;
        return totalReturn / purchaseMoney;
    }

    public int matchCount(WinningPrize winningPrize) {
        return winningPrizeStrategy.matchCount(winningPrize);
    }

    public boolean matchBonus(WinningPrize winningPrize) {
        return winningPrizeStrategy.matchBonus(winningPrize);
    }

    private LinkedHashMap<WinningPrize, Integer> initWinningCount() {
        return new LinkedHashMap<>() {{
            put(WinningPrize.FIFTH, INIT_COUNT);
            put(WinningPrize.FOURTH, INIT_COUNT);
            put(WinningPrize.THIRD, INIT_COUNT);
            put(WinningPrize.SECOND, INIT_COUNT);
            put(WinningPrize.FIRST, INIT_COUNT);
        }};
    }

    private void countingWinningTicket(Map<WinningPrize, Integer> result, LottoTicket lottoTicket) {
        int matchCount = winningTicket.compareMatchCount(lottoTicket);
        boolean matchBonus = winningTicket.matchBonusNumber(lottoTicket);
        WinningPrize winningPrize = winningPrizeStrategy.winningPrize(matchCount, matchBonus);
        Integer winningPrizeCount = result.get(winningPrize) + COUNT_UNIT;
        result.put(winningPrize, winningPrizeCount);
    }

    private boolean isWinning(LottoTicket lottoTicket) {
        int count = winningTicket.compareMatchCount(lottoTicket);
        return count >= WINNING_FLAG;
    }

    private int calculateTotalReturn() {
        Map<WinningPrize, Integer> winningPrizeResult = winningResults();
        return winningPrizeResult.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
