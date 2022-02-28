package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;

    public Lottos(int count) {
        lottos = new ArrayList<>();
        addAutoCreatedLotto(count);
    }

    public Lottos(Money purchaseAmount) {
        this(purchaseAmount.getMoney() / LOTTO_PRICE);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public Lottos(List<Lotto> lottos, Money change) {
        this.lottos = new ArrayList<>(lottos);
        addAutoCreatedLotto(change.getMoney() / LOTTO_PRICE);
    }

    private void addAutoCreatedLotto(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public List<Lotto> getAutoLottos(int manualCount) {
        return new ArrayList<>(lottos.subList(manualCount, lottos.size()));
    }
}
