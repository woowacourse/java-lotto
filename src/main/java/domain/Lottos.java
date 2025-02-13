package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos ofSize(final int quantity) {
        List<Lotto> lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            Lotto generatedLotto = Lotto.of(LottoGenerator.generate());
            lottos.add(generatedLotto);
        }
        return new Lottos(lottos);
    }

    public List<String> getPurchasedLottos() {
        return lottos.stream().map(Lotto::toString).toList();
    }

    public int getQuantity() {
        return lottos.size();
    }
}
