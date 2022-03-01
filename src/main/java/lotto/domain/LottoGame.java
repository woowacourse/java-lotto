package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private final PurchaseAmount purchaseAmount;
    private final int manualLottoCount;
    private final int autoLottoCount;
    private final List<Lotto> lottos = new ArrayList<>();

    public LottoGame(PurchaseAmount purchaseAmount, int manualLottoCount) {
        this.purchaseAmount = purchaseAmount;

        validateCanPurchase(manualLottoCount);
        this.manualLottoCount = manualLottoCount;
        autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottoCount);
    }

    private void validateCanPurchase(int count) {
        if (!purchaseAmount.canPurchase(count)) {
            throw new IllegalArgumentException("구입 금액으로 살 수 있는 수량이어야 합니다.");
        }
    }

    public void makeManualLottos(List<List<Integer>> inputLottos) {
        validateEqualToManualLottoCount(inputLottos);

        for (List<Integer> inputLotto : inputLottos) {
            lottos.add(new FixedLottoMachine(inputLotto).makeLottos());
        }
    }

    private void validateEqualToManualLottoCount(List<List<Integer>> inputLottos) {
        if (inputLottos.size() != manualLottoCount) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수와 같은 개수의 로또를 입력해주세요.");
        }
    }

    public void makeAutoLottos() {
        LottoMachine lottoMachine = new RandomLottoMachine();
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(lottoMachine.makeLottos());
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
