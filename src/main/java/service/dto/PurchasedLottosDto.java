package service.dto;

public class PurchasedLottosDto {
    private IssuedLottosDto allLottos;
    private int manualLottoCount;
    private int autoLottoCount;

    public PurchasedLottosDto(IssuedLottosDto allLottos, int manualLottoCount, int autoLottoCount) {
        this.allLottos = allLottos;
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = autoLottoCount;
    }

    public IssuedLottosDto getAllLottos() {
        return allLottos;
    }

    public int getManualCount() {
        return manualLottoCount;
    }

    public int getAutoCount() {
        return autoLottoCount;
    }
}

