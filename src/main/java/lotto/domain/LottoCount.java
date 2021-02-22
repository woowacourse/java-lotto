package lotto.domain;

public class LottoCount {
    private final int manualLottoCount;

    public LottoCount(String count) {
        this.manualLottoCount = Integer.parseInt(count);
    }
}
