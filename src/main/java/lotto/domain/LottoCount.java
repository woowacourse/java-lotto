package lotto.domain;

import lotto.exception.ExceedMoneyException;
import lotto.util.StringUtil;

public class LottoCount {
    private final int manualLotto;
    private final int autoLotto;

    public LottoCount(String manualLotto, int totalLotto) {
        validate(manualLotto);
        validateMoney(manualLotto, totalLotto);
        this.manualLotto = Integer.parseInt(manualLotto);
        this.autoLotto = totalLotto - this.manualLotto;
    }

    private void validate(String input) {
        StringUtil.checkNull(input);
        StringUtil.checkBlank(input);
        StringUtil.checkNumberFormat(input);
        StringUtil.checkRange(input);
    }

    private void validateMoney(String input, int totalLotto) {
        if (totalLotto < Integer.parseInt(input)) {
            throw new ExceedMoneyException(totalLotto + "장 이하만 구매가 가능합니다.");
        }
    }

    public int getManualLotto() {
        return manualLotto;
    }

    public int getAutoLottoCount() {
        return autoLotto;
    }
}
