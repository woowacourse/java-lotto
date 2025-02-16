package global.factory;

import domain.Lotto;
import global.generator.Generator;

public class LottoFactory {
    private final Generator generator;

    public LottoFactory(final Generator generator) {
        this.generator = generator;
    }

    public Lotto create() {
        return new Lotto(generator.generate());
    }

}
