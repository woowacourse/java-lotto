package lotto.domain;

import java.util.Map;
import java.util.Set;

public class LottoResult_VO {

    private final Map<Rank, Integer> result;
    private final int price;

    public LottoResult_VO(Map<Rank, Integer> result, int price) {
        this.result = result;
        this.price = price;
    }

    public Set<Rank> getResultKey() {
        return result.keySet();
    }

    public int getResultValue(Rank result) {
        return this.result.get(result);
    }

    public int getPrice() {
        return price;
    }
}
