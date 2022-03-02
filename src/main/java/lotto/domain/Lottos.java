package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;
    private int countOfManualLotto;
    private int countOfAutoLotto;

    public Lottos() {
        this.lottos = new ArrayList<>();
        this.countOfManualLotto = 0;
        this.countOfAutoLotto = 0;
    }

    public void buyLottoByManual(Lotto lotto, Money money) {
        money.spendMoney(Lotto.LOTTO_PRICE);
        lottos.add(lotto);
        countOfManualLotto++;
    }

    public void buyAllLottosByAuto(Money money) {
        int countCanBuy = money.calculateCountCanBuy();
        money.spendMoney(countCanBuy * Lotto.LOTTO_PRICE);
        IntStream.range(0, countCanBuy)
                .mapToObj(i -> Lotto.generateLottoByAuto())
                .forEach(lottos::add);
        countOfAutoLotto += countCanBuy;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfAutoLotto() {
        return countOfAutoLotto;
    }
}
