package lotto.utils;

import lotto.domain.Lottos;

public class ComplexLottosGenerator implements LottosGenerator {

    private final ManualLottosGenerator manual;
    private final AutoLottosGenerator auto;

    public ComplexLottosGenerator(ManualLottosGenerator manual, AutoLottosGenerator auto) {
        this.auto = auto;
        this.manual = manual;
    }

    @Override
    public Lottos generate() throws IllegalArgumentException {
        Lottos lottos = new Lottos();
        lottos.add(manual.generate());
        lottos.add(auto.generate());
        return lottos;
    }
}
