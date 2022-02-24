package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import vo.WinningCount;

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
        Map<Rank, WinningCount> map = setupMap();

        lottos.stream()
                .map(winningLotto::getRankByLotto)
                .forEach(rank -> increaseCountByRank(map, rank));

        return map;
    }

    private void increaseCountByRank(Map<Rank, WinningCount> map, Rank rank) {
        int count = map.get(rank).getCount() + 1;
        map.put(rank, new WinningCount(count));
    }

    private Map<Rank, WinningCount> setupMap() {
        Map<Rank, WinningCount> map = new HashMap<>();
        map.put(Rank.FIRST, new WinningCount(0));
        map.put(Rank.SECOND, new WinningCount(0));
        map.put(Rank.THIRD, new WinningCount(0));
        map.put(Rank.FOURTH, new WinningCount(0));
        map.put(Rank.FIFTH, new WinningCount(0));
        map.put(Rank.NO_MATCH, new WinningCount(0));

        return map;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
