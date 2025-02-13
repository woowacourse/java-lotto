package domain;

import domain.generator.Generator;

public class LottosFactory {

    private final LottoFactory lottoFactory;

    public LottosFactory(Generator generator) {
        this.lottoFactory = new LottoFactory(generator);
    }

    public Lottos from(final Amount amount) {
        Lottos lottos = new Lottos();
        for (int i = 0; amount.compareAmount(i); i++) {
            Lotto lotto = lottoFactory.from();
            lottos.add(lotto);
        }
        return lottos;
    }

}
