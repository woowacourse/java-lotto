package lotto.domain.strategy;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManualCreationStrategy implements LottoCreationStrategy {

    private List<List<String>> manualLottos;

    public ManualCreationStrategy(List<List<String>> manualLottos) {
        this.manualLottos = manualLottos;
    }

    @Override
    public Lottos create() {
        List<Lotto> result = new ArrayList<>();
        for (List<String> lotto : manualLottos) {
            result.add(new Lotto(lotto.stream()
                    .map(s -> new Ball(Integer.parseInt(s)))
                    .collect(Collectors.toList())));
        }
        return new Lottos(result);
//        for (int i = 0; i < lottoCount.getManualCount(); i++) {
//            lottos.add(new Lotto(InputView.requestManualLotto().stream()
//                    .map(Ball::valueOf)
//                    .collect(Collectors.toList())));
//        }
    }
}
