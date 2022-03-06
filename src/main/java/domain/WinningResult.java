package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningResult {
    private static final int INIT_COUNT = 0;
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
        Map<WinningPrize, Integer> countOfWinning = initWinningCount();
        lottoTickets.getTickets()
                .forEach(lottoTicket -> countWinningTicket(countOfWinning, lottoTicket, winningTicket));
        return countOfWinning;
    }

    private static LinkedHashMap<WinningPrize, Integer> initWinningCount() {
        Set<WinningPrize> prizes = Arrays.stream(WinningPrize.values())
                .filter(winningPrize -> winningPrize != WinningPrize.NONE)
                .collect(Collectors.toSet());
        LinkedHashMap<WinningPrize, Integer> result = new LinkedHashMap<>();
        prizes.forEach(winningPrize -> result.put(winningPrize, INIT_COUNT));
        return result;
    }

    private static void countWinningTicket(Map<WinningPrize, Integer> result, LottoTicket lottoTicket,
                                           WinningTicket winningTicket) {
        int matchCount = winningTicket.compareMatchCount(lottoTicket);
        boolean matchBonus = winningTicket.isMatchBonusNumber(lottoTicket);
        WinningPrize winningPrize = WinningPrize.findWinningPrize(matchCount, matchBonus);
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
