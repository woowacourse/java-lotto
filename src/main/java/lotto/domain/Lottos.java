package lotto.domain;

import static lotto.utils.LottoGenerator.generateLottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos = new ArrayList<>();

    public void purchase(Money money) {
        int purchaseNumber = money.divideBy(LOTTO_PRICE);
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto(generateLottoNumbers()));
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
