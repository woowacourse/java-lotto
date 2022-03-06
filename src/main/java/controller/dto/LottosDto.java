package controller.dto;

import domain.Lottos;

public class LottosDto {
    private final int autoLottoCount;
    private final Lottos lottos;

    public LottosDto(int autoLottoCount, Lottos lottos) {
        this.autoLottoCount = autoLottoCount;
        this.lottos = lottos;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }

    public Lottos getLottos() {
        return lottos;
    }

}
