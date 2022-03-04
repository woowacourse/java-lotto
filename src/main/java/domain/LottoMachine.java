package domain;

import domain.generator.AutomaticLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;

public class LottoMachine {

    private static final String ERROR_COUNT_NON_INTEGER = "구매 개수는 정수만 가능합니다.";
    private static final String ERROR_COUNT_NEGATIVE_INTEGER = "구매 개수는 양의 정수만 가능합니다.";
    private static final String ERROR_LESS_MONEY = "원하시는 로또 개수를 구매하기에는 돈이 부족합니다.";

    public int checkAvailableBuy(Money money, final String numOfLotto) {
        int numOfManualLotto = validateNumOfLotto(numOfLotto);
        if (numOfManualLotto > money.numOfAvailablePurchase()) {
            throw new IllegalArgumentException(ERROR_LESS_MONEY);
        }
        return numOfManualLotto;
    }

    private int validateNumOfLotto(final String numOfLotto) {
        int numOfManualLotto = checkNonInteger(numOfLotto);
        checkNegativeInteger(numOfManualLotto);

        return numOfManualLotto;
    }

    private int checkNonInteger(final String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_COUNT_NON_INTEGER);
        }
    }

    private void checkNegativeInteger(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(ERROR_COUNT_NEGATIVE_INTEGER);
        }
    }

    public Lottos purchaseManualLottos(Money money, final int numOfLotto, final String[][] inputNumbers) {
        final Lottos lottos = new Lottos();
        for (String[] inputNumber : inputNumbers) {
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
