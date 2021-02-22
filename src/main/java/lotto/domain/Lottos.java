package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.List;
import java.util.Objects;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(ManualLotto manualLotto, int autoCount) {
        this.lottos = manualLotto.getManualLotto();
        buyAutoLotto(autoCount);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private void buyAutoLotto(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoGenerator.make()));
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lottos)) return false;

        Lottos lottos1 = (Lottos) o;

        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return lottos != null ? lottos.hashCode() : 0;
    }
}
