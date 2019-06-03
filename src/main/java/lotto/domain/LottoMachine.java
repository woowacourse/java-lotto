package lotto.domain;

import lotto.domain.generator.AutoLottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;

import java.util.List;

public class LottoMachine {


    private LottoMachine() {
        throw new AssertionError();
    }

    public static Lottos buyLottos(PurchaseInformation purchaseInformation) {
        purchaseInformation.checkValidPurchaseInformation();

        Lottos lottos = buyManualLotto(purchaseInformation.getManualLottosNumbers());
        lottos.addAll(buyAutoLottos(purchaseInformation.getAutoLottoCount()));
        return lottos;
    }

    private static Lottos buyManualLotto(final List<String> lottosNumbers) {
        ManualLottoNumbersGenerator manualLottoNumbersGenerator = ManualLottoNumbersGenerator.getInstance();
        Lottos lottos = new Lottos();
        for (String numbers : lottosNumbers) {
            manualLottoNumbersGenerator.register(numbers);
            lottos.add(new Lotto(manualLottoNumbersGenerator.generate()));
        }
        return lottos;
    }

    private static Lottos buyAutoLottos(final int count) {
        AutoLottoNumbersGenerator autoLottoNumbersGenerator = AutoLottoNumbersGenerator.getInstance();
        Lottos lottos = new Lottos();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(autoLottoNumbersGenerator.generate()));
        }
        return lottos;
    }
}
