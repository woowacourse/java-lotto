package lotto.domain.lottoTicket;

import lotto.exception.InvalidManualLottoAmountException;

public class LottoAmount {
    int manualLottoAmount;
    int autoLottoAmount;

    public LottoAmount(int totalLottoAmount, int manualLottoAmount) {
        validateManualLottoAmount(totalLottoAmount, manualLottoAmount);
        this.manualLottoAmount = manualLottoAmount;
        this.autoLottoAmount = totalLottoAmount - manualLottoAmount;
    }

    private void validateManualLottoAmount(int totalLottoAmount, int manualLottoAmount) {
        if (totalLottoAmount < manualLottoAmount) {
            throw new InvalidManualLottoAmountException();
        }
    }

    public int getManualLottoAmount() {
        return manualLottoAmount;
    }

    public int getAutoLottoAmount() {
        return autoLottoAmount;
    }
}
