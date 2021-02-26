package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.Money;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public Lottos buyLottos(Money money, ManualLotto numManual) {
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

    private int getNumAutoLotto(Money money, ManualLotto numManual){
        return money.getPrice() / LOTTO_PRICE - numManual.getNumLotto();
    }

    private Lotto buyManualLotto() {
        return Lotto.manual(InputView.inputManualLottoNumber());
    }


    public Lotto buyAutoLotto() {
        return Lotto.generate(new RandomLottoGenerator());
    }
}
