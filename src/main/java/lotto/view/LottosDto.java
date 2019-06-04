package lotto.view;

import java.util.ArrayList;
import java.util.List;

public class LottosDto {
    private final List<LottoDto> lottos;

    private LottosDto(final List<LottoDto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static LottosDto of(final List<LottoDto> lottos) {
        return new LottosDto(lottos);
    }

    public List<LottoDto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
