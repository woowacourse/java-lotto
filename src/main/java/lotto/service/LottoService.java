package lotto.service;

public class LottoService {

    private static final int LOTTO_PRICE = 1000;

    public int countNumberOfPurchases(long purchaseAmount) {
        return (int) purchaseAmount / LOTTO_PRICE;
    }
}
