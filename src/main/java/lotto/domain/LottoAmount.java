package lotto.domain;

public class LottoAmount {

    public static final int PURCHASABLE_NUMBER = 0;

    private int totalAmount;
    private int manualAmount;

    public LottoAmount(Money money, int manualAmount) {
        this.totalAmount = money.toNumberOfPurchaseLotto();
        this.manualAmount = manualAmount;
    }

    public static LottoAmount of(Money money, String manualAmount) {
        validateManualMoney(money, manualAmount);

        return new LottoAmount(money, Integer.parseInt(manualAmount));
    }

    private static void validateManualMoney(Money money, String manualAmount) {
        validateEmptyAmount(manualAmount);
        validateNumber(manualAmount);
        validateAmount(money, manualAmount);
    }

    private static void validateEmptyAmount(String manualAmount) {
        if (manualAmount.isEmpty()) {
            throw new IllegalArgumentException("정확한 로또 수량을 입력해 주세요.");
        }
    }

    private static void validateNumber(String manualAmount) {
        try {
            Integer.parseInt(manualAmount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private static void validateAmount(Money money, String manualAmount) {
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
        return totalAmount - manualAmount;
    }
}