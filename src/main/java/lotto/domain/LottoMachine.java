package lotto.domain;

import lotto.exception.InvalidPurchaseInformationException;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private LottoMachine() {
        throw new AssertionError();
    }

    public static Lottos buyLottos(final PurchaseInformation purchaseInformation) {
        if (!purchaseInformation.isValidPurchaseInformation()) {
            throw new InvalidPurchaseInformationException("구매 정보가 잘못되었습니다.");
        }

        Lottos lottos = new Lottos();
        if (purchaseInformation.hasManualLottos()) {
            lottos.addAll(buyManualLotto(purchaseInformation.getManualLottosNumbers()));
        }
        lottos.addAll(buyAutoLottos(purchaseInformation.getAutoLottoCount()));
        return lottos;
    }

    private static Lottos buyAutoLottos(final int numberOfLottos) {
        Lottos lottos = new Lottos();
        for (int i = 0; i < numberOfLottos; i++) {
            lottos.add(LottoNumbersGenerator.getLottoNumbers());
        }
        return lottos;
    }

    private static Lottos buyManualLotto(final List<LottoNumbers> lottosNumbers) {
        return new Lottos(lottosNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList()));
    }
}
