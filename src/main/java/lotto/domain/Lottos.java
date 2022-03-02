package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;
    private int countByManual;
    private int countByAuto;

    public Lottos() {
        this.lottos = new ArrayList<>();
        this.countByManual = 0;
        this.countByAuto = 0;
    }

    public void buyLottoByManual(Lotto lotto, Money money) {
        money.spendMoney(Lotto.LOTTO_PRICE);
        lottos.add(lotto);
        countByManual++;
    }

    public void buyAllLottosByAuto(Money money) {
        int countCanBuy = money.calculateCountCanBuy();
        money.spendMoney(countCanBuy * Lotto.LOTTO_PRICE);
        IntStream.range(0, countCanBuy)
                .mapToObj(i -> Lotto.generateLottoByAuto())
                .forEach(lottos::add);
        countByAuto += countCanBuy;
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
