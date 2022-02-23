package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {
    private static final int INIT_COUNT = 0;
    private static final int COUNT_UNIT = 1;
    private static final int WINNING_FLAG = 3;
    private static final int SECOND_WINNING_COUNT = 5;
    private static final int TICKET_PRICE = 1000;

    private final Map<Integer, WinningPrize> winningInfo = new HashMap<>() {{
        put(6, WinningPrize.FIRST);
        put(5, WinningPrize.THIRD);
        put(4, WinningPrize.FOURTH);
        put(3, WinningPrize.FIFTH);
    }};
    private final LottoTickets lottoTickets;
    private final WinningTicket winningTicket;

    public LottoGame(LottoTickets lottoTickets, List<Integer> winningNumbers, int bonusNumber) {
        this.lottoTickets = lottoTickets;
        winningTicket = new WinningTicket(winningNumbers, bonusNumber);
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets.getTickets();
    }

    public Map<WinningPrize, Integer> winningResult() {
        Map<WinningPrize, Integer> result = initWinningCount();
        lottoTickets.getTickets().stream()
                .filter(this::isWinning)
                .forEach(lottoTicket -> countingWinningTicket(result, lottoTicket));
        return result;
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
        WinningPrize winningPrize = compareWin(lottoTicket);
        Integer winningPrizeCount = result.get(winningPrize) + COUNT_UNIT;
        result.put(winningPrize, winningPrizeCount);
    }

    private boolean isWinning(LottoTicket lottoTicket) {
        int count = winningTicket.compareMatchCount(lottoTicket);
        return count >= WINNING_FLAG;
    }

    private WinningPrize compareWin(LottoTicket lottoTicket) {
        int count = winningTicket.compareMatchCount(lottoTicket);
        if (count == SECOND_WINNING_COUNT && winningTicket.matchBonusNumber(lottoTicket)) {
            return WinningPrize.SECOND;
        }

        return winningInfo.get(count);
    }

    public Double getLottoRateOfReturn() {
        double totalReturn = calculateTotalReturn();
        double purchaseMoney = (double) lottoTickets.size() * TICKET_PRICE;
        return totalReturn / purchaseMoney;
    }

    private int calculateTotalReturn() {
        Map<WinningPrize, Integer> winningPrizeResult = winningResult();
        return winningPrizeResult.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
