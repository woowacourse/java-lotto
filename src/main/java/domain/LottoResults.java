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

    public long getCountSameLottoNumber(int number) {
        return lottoResults.stream()
                .filter(lottoResult -> lottoResult.isCorrectCount(number))
                .count();
    }

    public HashMap<String, Integer> getCountMap() {
        HashMap<String, Integer> map = new HashMap<>();

        for (LottoType lottoType : LottoType.values()) {
            map.put(lottoType.name(), Math.toIntExact(getCountSameLottoNumber(lottoType.getNumber())));
        }

        return map;
    }
}
