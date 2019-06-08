package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Winners {
    private static final int MONEY_UNIT = 1000;
    private static final int PERCENT = 100;

    private final List<Rank> rankResult;

    private Winners(Lottoes lottoes, WinningLotto winningLotto) {
        this.rankResult = new ArrayList<>();
        getResult(lottoes, winningLotto);
    }

    public static Winners create(Lottoes lottoes, WinningLotto winningLotto) {
        return new Winners(lottoes, winningLotto);
    }

    public List<Rank> getRankResult() {
        return rankResult;
    }

    public double calculateResultRate(int inputMoney) {
        double prizeSum = getSum();
        return (prizeSum / (inputMoney * MONEY_UNIT)) * PERCENT;
    }

    private void getResult(Lottoes lottoes, WinningLotto winningLotto) {
        for (int i = 0; i < lottoes.getSize(); i++) {
            rankResult.add(Rank.valueOf(winningLotto.match(lottoes.getIndexByLotto(i))
                    , winningLotto.matchBonus(lottoes.getIndexByLotto(i))));
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
