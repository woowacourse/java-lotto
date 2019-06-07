package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Winners {
    private static final int MONEY_UNIT = 1000;
    private static final int PERCENT = 100;

    private final List<Rank> rankResult;

    private Winners(MyLotto myLotto, WinningLotto winningLotto) {
        this.rankResult = new ArrayList<>();
        getResult(myLotto, winningLotto);
    }

    public static Winners create(MyLotto myLotto, WinningLotto winningLotto) {
        return new Winners(myLotto, winningLotto);
    }

    public List<Rank> getRankResult() {
        return rankResult;
    }

    public double calculateResultRate(int inputMoney) {
        double prizeSum = getSum();
        return (prizeSum / (inputMoney * MONEY_UNIT)) * PERCENT;
    }

    private void getResult(MyLotto myLotto, WinningLotto winningLotto) {
        for (int i = 0; i < myLotto.getSize(); i++) {
            rankResult.add(Rank.valueOf(winningLotto.match(myLotto.getIndexByLotto(i))
                    , winningLotto.matchBonus(myLotto.getIndexByLotto(i))));
        }
    }

    private double getSum() {
        double sum = 0;
        List<Integer> prizes = Rank.providePrizeResult(rankResult);

        for (Integer prize : prizes) {
            sum += prize;
        }

        return sum;
    }
}
