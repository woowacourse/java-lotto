package lotto.domain.result;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.purchase.PurchaseAmount;

import java.util.*;

public class LottoResult {
    private static final int INIT_VALUE = 0;
    private static final int PERCENTAGE = 100;

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

    private Map<Rank, Integer> matchWinningLotto() {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        init(map);
        addLottoResult(map);
        return map;
    }

    private void init(Map<Rank, Integer> map) {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            map.put(rank, INIT_VALUE);
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
                .reduce(INIT_VALUE, Integer::sum) * PurchaseAmount.LOTTO_PRICE;
        int result = map.keySet().stream()
                .mapToInt(x -> x.getMoney() * map.get(x))
                .sum();

        return (result / purchaseAmount) * PERCENTAGE;
    }

    public Map<Rank, Integer> getMap() {
        return map;
    }
}
