package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LottoResult {

    private final WinningLotto winningLotto;
    private final List<Set<Integer>> lottoTickets;

    private final Map<LottoPrize, Integer> lottoResult;

    public LottoResult(WinningLotto winningLotto, List<Set<Integer>> lottoTickets) {
        this.winningLotto = winningLotto;
        this.lottoTickets = lottoTickets;
        this.lottoResult = new TreeMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            addLottoResultPrize(prize);
        }
    }

    private void addLottoResultPrize(LottoPrize prize) {
        if (prize != LottoPrize.MISS) {
            this.lottoResult.put(prize, 0);
        }
    }

    public void matchLottoTicketsResult() {
        for (Set<Integer> lottoTicket : lottoTickets) {
            matchLottoTicketResult(lottoTicket);
        }
    }

    private void matchLottoTicketResult(Set<Integer> lottoTicket) {
        int bonusBall = winningLotto.getBonusBall();
        boolean isBonusHit = lottoTicket.contains(bonusBall);

        Set<Integer> winningLottoSet = new HashSet<>(winningLotto.getWinningNumbers());
        Set<Integer> lottoTicketSet = new HashSet<>(lottoTicket);

        lottoTicketSet.retainAll(winningLottoSet);
        int winningNumbersHit = lottoTicketSet.size();

        LottoPrize lottoPrize = LottoPrize.findLottoPrize(winningNumbersHit, isBonusHit);
        lottoResult.put(lottoPrize, lottoResult.getOrDefault(lottoPrize, 0) + 1);
    }

    public Double calculateLottoProfitRate(LottoMoney lottoMoney) {
        int money = lottoMoney.getLottoMoney();
        int totalProfit = 0;
        for (Map.Entry<LottoPrize, Integer> entry : lottoResult.entrySet()) {
            LottoPrize lottoPrize = entry.getKey();
            int count = entry.getValue();
            int prize = lottoPrize.getPrize();
            totalProfit += count * prize;
        }
        return (double) totalProfit / money;
    }

    public Map<LottoPrize, Integer> getLottoResult() {
        return lottoResult;
    }
}
