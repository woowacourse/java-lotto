package domain;

import domain.numbergenerator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(NumberGenerator numberGenerator, final int quantity) {
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            generatedLottos.add(Lotto.from(numberGenerator));
        }

        return new Lottos(generatedLottos);
    }
}
