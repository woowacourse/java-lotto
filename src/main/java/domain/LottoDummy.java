package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoDummy {
    private static List<Lotto> lottoDummy = new ArrayList<>();

    public LottoDummy(int lottoCount) {
        for (int index = 0; index < lottoCount; index++){
            lottoDummy.add(LottoFactory.createOneLotto());
        }
    }

    public void countWinningLottoRank(WinningNumber winningNumber, LottoResult lottoResult) {
        for (Lotto lotto : lottoDummy){
            LottoRank rank = LottoRank.findRank(winningNumber.countWinningMatch(lotto),
                                            winningNumber.isBonusMatch(lotto));
            lottoResult.addWinningRankCount(rank);
        }
    }

    public int getDummySize() {
        return lottoDummy.size();
    }

    public List<Lotto> getLottoDummy() {
        return Collections.unmodifiableList(lottoDummy);
    }
}
