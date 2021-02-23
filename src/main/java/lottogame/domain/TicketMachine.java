package lottogame.domain;

import lottogame.utils.CannotBuyLottoException;
import lottogame.utils.InvalidManualTicketQuantityException;

public class TicketMachine {
    private static final int TICKET_PRICE = 1000;

    public Money validManualTicket(Money money, int quantity) {
        validateQuantityRange(money, quantity);
        return money.consume(quantity * TICKET_PRICE);
    }

    private void validateQuantityRange(Money money, int quantity) {
        if (quantity == 0) {
            throw new CannotBuyLottoException();
        }
        checkQuantityByMoney(money, quantity);
    }

    private void checkQuantityByMoney(Money money, int quantity) {
        if (!money.availableForPurchase(TICKET_PRICE * quantity)) {
            throw new InvalidManualTicketQuantityException();
        }
    }

    public int buyableAutoTicketQuantity(Money money) {
        return money.buyLotto(TICKET_PRICE);
    }
}
