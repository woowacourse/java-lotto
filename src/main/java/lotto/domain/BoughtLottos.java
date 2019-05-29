package lotto.domain;

import lotto.domain.generator.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BoughtLottos {

    private final List<Lotto> lottos;

    public BoughtLottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static BoughtLottos buyLottos(final int price) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < price / 1000; i++) {
            lottos.add(LottoGenerator.generateLotto());
        }
        return new BoughtLottos(lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BoughtLottos that = (BoughtLottos) o;
        return Objects.equals(lottos, that.lottos);
    }


    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
