package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoCounts, RandomNumber randomNumber) {
        generateLottos(lottoCounts, randomNumber);
    }

    private void generateLottos(int lottoCounts, RandomNumber randomNumber) {
        for (int i = 0; i < lottoCounts; i ++) {
            lottos.add(new Lotto(new LottoNumbers(randomNumber)));
        }
    }

    public Prizes calculatePrize(Lotto winningLotto, LottoNumber bonusNumber) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.match(winningLotto);
            ranks.add(Rank.matchRank(matchCount, lotto.checkBonusNumberMatch(bonusNumber)));
        }
        return new Prizes(ranks);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
