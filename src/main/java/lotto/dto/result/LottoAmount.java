package lotto.dto.result;

public class LottoAmount {

    private final int manualLotto;
    private final int autoLotto;

    public LottoAmount(int manualLotto, int autoLotto) {
        this.manualLotto = manualLotto;
        this.autoLotto = autoLotto;
    }

    public int getManualLotto() {
        return manualLotto;
    }

    public int getAutoLotto() {
        return autoLotto;
    }
}