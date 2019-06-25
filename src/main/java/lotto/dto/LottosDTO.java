package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;

public class LottosDTO {

    public static class Create {
        private final List<Lotto> lottos;

        public Create(List<Lotto> lottos) {
            this.lottos = lottos;
        }

        public List<Lotto> getLottos() {
            return lottos;
        }
    }
}
