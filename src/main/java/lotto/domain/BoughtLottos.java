package lotto.domain;

import lotto.domain.generator.LottosAutoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BoughtLottos {
    public static final int BUY_PRICE = 1000;
    private final List<Lotto> lottos;
    private final int countOfBoughtManual;

    public BoughtLottos(final List<Lotto> lottos, final int countOfBoughtManual) {
        this.lottos = lottos;
        this.countOfBoughtManual = countOfBoughtManual;
    }

    public static BoughtLottos buyLottos(final int price, List<Lotto> manualLottos) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        int countOfBoughtManual = manualLottos.size();
        int amountOfAutoGenerateLotto = price / BUY_PRICE - countOfBoughtManual;

        lottos.addAll(new LottosAutoGenerator(amountOfAutoGenerateLotto).generate());

        return new BoughtLottos(lottos, countOfBoughtManual);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int countOfBoughtManualLotto() {
        return countOfBoughtManual;
    }

    public int countOfBoughtAutoLotto() {
        return lottos.size() - countOfBoughtManual;
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
