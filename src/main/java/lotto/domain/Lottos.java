package lotto.domain;

import java.util.List;

public class Lottos {
    public static int PRICE_PER_LOTTO = 1000;
    private List<Lotto> lottos;
    private static String INCORRECT_INDEX_MSG = "Lottos에 잘못된 index 접근입니다.";

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public Lotto getLottoByIndex(int index) {
        if (index < 0 || index > lottos.size())
            throw new IllegalArgumentException(INCORRECT_INDEX_MSG);
        return this.lottos.get(index);
    }
}
