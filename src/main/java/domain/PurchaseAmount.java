package domain;

public class PurchaseAmount {
    private static final int FIT_AMOUNT = 0;

    private int amountOfMoney;

    private PurchaseAmount(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public static PurchaseAmount of(int amountOfMoney) {
        validateAmountOfMoney(amountOfMoney);
        validateIfMultipleOfPricePerLotto(amountOfMoney);

        return new PurchaseAmount(amountOfMoney);
    }

    private static void validateIfMultipleOfPricePerLotto(int amountOfMoney) {
        if (amountOfMoney % Lotto.PRICE != FIT_AMOUNT) {
            throw new IllegalArgumentException(Lotto.PRICE + "원의 배수만 입력할 수 있습니다.");
        }
    }

    private static void validateAmountOfMoney(int amountOfMoney) {
        if (amountOfMoney < Lotto.PRICE) {
            throw new IllegalArgumentException("고작 그정도 돈으로는 로또를 살 수 없습니다");
        }
    }

    int getPurchaseAmount() {
        return amountOfMoney;
    }
}
