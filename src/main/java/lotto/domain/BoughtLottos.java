package lotto.domain;

import lotto.domain.exception.ExceedBoughtLottosAboutMoneyException;
import lotto.domain.generator.LottosAutoGenerator;
import lotto.domain.generator.LottosManualGenerator;

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

    public static BoughtLottos buyLottos(final Money money, List<String> inputManualLottos) {
        int countOfBoughtManual = inputManualLottos.size();
        int price = money.getBuyPrice();
        if (price / BUY_PRICE < countOfBoughtManual) {
            throw new ExceedBoughtLottosAboutMoneyException("입력된 가격보다 사려는 로또가 더 많습니다.");
        }

        List<Lotto> lottos = new ArrayList<>(generateLottos(new LottosManualGenerator(inputManualLottos)));
        int amountOfAutoGenerateLotto = price / BUY_PRICE - countOfBoughtManual;
        lottos.addAll(generateLottos(new LottosAutoGenerator(amountOfAutoGenerateLotto)));

        return new BoughtLottos(lottos, countOfBoughtManual);
    }

    private static List<Lotto> generateLottos(LottosGenerator generator) {
        return generator.generate();
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
