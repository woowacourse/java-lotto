package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Ranks {
    private List<Rank> ranks = new ArrayList<>();

    void add(Rank rank) {
        if (rank == null) {
            return;
        }

        ranks.add(rank);
    }

    public List<Rank> getRanks() {
        return ranks;
    }
}
