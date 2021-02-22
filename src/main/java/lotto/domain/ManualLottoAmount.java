package lotto.domain;

public class ManualLottoAmount {
    private final int value;

    public ManualLottoAmount(String amount, int autoLottoCount) {
        this.value = Integer.parseInt(amount);
    }

    public int getValue() {
        return 0;
    }
}
