package lotto.domain;

public class PurchasePrice {
    private static final int MINIMUM_PRICE = 1_000;
    private static final int PRICE_PER_LOTTO = 1_000;

    private final int price;

    public PurchasePrice(String purchasePriceInput) {
        checkNoInput(purchasePriceInput);
        int price = convertToInt(purchasePriceInput);
        validateMinimumPrice(price);
        this.price = price;
    }

    private static void checkNoInput(String purchasePriceInput) {
        if (null == purchasePriceInput || purchasePriceInput.trim().isEmpty()) {
            throw new RuntimeException("구입금액을 입력해 주세요.");
        }
    }

    private static int convertToInt(String purchasePriceInput) {
        try {
            return Integer.parseInt(purchasePriceInput);
        } catch (NumberFormatException e) {
            throw new RuntimeException("구입금액은 숫자만 입력 가능합니다.");
        }
    }

    private static void validateMinimumPrice(int price) {
        if (price < MINIMUM_PRICE) {
            throw new RuntimeException(String.format("최소 %d원 이상 구매하셔야 합니다.", MINIMUM_PRICE));
        }
    }

    public int calculateLottoCount() {
        return price / PRICE_PER_LOTTO;
    }

    public int getPrice() {
        return price;
    }
}
