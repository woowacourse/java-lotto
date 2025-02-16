package service;

import domain.DrawResult;
import domain.LottoPrize;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.WinningStatistics;
import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    public WinningStatistics calculateWinningStatistics(LottoTickets lottoTickets,
                                                        DrawResult drawResult) {
        Map<LottoPrize, Integer> prizeCounter = new HashMap<>();
        initializePrizeCounter(prizeCounter);

        lottoTickets.getLottoTickets()
                .forEach(lottoTicket -> {
                    int countMatched = lottoTicket.countMatchedLottoNumbers(drawResult.winningLottoTicket());
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

    public double calculateProfit(WinningStatistics winningStatistics) {
        long sum = 0;
        long lottoTicketNumber = 0;
        Map<LottoPrize, Integer> prizeCounter = winningStatistics.prizeCounter();
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            sum += (long) prizeCounter.get(lottoPrize) * lottoPrize.getMoney();
            lottoTicketNumber += prizeCounter.get(lottoPrize);
        }
        return lottoTicketNumber == 0 ? 0 : ((double) sum / (lottoTicketNumber * LottoTicket.LOTTO_PRICE));
    }
}
