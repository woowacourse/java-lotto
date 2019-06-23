package lotto.persistence;

import lotto.domain.Rank;

import java.util.Map;

public interface ResultDAO {
    static ResultDAO getInstance() {
        return ResultDAOImpl.getInstance();
    }

    void addResult(int roundId, Map<Rank, Integer> result);

    Map<Rank, Integer> findResultByRoundId(int roundId);

    void removeResult(int roundId);
}
