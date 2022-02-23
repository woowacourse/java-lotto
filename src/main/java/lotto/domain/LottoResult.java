package lotto.domain;

import java.util.HashMap;

public class LottoResult {
    private HashMap<LottoRank, Integer> result;


    public LottoResult() {
        this.result = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            result.put(lottoRank, 0);
        }
    }

    public void add(LottoRank lottoRank) {
        result.put(lottoRank, result.get(lottoRank) + 1);
    }

    public int sumOfPrize() {
        int sum = 0;
        for (LottoRank lottoRank : result.keySet()) {
            sum += result.get(lottoRank) * lottoRank.getPrizeAmount();
        }
        return sum;
    }

    public HashMap<LottoRank, Integer> getResult() {
        return result;
    }
}
