package lotto.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LottoResult {

    private final Map<LottoPrize, Integer> lottoResult;

    public LottoResult() {
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

    public void matchLottoTicketsResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        for (Lotto lottoTicket : lottoTickets.getLottoTickets()) {
            matchLottoTicketResult(winningLotto, lottoTicket);
        }
    }

    private void matchLottoTicketResult(WinningLotto winningLotto, Lotto lottoTicket) {
        int bonusBall = winningLotto.getBonusBall();
        boolean isBonusHit = lottoTicket.getLottoNumbers().contains(bonusBall);

        Set<Integer> winningLottoNumbers = new HashSet<>(winningLotto.getWinningNumbers());
        Set<Integer> lottoTicketNumbers = new HashSet<>(lottoTicket.getLottoNumbers());

        lottoTicketNumbers.retainAll(winningLottoNumbers);
        int winningNumbersHit = lottoTicketNumbers.size();

        LottoPrize lottoPrize = LottoPrize.findLottoPrize(winningNumbersHit, isBonusHit);
        lottoResult.put(lottoPrize, lottoResult.getOrDefault(lottoPrize, 0) + 1);
    }

    public Double calculateLottoProfitRate(LottoMoney lottoMoney) {
        int money = lottoMoney.amount();
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
