package domain;

import java.util.Collections;
import java.util.Map;

public class WinningResult {
    private static final int COUNT_UNIT = 1;

    private final Map<WinningPrize, Integer> countOfWinning;

    private WinningResult(Map<WinningPrize, Integer> countOfWinning) {
        this.countOfWinning = countOfWinning;
    }

    public static WinningResult toExtract(LottoTickets lottoTickets, WinningTicket winningTicket) {
        Map<WinningPrize, Integer> countOfWinning = countWinning(lottoTickets, winningTicket);
        return new WinningResult(countOfWinning);
    }

    private static Map<WinningPrize, Integer> countWinning(LottoTickets lottoTickets,
                                                           WinningTicket winningTicket) {
        Map<WinningPrize, Integer> countOfWinning = WinningPrize.initResultMap();
        lottoTickets.getTickets()
                .forEach(lottoTicket -> countWinningTicket(countOfWinning, lottoTicket, winningTicket));
        return countOfWinning;
    }

    private static void countWinningTicket(Map<WinningPrize, Integer> result, LottoTicket lottoTicket,
                                           WinningTicket winningTicket) {
        int matchCount = winningTicket.compareMatchCount(lottoTicket);
        boolean matchBonus = winningTicket.isMatchBonusNumber(lottoTicket);
        WinningPrize winningPrize = WinningPrize.findWinningPrize(matchCount, matchBonus);
        if (!winningPrize.equals(WinningPrize.NONE)) {
            result.merge(winningPrize, COUNT_UNIT, Integer::sum);
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
