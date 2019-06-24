package lotto.model.creator.lottos;

import lotto.model.creator.lotto.LottoCreatorStrategy;
import lotto.model.object.Lotto;

import java.util.ArrayList;
import java.util.List;

public class ManualLottosCreatorStrategy implements LottosCreatorStrategy {
        private final List<LottoCreatorStrategy> lottoCreatorStrategies;

        public ManualLottosCreatorStrategy(final List<LottoCreatorStrategy> lottoCreatorStrategies) {
                this.lottoCreatorStrategies = lottoCreatorStrategies;
        }

        @Override
        public List<Lotto> create() {
                List<Lotto> lottos = new ArrayList<>();
                for (int i = 0; i < lottoCreatorStrategies.size(); i++) {
                        lottos.add(lottoCreatorStrategies.get(i).create());
                }
                return lottos;
        }
}
