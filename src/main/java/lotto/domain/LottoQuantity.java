package lotto.domain;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.purchaseamount.PurchaseAmount;

public class LottoQuantity {
    private static final int MIN_QUANTITY = 0;

    private int quantity;

    private LottoQuantity(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    public static LottoQuantity create(int quantity) {
        return new LottoQuantity(quantity);
    }

    public static LottoQuantity create(String quantity) {
        try {
            return new LottoQuantity(Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            throw new InvalidLottoQuantityException("로또 개수는 숫자로 설정 가능합니다.");
        }
    }

    private void validateQuantity() {
        if (quantity < MIN_QUANTITY) {
            throw new InvalidLottoQuantityException(MIN_QUANTITY + "이상으로 설정 가능합니다.");
        }
    }

    public void validateAvailable(PurchaseAmount purchaseAmount) {
        if (!purchaseAmount.canBuy(quantity * LottoTicket.getPrice())) {
            throw new InvalidLottoQuantityException("구매 불가능한 개수입니다.");
        }
    }

    public boolean biggerThan(int size) {
        return quantity > size;
    }
}
