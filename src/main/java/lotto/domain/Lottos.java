package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lottos {

    private static final long LOTTO_PRICE = 1000;

    private Money money;
    private final List<Lotto> lottos;
    private int countByManual;
    private int countByAuto;

    public Lottos(Money money) {
        this.money = money;
        this.lottos = new ArrayList<>();
        this.countByManual = 0;
        this.countByAuto = 0;
    }

    public void buyLottoByManual(Lotto lotto) {
        lottos.add(lotto);
        money.minusPrice(LOTTO_PRICE);
        countByManual++;
    }

    public void buyAllLottosByAuto() {
        int totalLottoCount = money.calculateTotalLottoCount(LOTTO_PRICE);
        IntStream.range(0, totalLottoCount)
                .mapToObj(i -> Lotto.generateLottoByAuto())
                .forEach(lottos::add);
        money.minusPrice(totalLottoCount * LOTTO_PRICE);
        countByAuto += totalLottoCount;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getCountByManual() {
        return countByManual;
    }

    public int getCountByAuto() {
        return countByAuto;
    }
}
