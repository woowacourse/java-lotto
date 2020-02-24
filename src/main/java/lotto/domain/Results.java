package lotto.domain;

import java.math.BigInteger;
import java.util.*;

public class Results {
    private static final String MONEY_PER_LOTTO = "1000";
    private static final String HUNDRED_PERCENT = "100";
    private static final int RESULT_BASE = 6;
    private final List<Result> results;
    private final List<LottoTicket> userLottoTickets;
    private final WinningLottoTicket winningLotto;

    public Results(final LottoTickets lottoTickets, final WinningLottoTicket winningLotto) {
        this.winningLotto = winningLotto;
        this.userLottoTickets = lottoTickets.getLottoTickets();
        this.results = new ArrayList<>();
    }

    public void calculateResults() {
        addBlankResults();
        for (LottoTicket userLottoTicket : userLottoTickets) {
            Result result = new Result();
            result.calculate(winningLotto, userLottoTicket);
            results.add(result);
        }
    }

    private void addBlankResults() {
        for (WinningInfo winningInfo : WinningInfo.values()) {
            results.add(new Result(winningInfo));
        }
    }

    public List<Result> getResults() {
        return Collections.unmodifiableList(results);
    }

    public BigInteger getTotalEarning() {
        BigInteger totalEarning = new BigInteger("0");
        for (int i = RESULT_BASE; i < results.size(); i++) {
            int nowEarning = results.get(i)
                    .getWinningInfo()
                    .getWinningPrice();
            totalEarning = totalEarning.add(new BigInteger(String.valueOf(nowEarning)));
        }
        return totalEarning;
    }

    public BigInteger getEarningRate() {
        BigInteger totalEarning = getTotalEarning();
        BigInteger percent = new BigInteger(HUNDRED_PERCENT);
        BigInteger userLottoTicketsSize = new BigInteger(String.valueOf(userLottoTickets.size()));
        BigInteger moneyPerLotto = new BigInteger(MONEY_PER_LOTTO);
        BigInteger purchaseAmount = userLottoTicketsSize.multiply(moneyPerLotto);

        return totalEarning.multiply(percent)
                .divide(purchaseAmount);
    }

    public Map<WinningInfo, Integer> getSummary() {
        Map<WinningInfo, Integer> summary = new HashMap<>();

        calculateResults();
        for (Result result : results) {
            int count = getCount(summary, result);
            WinningInfo winningInfo = result.getWinningInfo();
            summary.put(winningInfo, count + 1);
        }
        summary.remove(WinningInfo.FAIL);
        return summary;
    }

    private int getCount(Map<WinningInfo, Integer> summary, Result result) {
        WinningInfo winningInfo = result.getWinningInfo();

        if (summary.containsKey(winningInfo)) {
            return summary.get(winningInfo);
        }
        return 0;
    }
}
