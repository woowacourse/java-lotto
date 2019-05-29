package lotto.domain;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_PRICE = 1_000_000_000;

    public static Lottos buyLottos(final int money) {
        checkInvalidMoney(money);
        Lottos lottos = new Lottos();
        int numberOfPurchases = calculateNumberOfPurchases(money);
        for (int i = 0; i < numberOfPurchases; i++) {
            lottos.add(LottoNumbersGenerator.getLottoNumbers());
        }
        return lottos;
    }

    private static void checkInvalidMoney(final int money) {
        if (money < LOTTO_PRICE || money > MAX_LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 가능 범위를 벗어났습니다.");
        }
    }

    private static int calculateNumberOfPurchases(final int money) {
        return money / LOTTO_PRICE;
    }
}
