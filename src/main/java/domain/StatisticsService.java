package domain;

import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    public WinningStatistics calculateWinningStatistics(LottoTickets lottoTickets,
                                                        DrawResult drawResult) {
        Map<LottoPrize, Integer> prizeCounter = new HashMap<>();
        initializePrizeCounter(prizeCounter);

        lottoTickets.getLottoTickets()
                .forEach(lottoTicket -> {
                    int countMatched = lottoTicket.countMatchedLottoNumbers(drawResult.lottoNumbers());
                    boolean isBonusNumberMatched = lottoTicket.containsLottoNumber(drawResult.bonusNumber());
                    LottoPrize prize = LottoPrize.getLottoPrize(countMatched, isBonusNumberMatched);
                    int prizeCount = prizeCounter.get(prize);
                    prizeCounter.put(prize, prizeCount + 1);
                });
        return new WinningStatistics(prizeCounter);
    }

    private void initializePrizeCounter(Map<LottoPrize, Integer> prizeCounter) {
        for (LottoPrize prize : LottoPrize.values()) {
            prizeCounter.put(prize, 0);
        }
    }

    public Profit calculateProfit(WinningStatistics winningStatistics) {
        long sum = 0;
        long lottoTicketNumber = 0;
        Map<LottoPrize, Integer> prizeCounter = winningStatistics.getPrizeCounter();
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            sum += (long) prizeCounter.get(lottoPrize) * lottoPrize.getMoney();
            lottoTicketNumber += prizeCounter.get(lottoPrize);
        }
        return new Profit((double) sum / (lottoTicketNumber * LottoTicket.LOTTO_PRICE));
    }
}
