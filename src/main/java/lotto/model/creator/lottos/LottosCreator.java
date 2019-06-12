package lotto.model.creator.lottos;

import lotto.model.object.Lotto;

import java.util.List;

public class LottosCreator {
        private final LottosCreatorStrategy lottosCreatorStrategy;

        public LottosCreator(final LottosCreatorStrategy lottosCreatorStrategy) {
                this.lottosCreatorStrategy = lottosCreatorStrategy;
        }

        public List<Lotto> create(){
                return lottosCreatorStrategy.create();
        }
}
