package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Results {
    private static final int MONEY_PER_LOTTO = 1000;

    private List<Result> results;
    private List<Lotto> userLotties;
    private WinningLotto winningLotto;

    public Results(List<Lotto> userLotties, WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
        this.userLotties = userLotties;
        this.results = new ArrayList<>();
    }

    public void calculateResults() {
        for (Lotto userLotto : userLotties) {
            Result result = new Result();
            result.calculate(winningLotto, userLotto);
            results.add(result);
        }
    }

    public List<Result> getResults() {
        return results;
    }

    public int getTotalEarning() {
        int totalEarning = 0;
        for (int i = 0; i < results.size(); i++) {
            totalEarning += results.get(i).getWinningInfo().getWinningPrice();
        }
        return totalEarning;
    }

    public int getEarningRate() {
        return (getTotalEarning()) / (userLotties.size() * 1000);
    }
}
