package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {

    private static final long LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos buyLottosByAuto(Money money) {
        List<Lotto> lottos = IntStream.range(0, money.calculateTotalLottoCount(LOTTO_PRICE))
                .mapToObj(integer -> Lotto.generateLottoByAuto())
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    public int getTotalLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
