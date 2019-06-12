package lotto.model.creator.lottos;

import lotto.model.object.Lotto;

import java.util.ArrayList;
import java.util.List;

public class ManualLottosCreatorStrategy implements LottosCreatorStrategy {
        private final List<String[]> lottoInputs;

        public ManualLottosCreatorStrategy(final List<String[]> lottoInputs) {
                this.lottoInputs = lottoInputs;
        }

        public List<Lotto> create() {
                List<Lotto> lottos = new ArrayList<>();
                for (String[] lottoInput : lottoInputs) {
                        lottos.add(new Lotto(lottoInput));
                }
                return lottos;
        }
}
