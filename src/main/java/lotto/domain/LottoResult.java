package lotto.domain;

import java.util.*;

public class LottoResult {
    private static Map<Rank, Integer> map = new LinkedHashMap<>();
    private final Winning winning;
    private final Lottos lottos;

    private LottoResult(Winning winning, Lottos lottos) {
        this.winning = winning;
        this.lottos = lottos;
        map = matchWinningLotto();
    }

    public static LottoResult of(Winning winning, Lottos lottos) {
        return new LottoResult(winning, lottos);
    }

    public Map<Rank, Integer> matchWinningLotto() {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        init(map);
        addLottoResult(map);
        return map;
    }

    private void init(Map<Rank, Integer> map) {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            map.put(rank, 0);
        }
    }

    private void addLottoResult(Map<Rank, Integer> map) {
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = winning.checkWinner(lotto);
            map.replace(rank, map.get(rank) + 1);
        }
    }

    public int yield() {
        int purchaseAmount = map.values().stream()
                .reduce(0, Integer::sum) * PurchaseAmount.LOTTO_PRICE
                ;
        int result = map.keySet().stream()
                .mapToInt(x -> x.getMoney() * map.get(x))
                .sum()
                ;

        return (result / purchaseAmount) * 100;
    }

    public Map<Rank, Integer> getMap() {
        return map;
    }
}
