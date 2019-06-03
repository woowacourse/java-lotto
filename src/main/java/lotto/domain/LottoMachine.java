package lotto.domain;

import lotto.domain.generator.AutoLottoNumbersGenerator;
import lotto.domain.generator.LottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.utils.NumbersSplitter;

import java.util.List;
import java.util.stream.Collectors;

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
        return new Lottos(lottosNumbers.stream()
                .map(LottoMachine::generateManualLotto)
                .collect(Collectors.toList()));
    }

    private static Lotto generateManualLotto(String numbers) {
        return new Lotto(ManualLottoNumbersGenerator.getInstance(NumbersSplitter.split(numbers)).generate());
    }

    private static Lottos buyAutoLottos(final int count) {
        LottoNumbersGenerator autoLottoNumbersGenerator = AutoLottoNumbersGenerator.getInstance();
        Lottos lottos = new Lottos();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(autoLottoNumbersGenerator.generate()));
        }
        return lottos;
    }
}
