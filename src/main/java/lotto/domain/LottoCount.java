package lotto.domain;

import static lotto.domain.NumberValidator.validateNullAndEmptyValue;
import static lotto.domain.NumberValidator.validateNumberFormat;

public class LottoCount {
    private static final int MIN_MANUAL_LOTTO_COUNT = 0;

    private final int lottoCountValue;
    private final int manualLottoCountValue;

    public LottoCount(LottoMoney lottoMoney, String manualLottoCount) {
        validateNull(lottoMoney);
        int lottoCount = lottoMoney.calculateLottoCount();
        validate(manualLottoCount, lottoCount);
        this.lottoCountValue = lottoCount;
        this.manualLottoCountValue = Integer.parseInt(manualLottoCount);
    }

    private static void validateNull(LottoMoney money) {
        if (money == null) {
            throw new RuntimeException("null이 들어왔습니다.");
        }
    }

    private static void validate(String manualLottoCount, int lottoCount) {
        validateNullAndEmptyValue(manualLottoCount, "수동으로 구매할 로또 수를 입력해 주세요.");
        validateNumberFormat(manualLottoCount, "수동으로 구매할 로또 수는 숫자만 입력 가능합니다.");
        validateNumberRange(manualLottoCount, lottoCount, String.format("수동으로 구매할 로또 수는 %d 이상 %d" +
                                                                                "(=구매한 총 로또 수) " +
                                                                                "이하여야 합니다.",
                                                                        MIN_MANUAL_LOTTO_COUNT,
                                                                        lottoCount));
    }

    private static void validateNumberRange(String manualLottoCount, int lottoCount,
                                            String message) {
        int manualLottoCountValue = Integer.parseInt(manualLottoCount);
        if (manualLottoCountValue < MIN_MANUAL_LOTTO_COUNT || lottoCount < manualLottoCountValue) {
            throw new RuntimeException(message);
        }
    }

    public boolean hasMinManualLottoCount() {
        return manualLottoCountValue == MIN_MANUAL_LOTTO_COUNT;
    }

    public int getManualLottoCountValue() {
        return manualLottoCountValue;
    }

    public int calculateAutoLottoCount() {
        return lottoCountValue - manualLottoCountValue;
    }
}
