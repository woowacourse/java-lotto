package domain;

import domain.generator.AutomaticLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;

public class LottoMachine {

    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_COUNT_NEGATIVE_INTEGER = "구매 개수는 양의 정수만 가능합니다.";
    private static final String ERROR_LESS_MONEY = "원하시는 로또 개수를 구매하기에는 돈이 부족합니다.";

    public int checkAvailableBuy(Money money, final int numOfLotto) {
        checkNegativeInteger(numOfLotto);
        if (numOfLotto > money.numOfAvailablePurchase()) {
            throw new IllegalArgumentException(ERROR_LESS_MONEY);
        }
        return numOfLotto;
    }

    private void checkNegativeInteger(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_COUNT_NEGATIVE_INTEGER);
        }
    }

    public Lottos purchaseManualLottos(Money money, final int numOfLotto, final int[][] numbers) {
        final Lottos lottos = new Lottos();
        for (int[] inputNumber : numbers) {
            purchase(lottos, new ManualLottoGenerator(inputNumber));
        }
        money.purchaseLotto(numOfLotto);
        return lottos;
    }

    public void purchaseAutomaticLottos(Lottos lottos, Money money) {
        for (int i = 0; i < money.numOfAvailablePurchase(); i++) {
            purchase(lottos, new AutomaticLottoGenerator());
        }
        money.purchaseLotto(money.numOfAvailablePurchase());
    }

    private void purchase(Lottos lottos, LottoGenerator purchaseStrategy) {
        Lotto lotto = new Lotto(purchaseStrategy.generateNumbers());
        lottos.addLotto(lotto);
    }
}
