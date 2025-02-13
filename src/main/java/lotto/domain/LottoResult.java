package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LottoResult {

    private final WinningLotto winningLotto;
    private final List<List<Integer>> lottoTickets;

    private Map<LottoPrize, Integer> lottoResult;
    private Double lottoProfitRate;

    public LottoResult(WinningLotto winningLotto, List<List<Integer>> lottoTickets) {
        this.winningLotto = winningLotto;
        this.lottoTickets = lottoTickets;
        initialize();
    }

    private void initialize() {
        this.lottoResult = new TreeMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            this.lottoResult.put(prize, 0);
        }
        this.lottoProfitRate = 0.0;
    }

    public void matchLottoTicketsResult() {
        for (List<Integer> lottoTicket : lottoTickets) {
            matchEachLottoTicketResult(lottoTicket);
        }
    }

    private void matchEachLottoTicketResult(List<Integer> lottoTicket) {
        int bonusBall = winningLotto.getBonusBall();

        boolean isBonusHit = matchBonusBall(bonusBall, lottoTicket);
        int winningNumbersHit = matchWinningNumbers(winningLotto, lottoTicket);

        LottoPrize lottoPrize = LottoPrize.findLottoPrize(winningNumbersHit, isBonusHit);
        lottoResult.put(lottoPrize, lottoResult.getOrDefault(lottoPrize, 0) + 1);
    }

    private boolean matchBonusBall(int bonusBall, List<Integer> lottoTicket) {
        return lottoTicket.contains(bonusBall);
    }

    private int matchWinningNumbers(WinningLotto winningLotto, List<Integer> lottoTicket) {
        Set<Integer> winningLottoSet = new HashSet<>(winningLotto.getWinningNumbers());
        Set<Integer> lottoTicketSet = new HashSet<>(lottoTicket);

        lottoTicketSet.retainAll(winningLottoSet);
        return lottoTicketSet.size();
    }

    public void calculateLottoProfitRate(LottoMoney lottoMoney) {
        int money = lottoMoney.getLottoMoney();
        int totalProfit = 0;

        for (Map.Entry<LottoPrize, Integer> entry : lottoResult.entrySet()) {
            LottoPrize lottoPrize = entry.getKey();
            int count = entry.getValue();
            int prize = lottoPrize.getPrize();

            totalProfit += count * prize;
        }
        this.lottoProfitRate = (double) totalProfit / money;
    }

    public Map<LottoPrize, Integer> getLottoResult() {
        return lottoResult;
    }

    public Double getLottoProfitRate() {
        return lottoProfitRate;
    }
}
