package domain;

class Purchasement {
    private static final int PRICE_PER_LOTTO = 1000;
    Purchasement(int amountOfMoney) {
        if (amountOfMoney < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException("고작 그정도 돈으로는 로또를 살 수 없습니다");
        }
    }
}
