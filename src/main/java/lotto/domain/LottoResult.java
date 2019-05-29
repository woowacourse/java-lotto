package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private static Map<Rank, Integer> map = new LinkedHashMap<>();

    private LottoResult(Winning winning, Lottos lottos) {
        map = lottos.matchWinningLotto(winning);
    }

    public static LottoResult of(Winning winning, Lottos lottos) {
        return new LottoResult(winning, lottos);
    }

    public int yield() {
        int purchaseAmount = map.values().stream()
                .reduce(0, Integer::sum) * 1000
                ;
        int result = map.keySet().stream()
                .mapToInt(x -> x.getMoney() * map.get(x))
                .sum()
                ;

        return (result / purchaseAmount) * 100;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Rank, Integer> maps : map.entrySet()) {
            stringBuilder.append(maps.getKey().getRank());
            stringBuilder.append("개 일치 (");
            stringBuilder.append(maps.getKey().getMoney());
            stringBuilder.append("원)");
            stringBuilder.append("- ");
            stringBuilder.append(maps.getValue());
            stringBuilder.append("개\n");
        }
        return stringBuilder.toString();
    }
}
