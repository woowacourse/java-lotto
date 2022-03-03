package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoOrder {

    private static final int LOTTO_PRICE = 1000;
    public static final String ERROR_ORDER_NUMBER = "금액이 부족하여 주문한 수만큼 로또를 구매할 수 없습니다.";

    private Money money;
    private final List<List<Integer>> manualNumbers;

    public LottoOrder(int inputMoney, List<List<Integer>> numbers) {
        this.money = new Money(inputMoney, LOTTO_PRICE);
        this.manualNumbers = numbers;
        validateOrderNumber();
    }

    public void billingManualLottoOrder() {
        money = money.decrease(LOTTO_PRICE, manualNumbers.size());
    }

    public int getNumberOfAutoLotto() {
        return money.getMaximumPurchase(LOTTO_PRICE);
    }

    public double getYield(double total) {
        return money.calculateYield(total);
    }

    private void validateOrderNumber() {
        if (money.getMaximumPurchase(LOTTO_PRICE) < manualNumbers.size()) {
            throw new RuntimeException(ERROR_ORDER_NUMBER);
        }
    }

    public List<List<Integer>> getManualLottoNumbers() {
        return new ArrayList<>(manualNumbers);
    }
}
