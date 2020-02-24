package lotto.domain;

import java.util.*;

public class Results {
    private static final int MONEY_PER_LOTTO = 1000;
    private static final int HUNDRED_PERCENT = 100;
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
        for(WinningInfo winningInfo : WinningInfo.values()){
            results.add(new Result(winningInfo));
        }
    }

    public List<Result> getResults() {
        return Collections.unmodifiableList(results);
    }

    public int getTotalEarning() {
        int totalEarning = 0;
        for (int i = RESULT_BASE; i < results.size(); i++) {
            totalEarning += results.get(i)
                    .getWinningInfo()
                    .getWinningPrice();
        }
        return totalEarning;
    }

    public int getEarningRate() {
        return (getTotalEarning()) / (userLottoTickets.size() * MONEY_PER_LOTTO) * HUNDRED_PERCENT;
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
