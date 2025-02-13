package domain;

import domain.generator.Generator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoFactory {
    private final Generator generator;

    public LottoFactory(Generator generator) {
        this.generator = generator;
    }

    public Lotto from(){
        return new Lotto(generator.generate());
    }

}
