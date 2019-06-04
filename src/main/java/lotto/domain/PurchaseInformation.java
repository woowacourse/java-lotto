package lotto.domain;

import lotto.exception.InvalidPurchaseInformationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchaseInformation {
    private final LottoCount lottoCount;
    private final List<String> manualLottosNumbers;

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

    public List<String> getManualLottosNumbers() {
        return Collections.unmodifiableList(manualLottosNumbers);
    }

    public void addManualLottoNumbers(final String lottoNumbers) {
        manualLottosNumbers.add(lottoNumbers);
    }

    public boolean hasManualLottos() {
        return lottoCount.hasManualLottos();
    }

    public void checkValidPurchaseInformation() {
        if (!lottoCount.isEqualManualLottoCount(manualLottosNumbers.size())) {
            throw new InvalidPurchaseInformationException("수동 로또 정보가 올바르지 않습니다.");
        }
    }
}
