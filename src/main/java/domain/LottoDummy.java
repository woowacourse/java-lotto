package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoDummy {
    private static final List<Lotto> lottoDummy = new ArrayList<>();

    public LottoDummy(final int lottoCount) {
        for (int index = 0; index < lottoCount; index++){
            lottoDummy.add(LottoFactory.createOneLotto());
        }
    }

    public LottoResult countWinningLotto(final WinningNumber winningNumber) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottoDummy){
            LottoRank rank = LottoRank.findRank(winningNumber.countWinningMatch(lotto),
                                            winningNumber.isBonusMatch(lotto));
            lottoResult.addWinningRankCount(rank);
        }
        return lottoResult;
    }

    public int getDummySize() {
        return lottoDummy.size();
    }

    public List<Lotto> getLottoDummy() {
        return Collections.unmodifiableList(lottoDummy);
    }
}
