package domain;

public class PurchaseAmount {
    public static final int LOTTO_PRICE = 1000;
    private int amount;

    public PurchaseAmount(String inputMoney) {
        checkNotNumber(inputMoney);
        this.amount = Integer.parseInt(inputMoney);
        checkNegativeAmount(amount);
        checkUnderLottoPrice();
    }

    private void checkUnderLottoPrice() {
        if (amount < LOTTO_PRICE){
            throw new IllegalArgumentException("로또 한장 가격보다 낮은 금액을 입력하셨습니다.");
        }
    }

    private void checkNotNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("구매 금액은 숫자여야합니다.");
        }
    }

    private void checkNegativeAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("구매 금액은 음수일 수 없습니다.");
        }
    }

    public int getCount() {
        return amount / LOTTO_PRICE;
    }
}
