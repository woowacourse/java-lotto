package lotto.dao;

import java.util.List;

public interface ResultDao {
    void addResult(List<String> results, int lottoRound);

    List<String> offerResults(int lottoRound);
}
