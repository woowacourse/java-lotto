package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Results {
    private static final int MONEY_PER_LOTTO = 1000;
    private static final int HUNDRED_PERCENT = 100;
    private static final int RESULT_BASE = 5;
    private List<Result> results;
    private List<LottoTicket> userLottoTickets;
    private WinningLottoTicket winningLotto;

    public Results(List<LottoTicket> userlottos, WinningLottoTicket winningLotto) {
        this.winningLotto = winningLotto;
        this.userLottoTickets = userlottos;
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
        results.add(new Result(WinningInfo.FIFTH));
        results.add(new Result(WinningInfo.FOURTH));
        results.add(new Result(WinningInfo.THIRD));
        results.add(new Result(WinningInfo.SECOND));
        results.add(new Result(WinningInfo.FIRST));
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


    public Map<WinningInfo, Long> getSummary() {
        calculateResults();
        Map<WinningInfo, Long> summary = results.stream()
                .collect(Collectors.groupingBy(Result::getWinningInfo, Collectors.counting()));
        summary.remove(WinningInfo.FAIL);
        return summary;
    }
}
