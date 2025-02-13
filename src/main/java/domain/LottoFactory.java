package domain;

import domain.generator.Generator;

public class LottoFactory {
    private final Generator generator;

    public LottoFactory(Generator generator) {
        this.generator = generator;
    }

    public Lotto from() {
        return new Lotto(generator.generate());
    }

}
