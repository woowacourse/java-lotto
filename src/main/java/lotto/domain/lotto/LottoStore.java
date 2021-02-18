package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lotto.Money;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;

public class LottoStore {

    public Lottos buyLottos(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.getPrice() / 1000; i++) {
            lottos.add(Lotto.generatedBy(new RandomLottoGenerator()));
        }
        return new Lottos(lottos);
    }

    public Lotto buyLotto() {
        return Lotto.generatedBy(new RandomLottoGenerator());
    }



}
