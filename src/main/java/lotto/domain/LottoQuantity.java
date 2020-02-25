package lotto.domain;

import lotto.exceptions.LottoQuantityException;

public class LottoQuantity {
    private int quantity;

    private LottoQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static LottoQuantity create(String maybe) {
        int quantity = validQuantity(maybe);
        return new LottoQuantity(quantity);
    }

    private static int validQuantity(String maybe) {
        int quantity = validNumber(maybe);
        validatePositive(quantity);
        return quantity;
    }

    private static void validatePositive(int quantity) {
        if (quantity < 0) {
            throw new LottoQuantityException("0 이상의 올바른 숫자를 입력해주세요.");
        }
    }

    private static int validNumber(String maybe) {
        int quantity;
        try {
            quantity = Integer.parseInt(maybe);
        } catch (NumberFormatException e) {
            throw new LottoQuantityException("0 이상의 올바른 숫자를 입력해주세요.");
        }
        return quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
