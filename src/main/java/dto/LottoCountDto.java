package dto;

import domain.player.LottoCount;

public class LottoCountDto {
    private int manualLottoCount;
    private int autoLottoCount;

    private LottoCountDto(int manualLottoCount, int autoLottoCount) {
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = autoLottoCount;
    }

    public static LottoCountDto of(LottoCount lottoCount) {
        return new LottoCountDto(lottoCount.getManualLottoCount(), lottoCount.getAutoLottoCount());
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
