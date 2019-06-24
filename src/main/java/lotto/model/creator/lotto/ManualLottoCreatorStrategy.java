package lotto.model.creator.lotto;

import lotto.model.object.Lotto;

public class ManualLottoCreatorStrategy implements LottoCreatorStrategy {
        private final String[] inputs;

        public ManualLottoCreatorStrategy(String[] inputs) {
                this.inputs = inputs;
        }

        @Override
        public Lotto create() {
                return new Lotto(inputs);
        }
}
