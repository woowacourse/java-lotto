package lotto.domain;

import lotto.exceptions.InvalidLottoQuantityException;

public class LottoQuantity {
    private int quantity;

    private LottoQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static LottoQuantity create(String maybe, Money money) {
        int quantity = validQuantity(maybe, money);
        return new LottoQuantity(quantity);
    }

    private static int validQuantity(String maybe, Money money) {
        int quantity = validNumber(maybe);
        validatePositive(quantity);
        validateEnough(quantity, money);
        return quantity;
    }

    private static int validNumber(String maybe) {
        int quantity;
        try {
            quantity = Integer.parseInt(maybe);
        } catch (NumberFormatException e) {
            throw new InvalidLottoQuantityException("0 이상의 올바른 숫자를 입력해주세요.");
        }
        return quantity;
    }

    private static void validatePositive(int quantity) {
        if (quantity < 0) {
            throw new InvalidLottoQuantityException("0 이상의 올바른 숫자를 입력해주세요.");
        }
    }

    private static void validateEnough(int quantity, Money money) {
        if (!money.isEnough(quantity * Money.TICKET_PRICE)) {
            throw new InvalidLottoQuantityException("너무 많은 수동 로또 개수를 입력하셨습니다.");
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
