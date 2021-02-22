package lotto.domain;

public class LottoAmount {

    public static final int PURCHASABLE_NUMBER = 0;

    private int totalAmount;
    private int manualAmount;
    private int autoAmount;

    public LottoAmount(Money money, String manualAmount) {
        validateManualMoney(money, manualAmount);
        this.totalAmount = money.toNumberOfPurchaseLotto();
        this.manualAmount = Integer.parseInt(manualAmount);
        this.autoAmount = totalAmount - this.manualAmount;
    }

    private void validateManualMoney(Money money, String manualAmount) {
        validateEmptyAmount(manualAmount);
        validateNumber(manualAmount);
        validateAmount(money, manualAmount);
    }

    private void validateEmptyAmount(String manualAmount) {
        if (manualAmount.isEmpty()) {
            throw new IllegalArgumentException("정확한 로또 수량을 입력해 주세요.");
        }
    }

    private void validateNumber(String manualAmount) {
        try {
            Integer.parseInt(manualAmount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private void validateAmount(Money money, String manualAmount) {
        if (Integer.parseInt(manualAmount) > money.toNumberOfPurchaseLotto()) {
            throw new IllegalArgumentException("입력한 수량이 구입 가능 수량보다 많습니다.");
        }

        if (Integer.parseInt(manualAmount) < PURCHASABLE_NUMBER) {
            throw new IllegalArgumentException("0 이상의 수를 입력해주세요.");
        }
    }

    public int toManualAmountNumber() {
        return manualAmount;
    }

    public int toAutoAmountNumber() {
        return autoAmount;
    }
}