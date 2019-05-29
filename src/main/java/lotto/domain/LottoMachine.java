package lotto.domain;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public static Lottos buyLottos(final Money money) {
        Lottos lottos = new Lottos();
        int numberOfPurchases = money.calculateNumberOfPurchases(LOTTO_PRICE);
        for (int i = 0; i < numberOfPurchases; i++) {
            lottos.add(LottoNumbersGenerator.getLottoNumbers());
        }
        return lottos;
    }
}
