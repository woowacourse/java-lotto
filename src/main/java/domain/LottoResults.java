package domain;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoResults {
    private List<LottoResult> lottoResults;

    public void add(LottoResult lottoResult) {
        lottoResults.add(lottoResult);
    }

    public int getTotal(int number) {
        return lottoResults.stream()
                .filter(lottoResult -> lottoResult.isCorrectCount(number))
                .collect(Collectors.toList()).size();
    }

    public HashMap<String, Integer> getCountMap() {
        HashMap<String, Integer> map = new HashMap<>();

        for (LottoType lottoType : LottoType.values()) {
            map.put(lottoType.name(), getTotal(lottoType.getNumber()));
        }

        return map;
    }
}
