package domain;

import domain.strategy.PurchaseStrategy;
import domain.strategy.RandomPurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static final String ERROR_PRICE_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_NEGATIVE_INTEGER = "가격은 1000원 이상만 가능합니다.";
    private static final int LOTTO_PRICE = 1000;

    public static Lottos purchaseLottos(final String inputMoney) {
        final List<Lotto> lottos = new ArrayList<>();
        int money = validatePrice(inputMoney);
        for (int i = 0; i < money / LOTTO_PRICE; i++) {
            purchase(lottos, new RandomPurchaseStrategy());
        }
        return new Lottos(lottos);
    }

    private static int validatePrice(final String inputPrice) {
        final int price = checkNonInteger(inputPrice, ERROR_PRICE_NON_INTEGER);
        checkUnderMinimumPrice(price);

        return price;
    }

    private static int checkNonInteger(final String number, final String message) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    private static void checkUnderMinimumPrice(final int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_INTEGER);
        }
    }

    private static void purchase(List<Lotto> lottos, PurchaseStrategy purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.generateNumbers());
        lottos.add(lotto);
    }

}
