package lotto;

public class PurchaseAmount {

    private static final int TICKET_PRICE = 1000;

    private final int amount;

    public PurchaseAmount(String input) {
        validateNullOrBlank(input);
        validateNumberFormat(input);
        final int inputAmount = Integer.parseInt(input);
        validateMinimumAmount(inputAmount);
        amount = calculateActualAmount(inputAmount);
    }

    private void validateNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("구입 금액은 공백일 수 없습니다");
        }
    }

    private void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야합니다");
        }
    }

    private void validateMinimumAmount(final int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException("구입금액은 1000원 이상이어야 합니다");
        }
    }

    private int calculateActualAmount(final int amount) {
        return amount - (amount % TICKET_PRICE);
    }

    public int getAmount() {
        return amount;
    }
}
