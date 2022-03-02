package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lottos {

    private static final long LOTTO_PRICE = 1000;

    private Money money;
    private final List<Lotto> lottos;

    public Lottos(Money money) {
        this.money = money;
        this.lottos = new ArrayList<>();
    }

    public void buyLottos(Lotto lotto) {
        lottos.add(lotto);
        money.minusPrice(LOTTO_PRICE);
    }

    public void buyLottosByAuto() {
        int totalLottoCount = money.calculateTotalLottoCount(LOTTO_PRICE);
        IntStream.range(0, totalLottoCount)
                .mapToObj(i -> Lotto.generateLottoByAuto())
                .forEach(lottos::add);
        money.minusPrice(totalLottoCount * LOTTO_PRICE);
    }

    public int getTotalLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
