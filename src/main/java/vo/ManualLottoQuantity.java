package vo;

public class ManualLottoQuantity {
    private static final int MINIMUM_MANUAL_LOTTO_QUANTITY = 1;
    private static final String ERROR_MESSAGE_FOR_INVALID_MANUAL_LOTTO_QUANTITY = "수동 구매 로또 수량은 1 이상으로 입력해주세요";

    private final int quantity;

    public ManualLottoQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_MANUAL_LOTTO_QUANTITY) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_MANUAL_LOTTO_QUANTITY);
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
