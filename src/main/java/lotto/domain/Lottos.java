package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;

    private Lottos(int count) {
        lottos = new ArrayList<>();
        addAutoCreatedLotto(count);
    }

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos from(Money money) {
        return new Lottos((int)(money.getMoney() / LOTTO_PRICE));
    }

    public static Lottos of(List<Lotto> lottoList, Money change) {
        Lottos lottos = new Lottos(lottoList);
        lottos.addAutoCreatedLotto((int)(change.getMoney() / LOTTO_PRICE));
        return lottos;
    }

    private void addAutoCreatedLotto(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    public Map<Rank, Integer> countLottoRank(WinningLotto winningLotto) {
        Map<Rank, Integer> lottoResult = new HashMap<>();
        Arrays.stream(Rank.values()).forEach((rank -> lottoResult.put(rank, 0)));
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.getLottoRank(lotto);
            lottoResult.put(rank, lottoResult.get(rank) + 1);
        }
        return lottoResult;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
