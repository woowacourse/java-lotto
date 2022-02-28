package lotto.model.dto;

import lotto.model.Lottos;

public class LottosDTO {
    private final int autoCount;
    private final int manualCount;

    private LottosDTO(int autoCount, int manualCount) {
        this.autoCount = autoCount;
        this.manualCount = manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public static LottosDTO of(Lottos lottos) {
        return new LottosDTO(lottos.getAutoCount(), lottos.getManualCount());
    }
}
