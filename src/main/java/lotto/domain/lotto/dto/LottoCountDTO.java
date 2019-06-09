package lotto.domain.lotto.dto;

public class LottoCountDTO {
    private int manualLottoCount;

    public void set(String manualLottoCount) {
        this.manualLottoCount = Integer.parseInt(manualLottoCount);
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }
}
