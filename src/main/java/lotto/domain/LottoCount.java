package lotto.domain;

import static lotto.util.NullValidator.validateNull;
import static lotto.util.NumberValidator.validateNullAndEmptyValue;
import static lotto.util.NumberValidator.validateNumberFormat;

public class LottoCount {
    public static final int MIN_MANUAL_LOTTO_COUNT = 0;

    private final int lottoCountValue;
    private final int manualLottoCountValue;

    public LottoCount(LottoMoney lottoMoney, String manualLottoCount) {
        validateNull(lottoMoney);
        int lottoCount = lottoMoney.calculateLottoCount();
        validate(manualLottoCount, lottoCount);
        this.lottoCountValue = lottoCount;
        this.manualLottoCountValue = Integer.parseInt(manualLottoCount);
    }

    private static void validate(String manualLottoCount, int lottoCount) {
        validateNullAndEmptyValue(manualLottoCount, "수동으로 구매할 로또 수를 입력해 주세요.");
        validateNumberFormat(manualLottoCount, "수동으로 구매할 로또 수는 숫자만 입력 가능합니다.");
        String message = String.format("수동으로 구매할 로또 수는 %d 이상 %d(=구매한 총 로또 수) 이하여야 합니다.",
                                       MIN_MANUAL_LOTTO_COUNT,
                                       lottoCount);
        validateNumberRange(manualLottoCount, lottoCount, message);
    }

    private static void validateNumberRange(String manualLottoCount, int lottoCount,
                                            String message) {
        int manualLottoCountValue = Integer.parseInt(manualLottoCount);
        if (manualLottoCountValue < MIN_MANUAL_LOTTO_COUNT || lottoCount < manualLottoCountValue) {
            throw new RuntimeException(message);
        }
    }

    public int getManualLottoCountValue() {
        return manualLottoCountValue;
    }

    public int calculateAutoLottoCount() {
        return lottoCountValue - manualLottoCountValue;
    }
}
