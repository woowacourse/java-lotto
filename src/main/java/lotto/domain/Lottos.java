package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static Lottos newInstanceByMoney(Money money) {
        return new Lottos(money.getMoney() / LOTTO_PRICE);
    }

    public static Lottos newInstanceByLottosMoney(List<Lotto> lottoList, Money change) {
        Lottos lottos = new Lottos(lottoList);
        lottos.addAutoCreatedLotto(change.getMoney() / LOTTO_PRICE);
        return lottos;
    }

    private void addAutoCreatedLotto(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Lotto> getAutoLottos(int manualCount) {
        return Collections.unmodifiableList(lottos.subList(manualCount, lottos.size()));
    }
}
