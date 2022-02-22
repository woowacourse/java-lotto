package lotto.domain;

import static lotto.utils.LottoGenerator.generateLottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int LOTTO_PRICE = 1000;

    private List<Lotto> lottos = new ArrayList<>();

    public void purchase(Money money) {
        int purchaseNumber = money.divideBy(LOTTO_PRICE);
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto(generateLottoNumbers()));
        }
    }

    public boolean matchSize(int size) {
        return lottos.size() == size;
    }
}
