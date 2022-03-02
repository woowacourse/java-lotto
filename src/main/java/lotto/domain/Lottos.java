package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lottos {

    private static final long LOTTO_PRICE = 1000;

    private Money money;
    private final List<Lotto> lottos;
    private int lottoCountByManual;
    private int lottoCountBAuto;

    public Lottos(Money money) {
        this.money = money;
        this.lottos = new ArrayList<>();
        this.lottoCountByManual = 0;
        this.lottoCountBAuto = 0;
    }

    public void buyLottoByManual(Lotto lotto) {
        lottos.add(lotto);
        money.minusPrice(LOTTO_PRICE);
        lottoCountByManual++;
    }

    public void buyAllLottosByAuto() {
        int totalLottoCount = money.calculateTotalLottoCount(LOTTO_PRICE);
        IntStream.range(0, totalLottoCount)
                .mapToObj(i -> Lotto.generateLottoByAuto())
                .forEach(lottos::add);
        money.minusPrice(totalLottoCount * LOTTO_PRICE);
        lottoCountBAuto += totalLottoCount;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getLottoCountByManual() {
        return lottoCountByManual;
    }

    public int getLottoCountByAuto() {
        return lottoCountBAuto;
    }
}
