package lotto.domain;

import static lotto.util.Constant.DEFAULT_VALUE_ZERO;
import static lotto.util.Constant.UPDATE_VALUE_ONE;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoResult {

    private Map<LottoPrize, Integer> lottoResult;
    private Double lottoProfitRate;

    public LottoResult() {
        initialize();
    }

    private void initialize() {
        this.lottoResult = new TreeMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            this.lottoResult.put(prize, DEFAULT_VALUE_ZERO);
        }
        this.lottoProfitRate = (double) DEFAULT_VALUE_ZERO;
    }

    public void matchLottoTicketsResult(WinningLotto winningLotto, List<Lotto> lottoTickets) {
        for (Lotto lottoTicket : lottoTickets) {
            matchEachLottoTicketResult(winningLotto, lottoTicket);
        }
    }

    private void matchEachLottoTicketResult(WinningLotto winningLotto, Lotto lottoTicket) {
        boolean isBonusHit = winningLotto.matchBonusBall(lottoTicket);
        int winningNumbersHit = winningLotto.matchWinningNumbers(lottoTicket);

        LottoPrize lottoPrize = LottoPrize.findLottoPrize(winningNumbersHit, isBonusHit);
        lottoResult.put(lottoPrize, lottoResult.getOrDefault(lottoPrize, DEFAULT_VALUE_ZERO) + UPDATE_VALUE_ONE);
    }

    public void calculateLottoProfitRate(LottoMoney lottoMoney) {
        int money = lottoMoney.getLottoMoney();
        int totalProfit = DEFAULT_VALUE_ZERO;

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
