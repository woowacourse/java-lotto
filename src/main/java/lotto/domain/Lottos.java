package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.util.NumberGenerator;
import lotto.util.RandomNumber;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoCounts, NumberGenerator numberGenerator) {
        generateLottos(lottoCounts, numberGenerator);
    }

    private void generateLottos(int lottoCounts, NumberGenerator numberGenerator) {
        for (int i = 0; i < lottoCounts; i ++) {
            lottos.add(new Lotto(numberGenerator));
        }
    }

    public Prizes calculatePrize(Lotto winningLotto, LottoNumber bonusNumber) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.match(winningLotto);
            ranks.add(Rank.matchRank(matchCount, lotto.isMatchExist(bonusNumber)));
        }
        return new Prizes(ranks);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
