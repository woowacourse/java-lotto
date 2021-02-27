package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.Money;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_BUY_FAIL = "구매 할 수 없는 로또 수량입니다.";

    public Lottos buyLottos(Money money, NumManualLotto numManual) {
        List<Lotto> lottos = new ArrayList<>();

        OutputView.manualNumberPrint();
        for (int i = 0; i < numManual.getNumLotto(); i++) {
            lottos.add(buyManualLotto());
        }

        for (int i = 0; i < getNumAutoLotto(money, numManual); i++) {
            lottos.add(buyAutoLotto());
        }
        return new Lottos(lottos);
    }

    public void validNumManualLotto(Money money, NumManualLotto numManualLotto) {
        if (money.getAvailableNumManualLotto(numManualLotto) < 0) {
            throw new IllegalArgumentException(ERROR_BUY_FAIL);
        }
    }

    private int getNumAutoLotto(Money money, NumManualLotto numManual) {
        return money.getPrice() / LOTTO_PRICE - numManual.getNumLotto();
    }

    private Lotto buyManualLotto() {
        return Lotto.manual(InputView.inputManualLottoNumber());
    }


    public Lotto buyAutoLotto() {
        return Lotto.generate(new RandomLottoGenerator());
    }
}
