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

    public void countWinningLottoRank(WinningNumber winningNumber, Map<LottoResult, Integer> result) {
        for (Lotto lotto : lottoDummy){
            LottoResult rank = winningNumber.findRank(lotto);
            countNotNullRank(result, rank);
        }
    }

    private void countNotNullRank(Map<LottoResult, Integer> result, LottoResult rank) {
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
