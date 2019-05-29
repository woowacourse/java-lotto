package lotto.domain;

import java.util.*;

public class Lottos {
    private static List<Lotto> lottos = new ArrayList<>();

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generate(PurchaseAmount purchaseAmount) {
        for (int i = 0; i < purchaseAmount.purchaseAutoCount(); i++) {
            lottos.add(LottoMaker.generator());
        }
        return of(lottos);
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public Map<Rank, Integer> matchWinningLotto(Winning winning) {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        init(map);
        addLottoResult(winning, map);
        return map;
    }

    private void init(Map<Rank, Integer> map) {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            map.put(rank, 0);
        }
    }

    private void addLottoResult(Winning winning, Map<Rank, Integer> map) {
        for (Lotto lotto : lottos) {
            Rank rank = winning.checkWinner(lotto);
            map.replace(rank, map.get(rank) + 1);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
