package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos buyLottosByAuto(Money money) {
        List<Lotto> lottos = IntStream.range(0, calculateTotalLottoCount(money))
                .mapToObj(integer -> Lotto.generateLottoByAuto())
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private static int calculateTotalLottoCount(Money money) {
        return money.getMoney() / LOTTO_PRICE;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getTotalLottoCount() {
        return lottos.size();
    }
}
