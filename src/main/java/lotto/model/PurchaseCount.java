package lotto.model;

public class PurchaseCount {
    private static final String ERROR_COUNT_OVER = "[ERROR] 구매할 수 있는 수량을 초과했습니다";
    public static final String ERROR_TYPE = "[ERROR] 로또 구매 수량은 숫자로만 입력해주세요";

    private int auto;
    private int manual;

    private PurchaseCount(int total, int manual) {
        checkCount(total, manual);
        this.auto = total - manual;
        this.manual = manual;
    }

    private void checkCount(int total, int manual) {
        if (total < manual) {
            throw new IllegalArgumentException(ERROR_COUNT_OVER);
        }
    }

    public static PurchaseCount of(Money money, String manualCountInput) {
        try {
            return new PurchaseCount(
                    money.countAvailableLotto(), Integer.parseInt(manualCountInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public void subtractAuto() {
        this.auto--;
    }

    public void subtractManual() {
        this.manual--;
    }

    public boolean isAutoAvailable() {
        return auto != 0;
    }

    public boolean isManualAvailable() {
        return manual != 0;
    }
}
