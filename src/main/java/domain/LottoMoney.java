package domain;

public class LottoMoney {
    private static final String NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수로 입력해야 합니다.";
    private static final String NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE
            = String.format("금액을 %d의 배수로 입력해주세요.", LottoMachine.TICKET_PRICE);
    private static final String PURCHASE_OVER_ERROR_MESSAGE = "금액보다 많은 수동 티켓을 구매할 수 없습니다.";

    private final int value;

    public LottoMoney(int value) {
        validateMoney(value);
        this.value = value;
    }

    private void validateMoney(int amount) {
        if (isNotPositiveNumber(amount)) {
            throw new IllegalArgumentException(NOT_POSITIVE_ERROR_MESSAGE);
        }

        if (isMultiplesOfTicketPrice(amount)) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE);
        }
    }

    private boolean isNotPositiveNumber(int purchaseMoney) {
        return purchaseMoney <= 0;
    }

    private boolean isMultiplesOfTicketPrice(int purchaseMoney) {
        return purchaseMoney % LottoMachine.TICKET_PRICE != 0;
    }

    public LottoMoney purchaseSelfTicket(int count) {
        int changeMoneyValue = this.value - count * LottoMachine.TICKET_PRICE;
        if (changeMoneyValue < 0) {
            throw new IllegalArgumentException(PURCHASE_OVER_ERROR_MESSAGE);
        }
        return new LottoMoney(this.value - count * LottoMachine.TICKET_PRICE);
    }

    public int getCanPurchaseTicketCount() {
        return value / LottoMachine.TICKET_PRICE;
    }

    public int getValue() {
        return value;
    }
}
