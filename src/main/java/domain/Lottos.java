package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private static final List<Lotto> lottos = new ArrayList<>();

    public Lottos(final int lottoCount) {
        for (int index = 0; index < lottoCount; index++){
            lottos.add(LottoFactory.createOneLotto());
        }
    }

    public LottoResult countWinningLotto(final WinningNumber winningNumber) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos){
            LottoRank rank = LottoRank.findRank(winningNumber.countWinningMatch(lotto),
                                            winningNumber.isBonusMatch(lotto));
            lottoResult.addWinningRankCount(rank);
        }
        return lottoResult;
    }

    public int getDummySize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
