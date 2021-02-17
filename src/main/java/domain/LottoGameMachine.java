package domain;

import domain.budget.Budget;
import domain.lotto.Lotto;
import domain.lotto.LottoCount;
import domain.lotto.Lottos;
import util.RandomLottoUtil;

import java.util.ArrayList;
import java.util.List;

public class LottoGameMachine {
    private static final Budget LOTTO_COST = Budget.amounts(1000);

    private final Budget budget;

    public LottoGameMachine(Budget budget) {
        this.budget = budget;
    }

    public void gameStart() {
        LottoCount lottoCount = calculateLottoCount();
        Lottos lottos = makeLottos(lottoCount);
    }

    private Lottos makeLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getLottoCount(); i++) {
            lottos.add(new Lotto(RandomLottoUtil.generateLottoNumbers()));
        }
        return new Lottos(lottos);
    }

    private LottoCount calculateLottoCount() {
        int lottoCount = budget.intQuotient(LOTTO_COST);
        System.out.println("lottoCount = " + lottoCount);
        return LottoCount.of(lottoCount);
    }
}
