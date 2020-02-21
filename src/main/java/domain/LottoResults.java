package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LottoResults {
    private List<LottoResult> lottoResults;

    public LottoResults() {
        this.lottoResults = new ArrayList<>();
    }

    public void add(LottoResult lottoResult) {
        lottoResults.add(lottoResult);
    }

    public int size() {
        return lottoResults.size();
    }

    private int getCountSameLottoNumber(int number) {
        return Math.toIntExact(lottoResults.stream()
                .filter(lottoResult -> lottoResult.isCorrectCount(number))
                .count());
    }

    public HashMap<String, Integer> getCountMap() {
        HashMap<String, Integer> winningCountMap = new HashMap<>();
        for (LottoType lottoType : LottoType.values()) {
            winningCountMap.put(lottoType.name(), getCountSameLottoNumber(lottoType.getNumber()));
        }
        return winningCountMap;
    }
}
