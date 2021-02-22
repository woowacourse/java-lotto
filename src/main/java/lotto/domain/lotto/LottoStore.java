package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.Money;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public Lottos buyLottos(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.getPrice() / LOTTO_PRICE; i++) {
            lottos.add(Lotto.generatedBy(new RandomLottoGenerator()));
        }
        return new Lottos(lottos);
    }

    public Lotto buyLotto() {
        return Lotto.generatedBy(new RandomLottoGenerator());
    }



}
