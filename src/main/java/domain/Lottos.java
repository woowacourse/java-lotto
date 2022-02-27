package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNullOrEmpty(lottos);
        this.lottos = Collections.unmodifiableList(lottos);
    }

    private void validateNullOrEmpty(List<Lotto> lottos) {
        if (Objects.isNull(lottos) || lottos.isEmpty()) {
            throw new IllegalArgumentException("Lotto 목록이 비었습니다");
        }
    }

    public Map<Rank, WinningCount> getResultByWinningLotto(WinningLotto winningLotto) {
        Map<Rank, WinningCount> lottoResult = setupLottoResult();

        lottos.stream()
                .map(winningLotto::getRankByLotto)
                .forEach(rank -> increaseCountByRank(lottoResult, rank));

        return lottoResult;
    }

    private void increaseCountByRank(Map<Rank, WinningCount> lottoResult, Rank rank) {
        int count = lottoResult.get(rank).getCount() + 1;
        lottoResult.put(rank, new WinningCount(count));
    }

    private Map<Rank, WinningCount> setupLottoResult() {
        Map<Rank, WinningCount> lottoResult = new HashMap<>();
        lottoResult.put(Rank.FIRST, new WinningCount(0));
        lottoResult.put(Rank.SECOND, new WinningCount(0));
        lottoResult.put(Rank.THIRD, new WinningCount(0));
        lottoResult.put(Rank.FOURTH, new WinningCount(0));
        lottoResult.put(Rank.FIFTH, new WinningCount(0));
        lottoResult.put(Rank.NO_MATCH, new WinningCount(0));

        return lottoResult;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
