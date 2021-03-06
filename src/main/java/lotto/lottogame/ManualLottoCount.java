package lotto.lottogame;

import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;

public class ManualLottoCount extends LottoCount {
    public static final String ERROR_MESSAGE_INVALID_RANGE_WITH_COUNT = "횟수 입력이 옳지 않습니다.";

    private final int manualLottoCount;

    public ManualLottoCount(String value, LottoCount lottoCount) {
        super(makeNumber(value));
        this.manualLottoCount = makeNumber(value);
        validRange(lottoCount);
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
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_RANGE_WITH_COUNT);
        }
    }

    public LottoCount makeAutoCount(LottoCount lottoCount) {
        return new LottoCount(lottoCount.subtractCount(manualLottoCount));
    }
}
