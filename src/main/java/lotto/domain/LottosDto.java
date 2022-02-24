package lotto.domain;

import java.util.List;

public class LottosDto {
    private final List<Lotto> lottos;

    public LottosDto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static LottosDto from(Lottos lottos) {
        return new LottosDto(lottos.getLottos());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getLottosSize() {
        return lottos.size();
    }
}
