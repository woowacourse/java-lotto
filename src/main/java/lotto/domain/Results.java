package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Results {
    private static final int MONEY_PER_LOTTO = 1000;

    private List<Result> results;
    private Lottos userLottos;
    private WinningLotto winningLotto;

    public Results(Lottos userLottos, WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
        this.userLottos = userLottos;
        this.results = new ArrayList<>();
    }

    public void calculateResults() {
        for (Lotto userLotto : userLottos.getLottos()) {
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
        return (getTotalEarning()) / (userLottos.getLottos().size() * 1000);
    }

    public int getWinningCount(WinningInfo winningInfo) {
        return results
                .stream()
                .filter(result -> result.isSameWinning(winningInfo))
                .collect(Collectors.toList())
                .size();
    }
}
