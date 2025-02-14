package domain.factory;

import domain.Amount;
import domain.Lotto;
import domain.Lottos;
import domain.generator.Generator;

public class LottosFactory {

    private final LottoFactory lottoFactory;

    public LottosFactory(Generator generator) {
        this.lottoFactory = new LottoFactory(generator);
    }

    public Lottos from(final Amount amount) {
        Lottos lottos = new Lottos();
        for (int i = 0; amount.compareAmount(i); i++) {
            Lotto lotto = lottoFactory.generate();
            lottos.add(lotto);
        }
        return lottos;
    }

}
