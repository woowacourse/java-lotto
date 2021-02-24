package lotto.lottogame;

import java.util.Objects;

import static lotto.lottogame.LottoCount.ZERO;
import static lotto.lottoticket.LottoNumber.ERROR_MESSAGE_INVALID_RANGE;
import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;

public class ManualLottoCount {
    private int manualLottoCount;

    public ManualLottoCount(String value) {
        this.manualLottoCount = makeNumber(value);
    }

    private int makeNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    public void validRange(LottoCount lottoCount) {
        if(manualLottoCount < ZERO || lottoCount.isSmallerThan(manualLottoCount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public AutoLottoCount makeManualCount(LottoCount lottoCount) {
        validRange(lottoCount);
        return new AutoLottoCount(lottoCount.subtractCount(manualLottoCount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManualLottoCount that = (ManualLottoCount) o;
        return manualLottoCount == that.manualLottoCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualLottoCount);
    }
}
