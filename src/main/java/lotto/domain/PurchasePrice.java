package lotto.domain;

public class PurchasePrice {
    private static final int MINIMUM_PRICE = 1_000;
    private static final int PRICE_PER_LOTTO = 1_000;

    private final int price;

    public PurchasePrice(String purchasePrice) {
        checkNoInput(purchasePrice);
        checkType(purchasePrice);
        int price = convertToInt(purchasePrice);
        validateMinimumPrice(price);
        this.price = price;
    }

    private void checkNoInput(String purchasePriceInput) {
        if (purchasePriceInput == null || purchasePriceInput.trim().isEmpty()) {
            throw new RuntimeException("구입금액을 입력해 주세요.");
        }
    }

    private void checkType(String purchasePrice) {
        try {
            Integer.parseInt(purchasePrice);
        } catch (NumberFormatException e) {
            throw new RuntimeException("구입금액은 숫자만 입력 가능합니다.");
        }
    }

    private int convertToInt(String purchasePrice) {
        return Integer.parseInt(purchasePrice);
    }

    private void validateMinimumPrice(int price) {
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
