package lotto.domain;

import static lotto.domain.Exception.ERROR_CANT_PURCHASE;

import java.util.List;

public class LottoGame {

    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoGame(final PurchaseAmount purchaseAmount, final int manualLottoCount) {
        validateCanPurchase(purchaseAmount, manualLottoCount);
        this.manualLottoCount = manualLottoCount;

        autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottoCount);
    }

    private void validateCanPurchase(final PurchaseAmount purchaseAmount, final int count) {
        if (!purchaseAmount.canPurchase(count)) {
            throw new IllegalArgumentException(ERROR_CANT_PURCHASE.getMessage());
        }
    }

    public Lottos makeManualAndAutoLottos(final List<List<Integer>> manualLottos) {
        final Lottos lottos = new Lottos();
        makeManualLottos(lottos, manualLottos);
        makeAutoLottos(lottos);
        return lottos;
    }

    private void makeManualLottos(final Lottos lottos, final List<List<Integer>> manualLottos) {
        for (List<Integer> manualLotto : manualLottos) {
            LottoMachine lottoMachine = new FixedLottoMachine(manualLotto);
            lottos.add(new Lotto(lottoMachine));
        }
    }

    private void makeAutoLottos(final Lottos lottos) {
        LottoMachine lottoMachine = new RandomLottoMachine();
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto(lottoMachine));
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
