package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInformation {
    private final LottoCount lottoCount;
    private final List<LottoNumbers> manualLottosNumbers;

    public PurchaseInformation(final LottoCount lottoCount) {
        this.lottoCount = lottoCount;
        this.manualLottosNumbers = new ArrayList<>();
    }

    public int getAutoLottoCount() {
        return lottoCount.getAutoLottoCount();
    }

    public int getManualLottoCount() {
        return lottoCount.getManualLottoCount();
    }

    public List<LottoNumbers> getManualLottosNumbers() {
        return manualLottosNumbers;
    }

    public void addManualLottoNumbers(final LottoNumbers lottoNumbers) {
        manualLottosNumbers.add(lottoNumbers);
    }

    public boolean hasManualLottos() {
        return lottoCount.hasManualLottos();
    }

    public boolean isValidPurchaseInformation() {
        return lottoCount.isEqualManualLottoCount(manualLottosNumbers.size());
    }
}
