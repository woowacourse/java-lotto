package lotto.domain.lotto;

import lotto.domain.purchase.PurchaseCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(PurchaseCount purchaseCount, List<Numbers> numbers) {
        this.lottos = new ArrayList<>();
        lottos.addAll(LottosGenerator.generateLottos(numbers));
        lottos.addAll(LottosGenerator.generateLottos(purchaseCount.getAutoCount()));
    }

    public static Lottos of(PurchaseCount purchaseCount, List<Numbers> numbers) {
        return new Lottos(purchaseCount, numbers);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
