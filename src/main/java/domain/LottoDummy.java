package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LottoDummy {
    private static List<Lotto> lottoDummy = new ArrayList<>();

    public LottoDummy(int lottoCount) {
        for (int index = 0; index < lottoCount; index++){
            lottoDummy.add(LottoFactory.createOneLotto());
        }
    }

    public void countWinningLottoRank(WinningNumber winningNumber, Map<LottoRank, Integer> result) {
        for (Lotto lotto : lottoDummy){
            LottoRank rank = LottoRank.findRank(winningNumber.countWinningMatch(lotto),
                                            winningNumber.isBonusMatch(lotto));
            countNotNullRank(result, rank);
        }
    }

    private void countNotNullRank(Map<LottoRank, Integer> result, LottoRank rank) {
        if (rank != null){
            result.put(rank, result.get(rank) + 1);
        }
    }

    public int getDummySize() {
        return lottoDummy.size();
    }

    public List<Lotto> getLottoDummy() {
        return Collections.unmodifiableList(lottoDummy);
    }
}
