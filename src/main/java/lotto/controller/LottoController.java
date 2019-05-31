package lotto.controller;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public Lottos buyLottos(int round) {
        List<Lotto> myLottos = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            NumberGenerator numberGenerator = NumberGenerator.create();
            myLottos.add(Lotto.create(numberGenerator.getNumbers()));
        }

        return Lottos.create(myLottos);
    }

    public List<Rank> getResult(Lottos lottos, WinnerNumber winnerNumber) {
        List<Rank> ranks = new ArrayList<>();

        for (int i = 0; i < lottos.getSize(); i++) {
            ranks.add(Rank.valueOf(winnerNumber.getCount(lottos.getIndexByLotto(i))));
        }

        return ranks;
    }

    public double getReturnRate(List<Rank> ranks, int inputMoney) {
        double sum = getSum(ranks);

        return calculateResutnRate(inputMoney, sum);
    }

    private double getSum(List<Rank> ranks) {
        double sum = 0;

        for (Rank rank : Rank.values()) {
            sum += rank.getPrize(ranks);
        }

        if (sum == 0) {
            return 0;
        }
        return sum;
    }

    private double calculateResutnRate(int inputMoney, double sum) {
        return sum / inputMoney;
    }
}
