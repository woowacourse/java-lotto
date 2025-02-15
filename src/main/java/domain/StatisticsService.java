package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    public WinningStatistics calculateWinningStatistics(List<LottoTicket> lottoTickets, LottoTicket winningLottoTicket,
                                                        int bonusNumber) {
        Map<LottoPrize, Integer> prizeCounter = new HashMap<>();
        initializePrizeCounter(prizeCounter);

        lottoTickets.forEach(lottoTicket -> {
            int countMatched = lottoTicket.countMatchedNumbers(winningLottoTicket.getLottoNumbers());
            boolean isBonusNumberMatched = lottoTicket.hasBonusNumber(bonusNumber);
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
