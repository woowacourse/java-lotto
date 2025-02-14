package domain.factory;

import domain.Lotto;
import domain.generator.Generator;

public class LottoFactory {
    private final Generator generator;

    public LottoFactory(Generator generator) {
        this.generator = generator;
    }

    public Lotto generate() {
        return new Lotto(generator.generate());
    }

}
