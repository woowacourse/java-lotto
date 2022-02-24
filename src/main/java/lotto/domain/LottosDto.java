package lotto.domain;

import java.util.List;

public class LottosDto {
    private final List<ChoiceNumber> lottos;

    public LottosDto(List<ChoiceNumber> lottos) {
        this.lottos = lottos;
    }

    public static LottosDto from(Lottos lottos) {
        return new LottosDto(lottos.getLottos());
    }

    public List<ChoiceNumber> getLottos() {
        return lottos;
    }

    public int getLottosSize() {
        return lottos.size();
    }
}
