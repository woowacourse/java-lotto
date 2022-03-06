package domain;

import domain.strategy.WinningPrizeStrategy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinningResult {
    private static final int INIT_COUNT = 0;
    private static final int COUNT_UNIT = 1;

    private final Map<WinningPrize, Integer> countOfWinning;

    private WinningResult(Map<WinningPrize, Integer> countOfWinning) {
        this.countOfWinning = countOfWinning;
    }

    public static WinningResult toExtract(LottoTickets lottoTickets,
                                          WinningTicket winningTicket,
                                          WinningPrizeStrategy winningPrizeStrategy) {
        Map<WinningPrize, Integer> countOfWinning = countWinning(lottoTickets, winningTicket, winningPrizeStrategy);
        return new WinningResult(countOfWinning);
    }

    private static Map<WinningPrize, Integer> countWinning(LottoTickets lottoTickets,
                                                           WinningTicket winningTicket,
                                                           WinningPrizeStrategy winningPrizeStrategy) {
        Map<WinningPrize, Integer> countOfWinning = initWinningCount();
        lottoTickets.getTickets()
                .forEach(lottoTicket -> countWinningTicket(countOfWinning, lottoTicket, winningTicket,
                        winningPrizeStrategy));
        return countOfWinning;
    }

    private static LinkedHashMap<WinningPrize, Integer> initWinningCount() {
        return new LinkedHashMap<>() {{
            put(WinningPrize.FIFTH, INIT_COUNT);
            put(WinningPrize.FOURTH, INIT_COUNT);
            put(WinningPrize.THIRD, INIT_COUNT);
            put(WinningPrize.SECOND, INIT_COUNT);
            put(WinningPrize.FIRST, INIT_COUNT);
        }};
    }

    private static void countWinningTicket(Map<WinningPrize, Integer> result,
                                           LottoTicket lottoTicket,
                                           WinningTicket winningTicket,
                                           WinningPrizeStrategy winningPrizeStrategy) {
        int matchCount = winningTicket.compareMatchCount(lottoTicket);
        boolean matchBonus = winningTicket.isMatchBonusNumber(lottoTicket);
        WinningPrize winningPrize = winningPrizeStrategy.findWinningPrize(matchCount, matchBonus);
        if (!winningPrize.equals(WinningPrize.NONE)) {
            Integer winningPrizeCount = result.get(winningPrize) + COUNT_UNIT;
            result.put(winningPrize, winningPrizeCount);
        }
    }

    public double calculateLottoRateOfReturn(LottoMoney purchaseMoney) {
        int totalReturn = sumTotalReturn();
        return totalReturn / (double) (purchaseMoney.getValue());
    }

    private int sumTotalReturn() {
        return countOfWinning.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public Map<WinningPrize, Integer> getCountOfWinning() {
        return Collections.unmodifiableMap(countOfWinning);
    }
}
