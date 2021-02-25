package lotto.lottogame;

import static lotto.lottoticket.LottoNumber.ERROR_MESSAGE_INVALID_RANGE;
import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;

public class ManualLottoCount extends LottoCount {
    private final int manualLottoCount;

    public ManualLottoCount(String value) {
        super(makeNumber(value));
        this.manualLottoCount = makeNumber(value);
    }

    private static int makeNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_INPUT);
        }
    }

    public void validRange(LottoCount lottoCount) {
        if (manualLottoCount < ZERO || lottoCount.isSmallerThan(manualLottoCount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE);
        }
    }

    public LottoCount makeAutoCount(LottoCount lottoCount) {
        validRange(lottoCount);
        return new LottoCount(lottoCount.subtractCount(manualLottoCount));
    }
}
