package lotto.domain.strategy;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AutomaticCreationStrategy implements LottoCreationStrategy {

    private int automaticLottoCount;

    public AutomaticCreationStrategy(int automaticLottoCount) {
        this.automaticLottoCount = automaticLottoCount;
    }

    @Override
    public Lottos create() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < automaticLottoCount; i++) {
            lottos.add(createOneLotto());
        }
        return new Lottos(lottos);
    }

    public Lotto createOneLotto() {
        return new Lotto(Ball.getShuffledLottoNumbers().stream()
                .limit(Lotto.BALLS_COUNT)
                .sorted()
                .collect(Collectors.toList()));
    }
}
