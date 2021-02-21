package lotto.domain;

public class LottoAmount {

    private int totalAmount;
    private int manualAmount;
    private int autoAmount;

    public LottoAmount(Money money, String manualMoney) {
        validateManualMoney(money, manualMoney);
        this.totalAmount = money.toNumberOfPurchaseLotto();
        this.manualAmount = Integer.parseInt(manualMoney);
        this.autoAmount = totalAmount - manualAmount;
    }

    private void validateManualMoney(Money money, String manualMoney) {
        validateEmptyAmount(manualMoney);
        validateNumber(manualMoney);
        validateAmount(money, manualMoney);
    }

    private void validateEmptyAmount(String manualMoney) {
        if (manualMoney.isEmpty()) {
            throw new IllegalArgumentException("정확한 로또 수량을 입력해 주세요.");
        }
    }

    private void validateNumber(String manualMoney) {
        try {
            Integer.parseInt(manualMoney);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private void validateAmount(Money money, String manualMoney) {
        if (Integer.parseInt(manualMoney) > money.toNumberOfPurchaseLotto()) {
            throw new IllegalArgumentException("입력한 수량이 구입 가능 수량보다 많습니다.");
        }
    }

    public int toManualAmountNumber() {
        return manualAmount;
    }

    public int toAutoAmountNumber() {
        return autoAmount;
    }
}
