package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Results {
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
}
