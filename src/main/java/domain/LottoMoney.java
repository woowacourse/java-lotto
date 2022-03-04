package domain;

public class LottoMoney {
    public static final String NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수로 입력해야 합니다.";
    public static final String NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE
            = String.format("금액을 %d의 배수로 입력해주세요.", LottoGame.TICKET_PRICE);

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
        return purchaseMoney % LottoGame.TICKET_PRICE != 0;
    }

    public LottoMoney purchaseSelfTicket(int count) {
        return new LottoMoney(this.value - count * LottoGame.TICKET_PRICE);
    }

    public int getValue() {
        return value;
    }
}
