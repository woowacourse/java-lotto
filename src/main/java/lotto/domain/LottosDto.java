package lotto.domain;

import java.util.List;

public class LottosDto {
    private final List<Lotto> lotto;

    public LottosDto(List<Lotto> lotto) {
        this.lotto = lotto;
    }

    public static LottosDto from(Lottos lottos) {
        return new LottosDto(lottos.getLottos());
    }

    public List<Lotto> getLotto() {
        return lotto;
    }
}
