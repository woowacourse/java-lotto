package lotto.domain.stats;

import com.google.common.collect.Maps;
import lotto.domain.Rank;

import java.util.*;

public class LottoResults {
    private List<LottoResult> resultGroup = new ArrayList<>();

    public void add(LottoResult lottoResult) {
        resultGroup.add(lottoResult);
    }

    public Map<Rank, Integer> values() {
        Map<Rank, Integer> result = new LinkedHashMap<>();

        for (Rank rank : Rank.values()) {
            if (rank.isNotFound()) continue;
            int count = (int) resultGroup
                    .stream()
                    .filter(lottoResult -> lottoResult.equals(rank))
                    .count();
            result.put(rank, count);
        }

        return result;
    }
}
