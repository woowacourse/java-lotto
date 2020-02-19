package domain.result;

import java.util.List;

public class LottoResult {

    private List<Rank> ranks;

    public LottoResult(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public int count(Rank rank) {
        return (int)ranks.stream()
                .filter(has -> has.equals(rank))
                .count();
    }

}
