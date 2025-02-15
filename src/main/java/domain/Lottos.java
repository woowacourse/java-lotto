package domain;

import domain.numbergenerator.NumberGenerator;
import dto.OutputLottosDto;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public static Lottos of(NumberGenerator numberGenerator, final int quantity) {
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            List<Integer> generatedNumbers = numberGenerator.generateNumber();
            generatedLottos.add(Lotto.from(generatedNumbers));
        }

        return new Lottos(generatedLottos);
    }
}
